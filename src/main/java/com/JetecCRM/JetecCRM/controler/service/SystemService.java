package com.JetecCRM.JetecCRM.controler.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JetecCRM.JetecCRM.Tool.ZeroTools;
import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardReadBean;
import com.JetecCRM.JetecCRM.model.BillboardReplyBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReadRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReplyRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Service
@Transactional
public class SystemService {

	@Autowired
	AdminRepository ar;
	@Autowired
	BillboardRepository br;
	@Autowired
	BillboardReadRepository brr;
	@Autowired
	BillboardReplyRepository billboardReplyRepository;
	@Autowired
	ZeroTools zTools;

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
		List<BillboardBean> resulet = new ArrayList<BillboardBean>();
		Sort sort = Sort.by(Direction.DESC, "billboardid");
		List<BillboardBean>  list = br.getByStateAndTop(state,"置頂", sort);
		for(BillboardBean bean : list)resulet.add(bean);
		list = br.getByStateAndTop(state,"", sort);
		for(BillboardBean bean : list)resulet.add(bean);
		
		return resulet;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	public boolean SaveBillboard(BillboardBean bean,HttpSession session) {
		
		String content = bean.getContent();
		bean.setContent(content.replaceAll("\\n", "<br>"));
		br.save(bean);
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		String mailTo = adminBean.getEmail();
		String Subject = bean.getTheme();
		String text = bean.getContent();
		StringBuilder maillist = new StringBuilder();
		for (AdminBean a : ar.findAll()) {
			maillist.append(a.getEmail());
			maillist.append(",");
		}
		maillist.append("jeter.tony56@gmail.com");
		zTools.mail(mailTo, text, Subject, maillist.toString());

		return true;

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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//點擊已讀
	public String saveRead(Integer billboardid, String username) {
		if(brr.existsByBillboardidAndName(billboardid,username)) {
			return "找不到資料";
		}else {
			System.out.println(billboardid);
			System.out.println(username);
			BillboardReadBean bean =new BillboardReadBean();
			bean.setBillboardid(billboardid);
			bean.setName(username);
			bean.setReadid(zTools.getUUID());
			System.out.println(bean);
			brr.save(bean);
			return "成功已讀";
		}
		
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//取消已讀
	public void ReRead(Integer billboardid, String username) {
		if(brr.existsByBillboardidAndName(billboardid,username)) {
			
			brr.deleteByBillboardidAndName(billboardid,username);
		}
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄留言
	public boolean SaveReply(BillboardReplyBean bean) {
		bean.setReplyid(zTools.getUUID());
		billboardReplyRepository.save(bean);
		return true;
	}

}
