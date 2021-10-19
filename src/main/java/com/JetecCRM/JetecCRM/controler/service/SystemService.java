package com.JetecCRM.JetecCRM.controler.service;

import java.io.File;
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
import com.JetecCRM.JetecCRM.model.AdminMailBean;
import com.JetecCRM.JetecCRM.model.BillboardAdviceBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardFileBean;
import com.JetecCRM.JetecCRM.model.BillboardGroupBean;
import com.JetecCRM.JetecCRM.model.BillboardReadBean;
import com.JetecCRM.JetecCRM.model.BillboardReplyBean;
import com.JetecCRM.JetecCRM.model.NewsBean;
import com.JetecCRM.JetecCRM.repository.AdminMailRepository;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.BillboardAdviceRepository;
import com.JetecCRM.JetecCRM.repository.BillboardFileRepository;
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
	BillboardFileRepository bfr;
	@Autowired
	BillboardAdviceRepository bar;
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
	public String SaveAdmin(AdminBean abean) {
		System.out.println(abean);
		
		if (ar.existsByEmail(abean.getEmail()) & abean.getAdminid() == null)
			return "失敗,Email 已被使用";
		ar.save(abean);
		return "儲存成功,<a href=\"/time.jsp\">請重新登入</a>";
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
		Sort sort = Sort.by(Direction.DESC, "createtime");
		List<BillboardBean> list = br.getByStateAndTop(state, "置頂", sort);
		for (BillboardBean bean : list)
			resulet.add(bean);
		list = br.getByStateAndTop(state, "", sort);
		for (BillboardBean bean : list)
			resulet.add(bean);

		return resulet;
	}
	// 讀取公佈欄列表 加分類

	public List<BillboardBean> getBillboardList(String state, String billboardgroupid) {
		List<BillboardBean> resulet = new ArrayList<BillboardBean>();
		Sort sort = Sort.by(Direction.DESC, "billboardid");
		// 如果是 一般公告的全部
		if ("01dasgregrehvbcv一般公告".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "一般公告", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "一般公告", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		} else if ("01dasgregrehvbcvaaa研發".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "研發", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "研發", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		} else if ("01dasgregrehvbcvbbb業務".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "業務", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "業務", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		} else if ("01dasgregrehvbcvccc行銷".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "行銷", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "行銷", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		} else if ("01dasgregrehvbcvddd生產".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "生產", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "生產", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		} else if ("01dasgregrehvbcvfggg採購".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "採購", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "採購", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		} else if ("01dasgregrehvbcvaaa財務".equals(billboardgroupid)) {
			List<BillboardBean> list = br.getByStateAndBilltowngroupAndTop(state, "財務", "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBilltowngroupAndTop(state, "財務", "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;
		}else {// 不是的話 根據billboardgroupid尋找
			List<BillboardBean> list = br.getByStateAndBillboardgroupidAndTop(state, billboardgroupid, "置頂", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			list = br.getByStateAndBillboardgroupidAndTop(state, billboardgroupid, "", sort);
			for (BillboardBean bean : list)
				resulet.add(bean);
			return resulet;

		}

//		return resulet;

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	public BillboardBean SaveBillboard(BillboardBean bean, HttpSession session) {
		AdminMailBean adminMailBean = new AdminMailBean();
		// 插入換行
		String content = bean.getContent();
		bean.setContent(content.replaceAll("\\n", "<br>"));
		// 插入群組id
		BillboardGroupBean bgb = bgr.findByBillboardgroupAndBillboardoption(bean.getBilltowngroup(),
				bean.getBillboardgroupid());
		bean.setBillboardgroupid(bgb.getBillboardgroupid());
		// 儲存
		BillboardBean save = br.save(bean);
		// 如果封存 mail未讀全刪
		if (save.getState().equals("封存")) {
			amr.deleteAllByBillboardid(save.getBillboardid());
			return save;
		}
		// 未讀處理
		adminMailBean.setBillboardid(save.getBillboardid());
		// 郵件
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		String mailTo = adminBean.getEmail();
		String Subject = bean.getTheme();
		String text = bean.getContent();
		StringBuilder maillist = new StringBuilder();
		// 抓出所有人
		for (AdminBean a : ar.findAll()) {
			// 抓出所有人群發郵件
			maillist.append(a.getEmail());
			maillist.append(",");
			// 抓出所有人插入maill
			// 如果maill沒資料
			if (!amr.existsByBillboardidAndAdminid(save.getBillboardid(), a.getAdminid())) {
				adminMailBean.setAdminmail(zTools.getUUID());
				//如果員工部門 和 發布的部門 一樣就儲存
				if (a.getDepartment().equals(bean.getBilltowngroup())) {
					adminMailBean.setAdminid(a.getAdminid());
					amr.save(adminMailBean);
				}

			}
		}
		maillist.append("jeter.tony56@gmail.com");
//		zTools.mail(mailTo, text, Subject, maillist.toString());

		return save;

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

			if (amr.existsByBillboardid(i))
				amr.deleteAllByBillboardid(i);
			br.deleteById(i);
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//點擊已讀
	public String saveRead(Integer billboardid, Integer adminid) {
		// 刪除mail 插入read
		if (amr.existsByBillboardidAndAdminid(billboardid, adminid)) {
			amr.deleteByBillboardidAndAdminid(billboardid, adminid);
			AdminBean adminBean = ar.getById(adminid);
			BillboardReadBean brb = new BillboardReadBean();
			brb.setBillboardid(billboardid);
			brb.setReadid(zTools.getUUID());
			brb.setName(adminBean.getName());
			brr.save(brb);
			return "成功已讀";
		} else {
			return "找不到資料";
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//取消已讀
	public void ReRead(Integer billboardid, Integer adminid) {
		// 刪除read 插入mail
		if (!amr.existsByBillboardidAndAdminid(billboardid, adminid)) {
			AdminMailBean aBean = new AdminMailBean();
			aBean.setAdminid(adminid);
			aBean.setBillboardid(billboardid);
			aBean.setAdminmail(zTools.getUUID());
			amr.save(aBean);
			AdminBean adminBean = ar.getById(adminid);
			if (brr.existsByBillboardidAndName(billboardid, adminBean.getName())) {
				brr.deleteByBillboardidAndName(billboardid, adminBean.getName());
			}
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄留言
	public boolean SaveReply(BillboardReplyBean bean) {
		if (bean.getReplyid() == null || bean.getReplyid().length() == 0)
			bean.setReplyid(zTools.getUUID());
		billboardReplyRepository.save(bean);
		return true;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//新增群組子項
	public String saveOption(String group, String option) {
		if (bgr.existsByBillboardgroupAndBillboardoption(group, option)) {
			return "改項目存在";
		} else {
			BillboardGroupBean bean = new BillboardGroupBean();
			bean.setBillboardgroup(group);
			bean.setBillboardoption(option);
			bean.setBillboardgroupid(zTools.getUUID());
			bgr.save(bean);
			return group + " " + option + "" + "新增成功";
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除群組子項
	public String delOption(String group, String option) {
		if (bgr.existsByBillboardgroupAndBillboardoption(group, option)) {
			BillboardGroupBean bgBean = bgr.findByBillboardgroupAndBillboardoption(group, option);
			if (br.existsByBillboardgroupid(bgBean.getBillboardgroupid())) {
				return "該項目還有留言  不能刪除";
			}
			bgr.delete(bgBean);
			return "刪除成功";
		} else {
			return "找不到項目,請聯絡資訊人員";
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//更新群組
	public void updataOption(HttpServletRequest sce) {
		ServletContext app = sce.getServletContext();
		app.setAttribute("billboardgroup", bgr.findAll());
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3. 儲存檔案名稱到資料庫
	public void saveUrl(BillboardFileBean billBoardFileBean) {
		bfr.save(billBoardFileBean);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除型錄
	public void removefile(String fileid) {
		BillboardFileBean billBoardFileBean = bfr.getById(fileid);
		File file = new File("E:\\JetecCRM\\src\\main\\resources\\static\\file\\" + billBoardFileBean.getUrl());
		System.out.println(file.delete());
		bfr.delete(billBoardFileBean);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//搜索公布欄
	public List<BillboardBean> selectBillboardt(String search) {
		List<BillboardBean> result = new ArrayList<BillboardBean>();
		boolean boo = true;
		// 搜索主題
		Sort sort = Sort.by(Direction.DESC, "createtime");
		for (BillboardBean p : br.findByThemeLikeIgnoreCaseAndState("%" + search + "%", "發佈", sort)) {
			result.add(p);
		}

		// 用發表人搜索
		for (BillboardBean p : br.findByUserLikeIgnoreCaseAndState("%" + search + "%", "發佈", sort)) {
			for (BillboardBean bean : result) {
				if (bean.getBillboardgroupid() == p.getBillboardgroupid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取留言by時間
	public List<BillboardBean> getBillboardByTime() {
		return br.getBillboardByTime();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除留言
	public Integer delReply(String replyId) {
		BillboardReplyBean bean = billboardReplyRepository.getById(replyId);
		if (bean != null)
			billboardReplyRepository.delete(bean);
		return bean.getBillboardid();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//@他人
	public void saveAdvice(Integer[] adviceto, Integer adminid, Integer billboardid,String[] formname) {
		BillboardAdviceBean bab = new BillboardAdviceBean();
		bab.setBillboardid(billboardid);
		bab.setAdvicefrom(adminid);
		int index = 0;
		for(Integer a : adviceto) {
			if(!bar.existsByAdvicetoAndBillboardid(a,billboardid)) {
				bab.setAdviceto(a);
				bab.setAdviceid(zTools.getUUID());
				bab.setFormname(formname[index++]);
				bar.save(bab);
			}			
		}
		
	}

}
