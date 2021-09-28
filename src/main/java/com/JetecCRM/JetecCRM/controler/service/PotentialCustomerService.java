package com.JetecCRM.JetecCRM.controler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.PotentialCustomerBean;
import com.JetecCRM.JetecCRM.repository.PotentialCustomerRepository;

@Service
@Transactional
public class PotentialCustomerService {

	@Autowired
	PotentialCustomerRepository PCR;
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

}
