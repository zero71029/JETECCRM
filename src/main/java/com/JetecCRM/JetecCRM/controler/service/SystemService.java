package com.JetecCRM.JetecCRM.controler.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.JetecCRM.JetecCRM.model.BillboardTopBean;
import com.JetecCRM.JetecCRM.repository.AdminMailRepository;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.BillboardAdviceRepository;
import com.JetecCRM.JetecCRM.repository.BillboardFileRepository;
import com.JetecCRM.JetecCRM.repository.BillboardGroupRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReadRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReplyRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;
import com.JetecCRM.JetecCRM.repository.BillboardTopRepository;

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
	BillboardTopRepository btr;
	@Autowired
	ZeroTools zTools;

/////////////////////////////////////////////////////////////////////////////////////	
	// 讀取員工列表
	public Object getAdminList(String so) {
		Sort sort = Sort.by(Direction.ASC, so);
		return ar.findAll(sort);
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
		return "儲存成功,<a href='/CRM/time.jsp'>請重新登入</a>";
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
	public List<BillboardBean> getBillboardList(String state, AdminBean adminBean,Integer pag) {

//		Sort sort = Sort.by(Direction.DESC, "createtime");
		
//		分頁

//		Page<BillboardBean> page = br.findAll( PageRequest.of(pag, 12, sort));
//		page.getSize();每頁條數
//		page.getNumber();當前頁
//		page.getNumberOfElements();本頁條數
//		page.getTotalElements();全部幾筆
//		page.getTotalPages();全部有幾頁		
//		List<BillboardBean> result = page.getContent();
		if (pag < 1) pag = 1;
		pag--;
		Pageable p = (Pageable) PageRequest.of(pag, 12, Direction.DESC, "createtime");
//		Page<BillboardBean> page = br.findByStateAndTop("發佈", "置頂",p);
		List<BillboardBean> resulet = br.findByStateAndTop(state, "置頂",p);
		boolean boo = true;
//		List<BillboardBean> list = br.getByStateAndTop(state, "置頂", sort);
//		for (BillboardBean bean : list)
//			resulet.add(bean);
		if (adminBean != null) {
			for (BillboardTopBean btb : adminBean.getTop()) {
				for (BillboardBean bean : resulet) {
					if (bean.getBillboardid() == btb.getBillboardid()) {
						boo = false;
					}
				}
				if (boo)
					resulet.add(br.getById(btb.getBillboardid()));
				boo = true; 
			}
		}

		List<BillboardBean> list = br.findByStateAndTop(state, "",p);
		
		for (BillboardBean b : list) {
			for (BillboardBean bean : resulet) {
				if (bean.getBillboardid() == b.getBillboardid()) {
					boo = false;
				}
			}
			if (boo)
				resulet.add(b);
			boo = true;
		}

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
		} else {// 不是的話 根據billboardgroupid尋找
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
				// 如果員工部門 和 發布的部門 一樣就儲存
				if (a.getDepartment().equals(bean.getBilltowngroup())) {
					System.out.println("Name" + a.getName());
					adminMailBean.setAdminid(a.getAdminid());
					amr.save(adminMailBean);
				}
				if ("一般公告".equals(bean.getBilltowngroup())) {
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
			btr.deleteAllByBillboardid(i);

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
			// @ 如果存在 就取出 插入reply=1
			if (bar.existsByAdvicetoAndBillboardid(adminid, billboardid)) {
				BillboardAdviceBean bab = bar.getByAdvicetoAndBillboardid(adminid, billboardid);
				bab.setReply("0");
				bar.save(bab);
			}

			return "成功已讀  “您已被標註“ 訊息取消";
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
		
		
		// 獲取Tomcat伺服器所在的路徑
		String tomcat_path = System.getProperty( "user.dir" );
		System.out.println("Tomcat伺服器所在的路徑: "+tomcat_path);
		// 獲取Tomcat伺服器所在路徑的最後一個檔案目錄
		String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("/")+1,tomcat_path.length());
		System.out.println("Tomcat伺服器所在路徑的最後一個檔案目錄: "+bin_path);
		// 判斷最後一個檔案目錄是否為bin目錄
		if(("bin").equals(bin_path)){ 
			// 獲取儲存上傳圖片的檔案路徑
			String pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("/")) +"/webapps/CRM"+"/file/";
			File file = new File(pic_path + billBoardFileBean.getUrl());
			System.out.println(file.delete());
		}
		
		
		
		
		

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
	public void saveAdvice(Integer[] adviceto, Integer adminid, Integer billboardid) {
		BillboardAdviceBean bab = new BillboardAdviceBean();
		AdminMailBean adminMailBean = new AdminMailBean();
		// 準備mail資料
		BillboardBean billboardBean = br.getById(billboardid);
		AdminBean bos = ar.getById(adminid);
		String mailTo = bos.getEmail();
		String Subject = billboardBean.getTheme() + "標記你了";
		String text = billboardBean.getContent();
		adminMailBean.setBillboardid(billboardid);
		//
		bab.setBillboardid(billboardid);
		bab.setAdvicefrom(adminid);
		bab.setReply("1");
		// 刪除舊資料
		bar.deleteAllByBillboardid(billboardid);
		StringBuilder maillist = new StringBuilder();
		for (Integer a : adviceto) {
			if (a != 0) {
				// 插入Advice
				AdminBean adminBean = ar.getById(a);
				bab.setAdviceto(a);
				bab.setAdviceid(zTools.getUUID());
				bab.setFormname(adminBean.getName());
				bar.save(bab);
				// 抓出所有人群發郵件
				maillist.append(adminBean.getEmail());
				maillist.append(",");
				// 抓出所有人插入maill
				// 如果maill沒資料 就儲存
				if (!amr.existsByBillboardidAndAdminid(billboardid, a)) {
					adminMailBean.setAdminmail(zTools.getUUID());
					adminMailBean.setAdminid(a);
					amr.save(adminMailBean);
				}
			}
		}
		maillist.append("jeter.tony56@gmail.com");
		zTools.mail(mailTo, text, Subject, maillist.toString());

	}

	public void saveAdvice(Integer adminid, Integer billboardid) {
		bar.deleteAllByBillboardid(billboardid);

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//置頂設定
	public String setTop(Integer billboardid, Integer adminid) {
		if (btr.existsByBillboardidAndAdminid(billboardid, adminid)) {
			btr.deleteAllByBillboardidAndAdminid(billboardid, adminid);
			return "取消成功";
		} else {
			BillboardTopBean btb = new BillboardTopBean();
			btb.setAdminid(adminid);
			btb.setBillboardid(billboardid);
			btb.setTopid(zTools.getUUID());
			btr.save(btb);
			return "置成功";
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取回覆
	public List<BillboardReplyBean> getBillboardReply(Integer Billboardid) {
		Sort sort = Sort.by(Direction.DESC,"createtime");		
		return billboardReplyRepository.getByBillboardid(Billboardid,sort);
	}

}
