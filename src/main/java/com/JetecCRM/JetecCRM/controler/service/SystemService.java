package com.JetecCRM.JetecCRM.controler.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Service
@Transactional
public class SystemService {

	@Autowired
	AdminRepository ar;
	@Autowired
	BillboardRepository br;

/////////////////////////////////////////////////////////////////////////////////////	
	// 讀取員工列表
	public Object getAdminList() {
		return ar.findAll();
	}

/////////////////////////////////////////////////////////////////////////////////////
	// 讀取員工細節
	public AdminBean getById(Integer id) {
		return ar.getById(id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存員工
	public void SaveAdmin(AdminBean abean) {
		ar.save(abean);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除員工
	public void delAdmin(List<Integer> id, HttpServletRequest sce) {
		for (Integer i : id) {
			ar.deleteById(i);
		}
		ServletContext app = sce.getServletContext();
		app.setAttribute("admin", ar.findAll());
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表
	public List<BillboardBean> getBillboardList(String state) {
		Sort sort = Sort.by(Direction.DESC, "billboardid");
		return br.getByState(state, sort);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	public void SaveBillboard(BillboardBean bean) {
		String content = bean.getContent();
		bean.setContent(content.replaceAll("\\n", "<br>"));
		br.save(bean);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄細節 公佈欄用
	public BillboardBean getBillboardById(Integer id) {
		BillboardBean bean = br.getById(id);
//		bean.setContent(bean.getContent().replaceAll("<br>", "\n"));		
		return bean;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄細節 後台用
	public BillboardBean getBillboard(Integer id) {

		return br.getById(id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除公佈欄
	public void delBillboard(List<Integer> id) {
		for (Integer i : id) {
			br.deleteById(i);
		}

	}

}
