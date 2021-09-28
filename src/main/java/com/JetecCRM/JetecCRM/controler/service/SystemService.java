package com.JetecCRM.JetecCRM.controler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;

@Service
@Transactional
public class SystemService {
	
	@Autowired
	AdminRepository ar;
/////////////////////////////////////////////////////////////////////////////////////	
	//讀取員工列表
	public Object getAdminList() {		
		return ar.findAll();
	}
/////////////////////////////////////////////////////////////////////////////////////
	//讀取員工細節
	public AdminBean getById(Integer id) {
		return ar.getById(id);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存員工
	public void SaveAdmin(AdminBean abean) {
		ar.save(abean);
		
	}
	
	

}
