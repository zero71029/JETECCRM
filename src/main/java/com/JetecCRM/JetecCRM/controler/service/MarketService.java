package com.JetecCRM.JetecCRM.controler.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.AgreementBean;
import com.JetecCRM.JetecCRM.model.MarketBean;
import com.JetecCRM.JetecCRM.model.MarketRemarkBean;
import com.JetecCRM.JetecCRM.model.QuotationBean;
import com.JetecCRM.JetecCRM.model.TrackBean;
import com.JetecCRM.JetecCRM.repository.AgreementRepository;
import com.JetecCRM.JetecCRM.repository.MarketRemarkRepository;
import com.JetecCRM.JetecCRM.repository.MarketRepository;
import com.JetecCRM.JetecCRM.repository.QuotationRepository;
import com.JetecCRM.JetecCRM.repository.TrackRepository;

@Service
@Transactional
public class MarketService {
	@Autowired
	MarketRepository mr;
	@Autowired
	MarketRemarkRepository mrr;
	@Autowired
	TrackRepository tr;
	@Autowired
	QuotationRepository qr;
	@Autowired
	AgreementRepository ar;
	
	

	public void save(MarketBean marketBean) {
		mr.save(marketBean);
	}

/////////////////////////////////////////////////////////////////////////////////////
	public List<MarketBean> getList() {		
		Sort sort =  Sort.by(Direction.DESC,"marketid");		
		return mr.findAll( sort);
	}

/////////////////////////////////////////////////////////////////////////////////////
	public MarketBean getById(Integer id) {
		return mr.getById(id);
	}

/////////////////////////////////////////////////////////////////////////////////////	
	public void SaveRemark(MarketRemarkBean mrb) {
		mrr.save(mrb);
	}

/////////////////////////////////////////////////////////////////////////////////////	
	// 刪除銷售機會
	public void delMarket(List<Integer> id) {
		for (Integer i : id) {
			mrr.deleteByMarketid(i);
			mr.deleteById(i);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////	
//搜索銷售機會
	public List<MarketBean> selectMarket(String name) {
		List<MarketBean> result= new ArrayList<MarketBean>();
		boolean boo = true;
		// 搜索名稱
		for (MarketBean p : mr.findByNameLikeIgnoreCase("%" + name + "%")) {
			result.add(p);
		}

		// 用業務搜索
		for (MarketBean p : mr.findByUserLikeIgnoreCase("%" + name + "%")) {
			for (MarketBean bean : result) {
				if (bean.getMarketid() == p.getMarketid()) {
					boo = false;
				}
			}
			if (boo)result.add(p);
		}
		// 用客戶搜索
		for (MarketBean p : mr.findByClientLikeIgnoreCase("%" + name + "%")) {
			for (MarketBean bean : result) {
				if (bean.getMarketid() == p.getMarketid()) {
					boo = false;
				}
			}
			if (boo)result.add(p);
		}
		// 用聯絡人搜索
//		for (MarketBean p : mr.findByContactpersonLikeIgnoreCase("%" + name + "%")) {
//			for (MarketBean bean : result) {
//				if (bean.getMarketid() == p.getMarketid()) {
//					boo = false;
//				}
//			}
//			if (boo)result.add(p);
//		}
		
		return result;
	}
/////////////////////////////////////////////////////////////////////////////////////	
//刪除備註
	public void delRemark(Integer remarkId) {
		mrr.deleteById(remarkId);		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存追蹤
	public void SaveTrack(TrackBean trackBean) {
		tr.save(trackBean);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存報價單
	public void SaveQuotation(QuotationBean qBean) {
		qr.save(qBean);
//		qr.delNull();
//		qr.alterINCREMENT();
		
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取報價單細節
	public QuotationBean getQuotationById(Integer id) {	
		return qr.getById(id);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//報價單列表
	public List<QuotationBean> getQuotationList() {
		Sort sort =  Sort.by(Direction.DESC,"quotationid");		
		return qr.findAll(sort);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存合約	
	public void SaveAgreement(AgreementBean aBean) {
		ar.save(aBean);
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取合約細節
	public AgreementBean getAgreementBeanById(Integer id) {		
		return ar.getById(id);
	}
	



}
