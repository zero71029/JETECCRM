package com.JetecCRM.JetecCRM.controler.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.MarketBean;
import com.JetecCRM.JetecCRM.repository.MarketRepository;

@Service
@Transactional
public class MarketService {
	@Autowired
	MarketRepository mr;
	public void save(MarketBean marketBean) {		
		mr.save(marketBean);		
	}
/////////////////////////////////////////////////////////////////////////////////////
	public List<MarketBean> getList() {		
		return mr.findAll();
	}
/////////////////////////////////////////////////////////////////////////////////////
	public MarketBean getById(Integer id) {
		return mr.getById(id);
	}

}
