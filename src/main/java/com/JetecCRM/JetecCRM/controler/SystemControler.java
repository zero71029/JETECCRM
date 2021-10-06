package com.JetecCRM.JetecCRM.controler;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JetecCRM.JetecCRM.controler.service.SystemService;
import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.google.api.client.http.HttpRequest;

@Controller
@RequestMapping("/system")
public class SystemControler {

	@Autowired
	SystemService ss;
	@Autowired
	AdminRepository ar;


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
	public String SaveAdmin(AdminBean abean,HttpServletRequest req) {
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
	public String delAdmin(@RequestParam("id") List<Integer> id,HttpServletRequest sce) {
		System.out.println("*****刪除員工*****");
		ss.delAdmin(id,sce);
		
		
		
		
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
	public String SaveBillboard(BillboardBean bean) {
		System.out.println("*****儲存公佈欄*****");
		System.out.println(bean);
		ss.SaveBillboard(bean);
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
}
