package com.JetecCRM.JetecCRM.controler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.MarketBean;
import com.JetecCRM.JetecCRM.model.PotentialCustomerBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.PotentialCustomerRepository;
import com.JetecCRM.JetecCRM.repository.TrackRepository;

@Service
@Transactional
public class PotentialCustomerService {

	@Autowired
	PotentialCustomerRepository PCR;
	@Autowired
	TrackRepository tr;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存潛在客戶列表
	public void SavePotentialCustomer(PotentialCustomerBean pcb) {
		PCR.save(pcb);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取潛在客戶列表
	public List<PotentialCustomerBean> getList() {
		return PCR.findAll();
	}

	public PotentialCustomerBean getById(Integer id) {
		return PCR.getById(id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除潛在客戶
	public void delPotentialCustomer(List<Integer> id) {
		for (Integer i : id) {
			tr.deleteByCustomerid(i);
			PCR.deleteById(i);
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//搜索潛在客戶
	public Object selectPotentialCustomer(String name) {
		List<PotentialCustomerBean> result = new ArrayList<PotentialCustomerBean>();
		boolean boo = true;
// 搜索名稱
		for (PotentialCustomerBean p : PCR.findByNameLikeIgnoreCase("%" + name + "%")) {
			result.add(p);
		}

// 用業務搜索
		for (PotentialCustomerBean p : PCR.findByUserLikeIgnoreCase("%" + name + "%")) {
			for (PotentialCustomerBean bean : result) {
				if (bean.getCustomerid() == p.getCustomerid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}
// 用公司搜索
		for (PotentialCustomerBean p : PCR.findByCompanyLikeIgnoreCase("%" + name + "%")) {
			for (PotentialCustomerBean bean : result) {
				if (bean.getCustomerid() == p.getCustomerid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}

		return result;

	}

}
