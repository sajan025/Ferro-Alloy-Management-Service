package com.jsp.ferro.alloy.finance.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.exception.RequestException;
import com.jsp.ferro.alloy.finance.dao.FerrorCostingDao;
import com.jsp.ferro.alloy.finance.dao.FinanceCostingHistoryDao;
import com.jsp.ferro.alloy.finance.dao.FinanceDao;
import com.jsp.ferro.alloy.finance.dao.FinanceHistoryDao;
import com.jsp.ferro.alloy.finance.dto.FinanceRequestDto;
import com.jsp.ferro.alloy.finance.entity.FerroCosting;
import com.jsp.ferro.alloy.finance.entity.FerroCostingHistory;
import com.jsp.ferro.alloy.finance.entity.FinanceRequest;
import com.jsp.ferro.alloy.finance.entity.FinanceRequestHistrory;
import com.jsp.ferro.alloy.gen.entity.BaseAdditionalFields;
import com.jsp.ferro.alloy.gen.service.BaseEntityService;
import com.jsp.ferro.alloy.sms.entity.SmsRequest;

@Service
@Transactional
public class FinanceService extends BaseEntityService<FinanceRequest, FinanceDao> {

	@Autowired private ModelMapper mapper;
	@Autowired private FinanceHistoryDao historyDao;
	@Autowired private FerrorCostingDao costDao;
	@Autowired private FinanceCostingHistoryDao financeCostingHistoryDao;

	public FinanceService(FinanceDao dao) {
		super(dao);
	}

	public List<FinanceRequestDto> findAllRequest() {
		return getDao().findAll().stream().map(this::convert).collect(Collectors.toList());
	}

	public FinanceRequestDto convert(FinanceRequest request) {
		return mapper.map(request, FinanceRequestDto.class);
	}

	public FinanceRequestDto saveFinanceRequest(FinanceRequestDto financeDto) {

		FinanceRequest financeRequest = mapper.map(financeDto, FinanceRequest.class);
		if (financeRequest != null) {
			financeRequest = save(financeRequest);
			if (financeRequest.getStatus().equalsIgnoreCase("Submit")) {
				FinanceRequestHistrory fhistory = FinanceRequestHistrory.builder().id(null)
						.segment(financeRequest.getSegment()).subSegment(financeRequest.getSubSegment())
						.alloyExtra(financeRequest.getAlloyExtra())
						.qcRejectionImpactPer(financeRequest.getQcRejectionImpactPer())
						.qcRejectionImpactRs(financeRequest.getQcRejectionImpactRs())
						.smsProcessExtra(financeRequest.getSmsProcessExtra())
						.plateMillYieldImpact(financeRequest.getPlateMillYieldImpact())
						.plateMillNco(financeRequest.getPlateMillNco())
						.plateMillNcoImpact(financeRequest.getPlateMillNcoImpact())
						.plateMillRejection(financeRequest.getPlateMillRejection())
						.plateMillRejectionImpact(financeRequest.getPlateMillRejectionImpact())
						.processCharges(financeRequest.getProcessCharges())
						.totalRevisedCost(financeRequest.getTotalRevisedCost()).remarks(financeRequest.getRemarks())
						.ferroCostingRateFkId(financeRequest.getFerroCostingRateFkId())
						.status(financeRequest.getStatus()).qualityRequestFkId(financeRequest.getQualityRequestFkId())
						.financeRequestFkId(financeRequest.getId()).build();

				historyDao.save(fhistory);
				// send mail
			}

		} else {
			throw new RequestException("Request can't be empty, Please fill all the required data.");
		}
		return mapper.map(financeRequest, FinanceRequestDto.class);
	}

	public FinanceRequest fatchAllRequest1(Optional<Integer> id) {
		FinanceRequest entities = null;
		if (id != null) {
			entities = getDao().getById(id);
		}
		return entities;

	}

	public List<FinanceRequestHistrory> fatchfinanceHistoryData(Optional<Integer> id) {
		List<FinanceRequestHistrory> entities = null;
		if(id != null) {
			entities = historyDao.fatchfinanceHistoryData(id);
		} 
		return entities;
	}

	public void saveFinanceCostRequest(List<FerroCosting> ferroCostList) {
		for(FerroCosting ferroCost:ferroCostList) {
			setAdditionalFields(ferroCost);
			costDao.save(ferroCost);
			if(ferroCost.getStatus().equalsIgnoreCase("Submit")) {
				FerroCostingHistory history=FerroCostingHistory.builder().id(null)
						.qualityRequestFkId(ferroCost.getQualityRequestFkId())
						.itemCode(ferroCost.getItemCode())
						.description(ferroCost.getDescription())
						.baseRates(ferroCost.getBaseRates())
						.ratesLcncBasis(ferroCost.getRatesLcncBasis())
						.sapRate(ferroCost.getSapRate())
						.ferroCostingFkId(ferroCost.getId())
						.build();
				setAdditionalFields(history);
				financeCostingHistoryDao.save(history);
			}
		}
		
	}
	private void setAdditionalFields(FerroCostingHistory entity) {
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
	private void setAdditionalFields(FerroCosting entity) {
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

	public List<FerroCosting> fatchAllCostingRate(Optional<Integer> qualityFkId) {
		// TODO Auto-generated method stub
		return costDao.fatchAllCostingRate(qualityFkId);
	}

	public List<FerroCostingHistory> fatchCostingHistoryData(Optional<Integer> id) {
		// TODO Auto-generated method stub
		return financeCostingHistoryDao.fatchCostingHistoryData(id);
	}

	

}
