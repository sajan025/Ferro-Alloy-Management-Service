package com.jsp.ferro.alloy.qlty.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.gen.service.BaseEntityService;
import com.jsp.ferro.alloy.qlty.dao.QualityHistoryDao;
import com.jsp.ferro.alloy.qlty.entity.QualityRequestHistory;
@Service
@Transactional
public class QualityHistoryService extends BaseEntityService<QualityRequestHistory, QualityHistoryDao> {
	
	@Autowired
	private QualityHistoryDao qualityHistoryDao;
	

	public QualityHistoryService(QualityHistoryDao dao) {
		super(dao);
	}

	public List<QualityRequestHistory> fatchQualityHistoryData(Optional<Integer> id) {
		List<QualityRequestHistory> entities=null;
	
		if(id!=null) {
			entities= qualityHistoryDao.fatchQualityHistoryData(id);
			  
		}else {
		
		}
		return entities;
	}

}
