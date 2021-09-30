package com.JetecCRM.JetecCRM.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JetecCRM.JetecCRM.controler.service.ClientService;
import com.JetecCRM.JetecCRM.model.ClientBean;
import com.JetecCRM.JetecCRM.model.PotentialCustomerBean;

@Controller
@RequestMapping("/CRM")
public class CustomerControler {
	@Autowired
	ClientService cs;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存客戶
	@RequestMapping("/SaveClient")
	public String SaveClient(ClientBean clientBean) {
		System.out.println("*****儲存客戶*****");
		System.out.println(clientBean);
		cs.SaveAdmin(clientBean);
		return "redirect:/CRM/ClientList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取客戶列表
	@RequestMapping("/ClientList")
	public String clientList(Model model) {
		System.out.println("*****讀取客戶列表*****");
		model.addAttribute("list", cs.getList());
		return "/client/clientList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取客戶細節
	@RequestMapping("/client/{id}")
	public String client(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取客戶細節****");
		if (id == 0) {
			model.addAttribute("bean", new ClientBean());
		} else {
			model.addAttribute("bean", cs.getById(id));
		}	
		return "/client/client";
	}
}
