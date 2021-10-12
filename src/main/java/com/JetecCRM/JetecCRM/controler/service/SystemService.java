package com.JetecCRM.JetecCRM.controler.service;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;
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
import com.JetecCRM.JetecCRM.model.AdminMailBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardGroupBean;
import com.JetecCRM.JetecCRM.model.BillboardReadBean;
import com.JetecCRM.JetecCRM.model.BillboardReplyBean;
import com.JetecCRM.JetecCRM.repository.AdminMailRepository;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.BillboardGroupRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReadRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReplyRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Service
@Transactional
public class SystemService {

	@Autowired
	AdminRepository ar;
	@Autowired
	AdminMailRepository amr;
	@Autowired
	BillboardRepository br;
	@Autowired
	BillboardReadRepository brr;
	@Autowired
	BillboardReplyRepository billboardReplyRepository;
	@Autowired
	BillboardGroupRepository bgr; 
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
	//讀取公佈欄列表  加分類	
	
	public List<BillboardBean> getBillboardList(String state, String billboardgroupid) {
		List<BillboardBean> resulet = new ArrayList<BillboardBean>();
		Sort sort = Sort.by(Direction.DESC, "billboardid");
		//如果是 一般公告的全部
		if("01dasgregrehvbcv一般公告".equals(billboardgroupid)) {
			List<BillboardBean>  list = br.getByStateAndBilltowngroupAndTop(state,"一般公告","置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state,"一般公告","", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
		}else if ("01dasgregrehvbcvaaa研發".equals(billboardgroupid)) {
			List<BillboardBean>  list = br.getByStateAndBilltowngroupAndTop(state,"研發","置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state,"研發","", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
		}else if ("01dasgregrehvbcvbbb業務".equals(billboardgroupid)) {
			List<BillboardBean>  list = br.getByStateAndBilltowngroupAndTop(state,"業務","置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state,"業務","", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
		}else if ("01dasgregrehvbcvccc行銷".equals(billboardgroupid)) {
			List<BillboardBean>  list = br.getByStateAndBilltowngroupAndTop(state,"行銷","置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state,"行銷","", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
		}else if ("01dasgregrehvbcvddd生產".equals(billboardgroupid)) {
			List<BillboardBean>  list = br.getByStateAndBilltowngroupAndTop(state,"生產","置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state,"生產","", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
		}else if ("01dasgregrehvbcvfggg採購".equals(billboardgroupid)) {
			List<BillboardBean>  list = br.getByStateAndBilltowngroupAndTop(state,"採購","置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state,"採購","", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
		}else {//不是的話 根據billboardgroupid尋找
			List<BillboardBean>  list = br.getByStateAndBillboardgroupidAndTop(state,billboardgroupid,"置頂", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			list = br.getByStateAndBillboardgroupidAndTop(state,billboardgroupid,"", sort);
			for(BillboardBean bean : list)resulet.add(bean);
			return resulet;
			
		}

//		return resulet;
		
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	public boolean SaveBillboard(BillboardBean bean,HttpSession session) {
		AdminMailBean adminMailBean =new AdminMailBean();
		//插入換行
		String content = bean.getContent();
		bean.setContent(content.replaceAll("\\n", "<br>"));
		//插入群組id
		BillboardGroupBean bgb =bgr.findByBillboardgroupAndBillboardoption(bean.getBilltowngroup(),bean.getBillboardgroupid());
		bean.setBillboardgroupid(bgb.getBillboardgroupid());
		//
		BillboardBean save = br.save(bean);
		adminMailBean.setBillboardid(save.getBillboardid());
		//郵件
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		String mailTo = adminBean.getEmail();
		String Subject = bean.getTheme();
		String text = bean.getContent();
		StringBuilder maillist = new StringBuilder();
		//群發郵件
		for (AdminBean a : ar.findAll()) {	
			maillist.append(a.getEmail());
			maillist.append(",");
			//抓出所有人插入maill
			adminMailBean.setAdminmail(zTools.getUUID());
			adminMailBean.setAdminid(a.getAdminid());
			amr.save(adminMailBean);
		}
		maillist.append("jeter.tony56@gmail.com");
//		zTools.mail(mailTo, text, Subject, maillist.toString());

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//新增群組子項
	public void saveOption(String group, String option) {
		BillboardGroupBean bean = new BillboardGroupBean();
		bean.setBillboardgroup(group);
		bean.setBillboardoption(option);
		bean.setBillboardgroupid(zTools.getUUID());
		bgr.save(bean);

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除群組子項
	public String delOption(String group, String option) {
		if(bgr.existsByBillboardgroupAndBillboardoption(group,option)) {
			BillboardGroupBean bgBean =bgr.findByBillboardgroupAndBillboardoption(group,option);			
			if(br.existsByBillboardgroupid(bgBean.getBillboardgroupid())) {
				return "該項目還有留言  不能刪除";
			}
			bgr.delete(bgBean);
			return "刪除成功";
		}else {
			return "找不到項目";
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//更新群組
	public void updataOption(HttpServletRequest sce) {
		ServletContext app = sce.getServletContext();
		app.setAttribute("billboardgroup", bgr.findAll());
	}
	

}
