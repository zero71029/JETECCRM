package com.JetecCRM.JetecCRM.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JetecCRM.JetecCRM.controler.service.SystemService;
import com.JetecCRM.JetecCRM.model.AdminBean;

@Controller
@RequestMapping("/CRM")
public class SystemControler {

	@Autowired
	SystemService ss;

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
	public String SaveAdmin(AdminBean abean) {
		System.out.println("*****儲存員工*****");
		System.out.println(abean);
		ss.SaveAdmin(abean);
		return "redirect:/CRM/adminList";
	}

}
