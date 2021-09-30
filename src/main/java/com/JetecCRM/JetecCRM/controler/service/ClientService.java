package com.JetecCRM.JetecCRM.controler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.ClientBean;
import com.JetecCRM.JetecCRM.repository.ClientRepository;

@Service
@Transactional
public class ClientService {
	
	@Autowired
	ClientRepository cr;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存客戶
	public void SaveAdmin(ClientBean clientBean) {
		cr.save(clientBean);		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取客戶列表
	public Object getList() {		
		return cr.findAll();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取客戶細節
	public Object getById(Integer id) {
		
		return cr.getById(id);
	}

}
