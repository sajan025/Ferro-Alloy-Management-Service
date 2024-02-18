package com.jsp.ferro.alloy.sms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
import com.jsp.ferro.alloy.gen.entity.BaseAdditionalFields;
import com.jsp.ferro.alloy.gen.service.BaseEntityService;
import com.jsp.ferro.alloy.qlty.dao.QualityDao;
import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;
import com.jsp.ferro.alloy.qlty.entity.QualityRequest;
import com.jsp.ferro.alloy.sms.dao.SmsRequestDao;
import com.jsp.ferro.alloy.sms.dao.SmsRequestHistoryDao;
import com.jsp.ferro.alloy.sms.dto.SmsRequestDto;
import com.jsp.ferro.alloy.sms.entity.SMsRequestHistrory;
import com.jsp.ferro.alloy.sms.entity.SmsRequest;

@Service
@Transactional
public class SmsService extends BaseEntityService<QualityRequest, QualityDao> {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private SmsRequestDao smsRequestDao;
	@Autowired
	private SmsRequestHistoryDao smsRequestHistoryDao;

	public SmsService(QualityDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	public Page<QualityRequestDto> fatchAllRequest(Optional<Integer> page, Optional<String> sortBy,
			Optional<String> product, Optional<String> grade, Optional<String> internalGrade) {
		Page<QualityRequest> entities;
		Pageable pageable = null;
		if (product.isPresent() || grade.isPresent() || internalGrade.isPresent()) {
			entities = getDao().search(product, grade, internalGrade, pageable);
		} else {
			entities = getDao().findAllByStatus(
					PageRequest.of(page.orElse(0), 4, Sort.Direction.DESC, sortBy.orElse("id")), "Submit");
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

	public SmsRequestDto saveSmsData(SmsRequestDto smsRequestDto) {
		SmsRequest smsRequest = mapper.map(smsRequestDto, SmsRequest.class);
		setAdditionalFields(smsRequest);
		smsRequest = smsRequestDao.save(smsRequest);
		// save data to history
		SMsRequestHistrory history = SMsRequestHistrory.builder().id(null)
				.qualityRequestFkId(smsRequestDto.getQualityRequestFkId().intValue())
				.segment(smsRequestDto.getSegment()).subSegment(smsRequestDto.getSubSegment())
				.processRoute(smsRequestDto.getProcessRoute())
				.cokePetroleumCalcined(smsRequestDto.getCokePetroleumCalcined())
				.graphiteFines(smsRequestDto.getGraphiteFines()).silicoManganeseHP(smsRequestDto.getSilicoManganeseHP())
				.silicoManganesExtraLowC(smsRequestDto.getSilicoManganesExtraLowC())
				.silicoManganeseLowPhos(smsRequestDto.getSilicoManganeseLowPhos())
				.manganeseMetalBriqutes(smsRequestDto.getManganeseMetalBriqutes())
				.ferroManganeseHC(smsRequestDto.getFerroManganeseHC())
				.ferroMangansesLC(smsRequestDto.getFerroMangansesLC()).ferroSilicon(smsRequestDto.getFerroSilicon())
				.aluminiumDross(smsRequestDto.getAluminiumDross()).ingotAL(smsRequestDto.getIngotAL())
				.wireAL(smsRequestDto.getWireAL()).wire16MM(smsRequestDto.getWire16MM())
				.caWirePure(smsRequestDto.getCaWirePure()).calcinedLime(smsRequestDto.getCalcinedLime())
				.copperCathode(smsRequestDto.getCopperCathode()).nickelMetal(smsRequestDto.getNickelMetal())
				.ferroChromeLC(smsRequestDto.getFerroChromeLC()).ferroChromeCR(smsRequestDto.getFerroChromeCR())
				.ferroNiobium(smsRequestDto.getFerroNiobium()).wireFETICored(smsRequestDto.getWireFETICored())
				.ferroVanadium(smsRequestDto.getFerroVanadium()).nitrovan(smsRequestDto.getNitrovan())
				.ferroMolybdenum(smsRequestDto.getFerroMolybdenum()).ferroBoron(smsRequestDto.getFerroBoron())
				.shotsAluminium(smsRequestDto.getShotsAluminium()).ferroAluminium(smsRequestDto.getFerroAluminium())
				.ferroPhosphorous(smsRequestDto.getFerroPhosphorous()).ferroTitaninum(smsRequestDto.getFerroTitaninum())
				.synSlagPreMelted(smsRequestDto.getSynSlagPreMelted()).slagFluidiser(smsRequestDto.getSlagFluidiser())
				.wire13MM(smsRequestDto.getWire13MM()).alCubes(smsRequestDto.getAlCubes())
				.manganeseCoreWire(smsRequestDto.getManganeseCoreWire())
				.totalAlloyCostPerTon(smsRequestDto.getTotalAlloyCostPerTon()).smsRemark(smsRequestDto.getSmsRemark())
				.build();
		setAdditionalFields(history);
		smsRequestHistoryDao.save(history);
		SmsRequestDto Dto = mapper.map(smsRequest, SmsRequestDto.class);
		return Dto;
	}
	private void setAdditionalFields(SmsRequest entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreatedDate(new Date());
            baseAdditionalFields.setCreatedBy(getCurrentCustomerId());
        }else {
        	baseAdditionalFields.setUpdatedDate(new Date());
        	baseAdditionalFields.setUpdatedBy(getCurrentCustomerId());
        }
        
        
    }
	private void setAdditionalFields(SMsRequestHistrory entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreatedDate(new Date());
            baseAdditionalFields.setCreatedBy(getCurrentCustomerId());
        }else {
        	baseAdditionalFields.setUpdatedDate(new Date());
        	baseAdditionalFields.setUpdatedBy(getCurrentCustomerId());
        }
        
        
    }

	public SmsRequestDto fatchAllSmsData(Optional<Integer> qualityFkid) {
		SmsRequest smsRequst=smsRequestDao.findByQualityRequestFkId(qualityFkid.orElseThrow(()->new CustomException("Id can not be null", HttpStatus.NOT_FOUND)));
		if(smsRequst==null) {  throw new CustomException("No record Found.",HttpStatus.NOT_FOUND);}
		SmsRequestDto dto=mapper.map(smsRequst, SmsRequestDto.class);
		return dto;
	}

	public List<SMsRequestHistrory> fatchSmsHistoryData(Optional<Integer> qualityFkid) {
		// TODO Auto-generated method stub
		return smsRequestHistoryDao.findAllByQualityFkId(qualityFkid);
	}

	

}
