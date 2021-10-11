package com.JetecCRM.JetecCRM.controler;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JetecCRM.JetecCRM.Tool.ZeroTools;
import com.JetecCRM.JetecCRM.controler.service.SystemService;
import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.model.AuthorizeBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardReplyBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.AuthorizeRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Controller
@RequestMapping("/system")
public class SystemControler {

	@Autowired
	SystemService ss;
	@Autowired
	AdminRepository ar;
	@Autowired
	BillboardRepository br;
	@Autowired
	AuthorizeRepository authorizeRepository;

	@Autowired
	ZeroTools zTools;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取員工列表
	@RequestMapping("/adminList")
	public String adminList(Model model) {
		System.out.println("*****讀取員工列表*****");
		model.addAttribute("list", ss.getAdminList());
		return "/system/adminList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取員工細節
	@RequestMapping("/adminDetail/{id}")
	public String adminDetail(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取員工細節****");
		model.addAttribute("bean", ss.getById(id));
		return "/system/admin";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存員工
	@RequestMapping("/SaveAdmin")
	public String SaveAdmin(AdminBean abean, HttpServletRequest req) {
		System.out.println("*****儲存員工*****");
		ss.SaveAdmin(abean);
		ServletContext sce = req.getServletContext();
		sce.setAttribute("admin", ar.findAll());

		return "redirect:/system/adminList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除員工
	@RequestMapping("/delAdmin")
	@ResponseBody
	public String delAdmin(@RequestParam("id") List<Integer> id, HttpServletRequest sce) {
		System.out.println("*****刪除員工*****");
		ss.delAdmin(id, sce);

		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表
	@RequestMapping("/billboardList")
	public String billboardList(Model model) {
		System.out.println("*****讀取公佈欄列表*****");
		model.addAttribute("list", ss.getBillboardList("發佈"));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表
	@RequestMapping("/OffShelf")
	public String OffShelf(Model model) {
		System.out.println("*****讀取公佈欄列表*****");
		model.addAttribute("list", ss.getBillboardList("下架"));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	@RequestMapping("/SaveBillboard")
	public String SaveBillboard(BillboardBean bean, HttpSession session) {
		System.out.println("*****儲存公佈欄*****");
		System.out.println(bean);
		ss.SaveBillboard(bean, session);

		return "redirect:/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄細節
	@RequestMapping("/billboard/{id}")
	public String billboard(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取公佈欄細節****");
		BillboardBean bean = ss.getBillboardById(id);
		bean.setContent(bean.getContent().replaceAll("<br>", "\n"));
		model.addAttribute("bean", bean);
		return "/system/billboard";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除公佈欄
	@RequestMapping("/delBillboard")
	@ResponseBody
	public String delMarket(@RequestParam("id") List<Integer> id) {
		System.out.println("*****刪除公佈欄*****");
		ss.delBillboard(id);
		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄留言
	@RequestMapping("/saveReply")
	public String saveReply(BillboardReplyBean bean) {
		System.out.println("*****儲存公佈欄留言*****");
		System.out.println(bean);
		if (ss.SaveReply(bean)) {
			BillboardBean bb = br.getById(bean.getBillboardid());
			AdminBean abean = ar.findByName(bb.getUser());

			String mailTo = abean.getEmail();
			String Subject = bean.getName() + "回覆留言";
			String text = "主題 :" + bb.getTheme() + "<br>回覆 :" + bean.getContent();
			StringBuilder maillist = new StringBuilder();
			zTools.mail(mailTo, text, Subject, maillist.toString());
		}

		return "redirect:/billboardReply/" + bean.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//公佈欄授權
	@RequestMapping("/authorize")
	@ResponseBody
	public String authorize(@RequestParam("adminid") Integer adminid) {
		System.out.println("*****公佈欄授權*****");
		String uuid = zTools.getUUID();
		if (adminid != 0) {
			AdminBean aBean = ar.getById(adminid);
			String mailTo = aBean.getEmail();
			String Subject = "公佈欄授權";
			AuthorizeBean authorizeBean = new AuthorizeBean();
			authorizeBean.setId(uuid);
			authorizeBean.setUsed(aBean.getName());
			authorizeRepository.save(authorizeBean);
			String text = String.format("<a href='http://192.168.11.114:8081/authorize/%s'>點擊鍊接留言</a>", uuid);
			String maillist = "";
			zTools.mail(mailTo, text, Subject, maillist);
		}
		return String.format("http://192.168.11.114:8081/authorize/%s", uuid);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//新增群組分類
	@RequestMapping("/addOption/{group}/{option}")
	@ResponseBody
	public String addOption(@PathVariable("group")String group,@PathVariable("option")String option,HttpServletRequest sce) {
		System.out.println("*****新增群組分類*****");
		
		ss.saveOption(group,option,sce);
		
		
		
		return"save ok";
	}

}
