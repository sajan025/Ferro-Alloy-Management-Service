package com.jsp.ferro.alloy.qlty.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.exception.CustomException;
import com.jsp.ferro.alloy.exception.RequestException;
import com.jsp.ferro.alloy.gen.dao.MasterChemistryDao;
import com.jsp.ferro.alloy.gen.entity.BaseAdditionalFields;
import com.jsp.ferro.alloy.gen.entity.MasterChemistry;
import com.jsp.ferro.alloy.gen.service.BaseEntityService;
import com.jsp.ferro.alloy.gen.service.EmailService;
import com.jsp.ferro.alloy.model.User;
import com.jsp.ferro.alloy.qlty.dao.ChemicalCompositionHistoryDao;
import com.jsp.ferro.alloy.qlty.dao.QualityDao;
import com.jsp.ferro.alloy.qlty.dao.QualityHistoryDao;
import com.jsp.ferro.alloy.qlty.dto.ChemicalCompositionDto;
import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;
import com.jsp.ferro.alloy.qlty.entity.ChemicalComposition;
import com.jsp.ferro.alloy.qlty.entity.ChemicalCompositionHistory;
import com.jsp.ferro.alloy.qlty.entity.QualityRequest;
import com.jsp.ferro.alloy.qlty.entity.QualityRequestHistory;
import com.jsp.ferro.alloy.repository.UserRepository;
import com.jsp.ferro.alloy.sms.entity.SmsRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class QualityService extends BaseEntityService<QualityRequest, QualityDao> {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private QualityHistoryDao qualityHistoryDao;
	@Autowired
	private ChemicalCompositionHistoryDao chemicalCompositionHistoryDao;
	@Autowired
	private MasterChemistryDao chemistryDao;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	public QualityService(QualityDao dao) {
		super(dao);
	}

	public QualityRequestDto saveProductRequest(QualityRequestDto qualityDto) {
		QualityRequest qualityRequest = mapper.map(qualityDto, QualityRequest.class);
		for (ChemicalComposition c : qualityRequest.getCompositionList()) {
			c.setFkId(qualityRequest);

		}

		if (qualityRequest != null) {
			setAdditionalFields(qualityRequest);
			qualityRequest = save(qualityRequest);
			if (qualityRequest.getStatus().equalsIgnoreCase("Submit")) {
				// save data into history
				QualityRequestHistory qhistory = QualityRequestHistory.builder().id(null)
						.internalGrade(qualityRequest.getInternalGrade()).fkId(qualityRequest.getId().intValue())
						.externalGrade(qualityRequest.getExternalGrade()).product(qualityRequest.getProduct())
						.remarks(qualityRequest.getRemarks()).status(qualityRequest.getStatus()).compositionList(null)
						.build();
				setAdditionalFields(qhistory);
				qhistory = qualityHistoryDao.save(qhistory);
				List<ChemicalCompositionHistory> chemicalHistory = getCompositionList(
						qualityRequest.getCompositionList(), qhistory);
				chemicalCompositionHistoryDao.saveAll(chemicalHistory);

				// send mail
				List<User> userList = userRepository.findAll().stream()
						.filter(role -> role.getUserRole().stream().anyMatch(r -> r.getRoleId().equals(4)))
						.collect(Collectors.toList());
				for (User user : userList)
					emailService.sendEmail(qualityRequest, user.getEmail());
			}
		} else {
			throw new RequestException("Request can't be empty, Please fill all the required data.");
		}
		return mapper.map(qualityRequest, QualityRequestDto.class);
	}

	private List<ChemicalCompositionHistory> getCompositionList(List<ChemicalComposition> compositionList,
			QualityRequestHistory id) {
		List<ChemicalCompositionHistory> compositionHistoryList = new ArrayList<ChemicalCompositionHistory>();
		for (ChemicalComposition dto : compositionList) {
			compositionHistoryList.add(ChemicalCompositionHistory.builder().id(null).material(dto.getMaterial())
					.min(dto.getMin()).max(dto.getMax()).itemCode(dto.getItemCode()).actual(dto.getActual())
					.ceq(dto.getCeq()).leanChemGrade(dto.getLeanChemGrade()).fkId(id).build());
		}
		return compositionHistoryList;
	}

	public Page<QualityRequestDto> fatchAllRequest(Optional<Integer> page, Optional<String> sortBy,
			Optional<String> product, Optional<String> grade, Optional<String> internalGrade) {
		Page<QualityRequest> entities;
		Pageable pageable = null;
		if (product.isPresent() || grade.isPresent() || internalGrade.isPresent()) {
			entities = getDao().searchByParam(product, grade, internalGrade, pageable);
		} else {
			entities = getDao().findAll(PageRequest.of(page.orElse(0), 4, Sort.Direction.DESC, sortBy.orElse("id")));
		}

		Page<QualityRequestDto> dtoPage = entities.map(new Function<QualityRequest, QualityRequestDto>() {
			@Override
			public QualityRequestDto apply(QualityRequest entity) {
				return convert(entity);
			}
		});
		return dtoPage;
	}

	public QualityRequestDto convert(QualityRequest request) {
		return mapper.map(request, QualityRequestDto.class);
	}

	public QualityRequestDto fatchRequestId(Long id) {
		// TODO Auto-generated method stub
		return mapper.map(getDao().findById(id), QualityRequestDto.class);
	}

	public Page<QualityRequestDto> findProduct(Optional<String> externalGrade) {
		Page<QualityRequest> qualityRequest = getDao().findByExternalGrade(externalGrade, PageRequest.of(0, 1));
		Page<QualityRequestDto> qualityDto = null;
		if (qualityRequest.getContent().size() == 0) {
			Page<MasterChemistry> masterChemistry = chemistryDao.findByGrade(externalGrade, PageRequest.of(0, 1))
					.orElseThrow(() -> new CustomException("Data not found!", HttpStatus.NOT_FOUND));
			qualityDto = masterChemistry.map(new Function<MasterChemistry, QualityRequestDto>() {

				@Override
				public QualityRequestDto apply(MasterChemistry masterChemistry) {
					// TODO Auto-generated method stub
					return convertToDto(masterChemistry);
				}
			});
		} else {
			qualityDto = qualityRequest.map(new Function<QualityRequest, QualityRequestDto>() {
				@Override
				public QualityRequestDto apply(QualityRequest entity) {
					return convert(entity);
				}
			});
		}
		return qualityDto;
	}

	protected QualityRequestDto convertToDto(MasterChemistry masterChemistry) {
		// TODO Auto-generated method stub
		return QualityRequestDto.builder().externalGrade(masterChemistry.getGrade())
				.compositionList(getCompositionList(masterChemistry)).build();
	}

	private List<ChemicalCompositionDto> getCompositionList(MasterChemistry chemistry) {
		// TODO Auto-generated method stub
		return Arrays.asList(ChemicalCompositionDto.builder().min(chemistry.getCMin()).max(chemistry.getCMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getMnMin()).max(chemistry.getMnMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getSiMin()).max(chemistry.getSiMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getCrMin()).max(chemistry.getCrMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getVMin()).max(chemistry.getVMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getNbMin()).max(chemistry.getNbMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getNiMin()).max(chemistry.getNiMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getMoMin()).max(chemistry.getMoMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getCuMin()).max(chemistry.getCuMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getTiMin()).max(chemistry.getTiMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getCaMin()).max(chemistry.getCaMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getAlMin()).max(chemistry.getAlMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getBMin()).max(chemistry.getBMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getPMin()).max(chemistry.getPMax()).build(),
				ChemicalCompositionDto.builder().min(chemistry.getNMin()).max(chemistry.getNMax()).build());
	}

	private void setAdditionalFields(QualityRequest entity) {
		BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

		if (baseAdditionalFields == null) {
			baseAdditionalFields = new BaseAdditionalFields();
			entity.setBaseAdditionalFields(baseAdditionalFields);
		}

		if (entity.getId() == null) {
			baseAdditionalFields.setCreatedDate(new Date());
			baseAdditionalFields.setCreatedBy(getCurrentCustomerId());
		} else {
			baseAdditionalFields.setUpdatedDate(new Date());
			baseAdditionalFields.setUpdatedBy(getCurrentCustomerId());
		}

	}

	private void setAdditionalFields(QualityRequestHistory entity) {
		BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

		if (baseAdditionalFields == null) {
			baseAdditionalFields = new BaseAdditionalFields();
			entity.setBaseAdditionalFields(baseAdditionalFields);
		}

		if (entity.getId() == null) {
			baseAdditionalFields.setCreatedDate(new Date());
			baseAdditionalFields.setCreatedBy(getCurrentCustomerId());
		} else {
			baseAdditionalFields.setUpdatedDate(new Date());
			baseAdditionalFields.setUpdatedBy(getCurrentCustomerId());
		}

	}
}
