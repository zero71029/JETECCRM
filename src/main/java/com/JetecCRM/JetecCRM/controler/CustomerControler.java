package com.JetecCRM.JetecCRM.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JetecCRM.JetecCRM.controler.service.ClientService;
import com.JetecCRM.JetecCRM.model.ClientBean;
import com.JetecCRM.JetecCRM.model.ContactBean;
import com.JetecCRM.JetecCRM.model.MarketBean;

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
			model.addAttribute("market", new MarketBean());
		} else {
			ClientBean cb =cs.getById(id);
			model.addAttribute("bean", cb);
			model.addAttribute("market", cs.getMarketListByClient(cb.getName()));
		}
		

		return "/client/client";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 搜索客戶
	@RequestMapping("/selectclient")
	public String selectclient(Model model, @RequestParam("name") String name) {
		System.out.println("搜索客戶");
		model.addAttribute("list", cs.selectclient(name));
		return "/client/clientList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取聯絡人by名稱 ajax
	@RequestMapping("/selectContactByClientName/{name}")
	@ResponseBody
	public List<ContactBean> selectContactByClientName(@PathVariable("name") String name) {

		return cs.selectContactByClientName(name);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存聯絡人
	@RequestMapping("/SaveContact")
	public String SaveContact(ContactBean contactBean) {
		System.out.println("*****儲存客戶*****");
		System.out.println(contactBean);
		cs.SaveContact(contactBean);
		return "redirect:/CRM/ContactList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取聯絡人列表
	@RequestMapping("/ContactList")
	public String ContactList(Model model) {
		System.out.println("*****讀取聯絡人列表*****");
		model.addAttribute("list", cs.getContactList());
		return "/client/contactList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取聯絡人細節
	@RequestMapping("/contact/{id}")
	public String contact(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取聯絡人細節****");
		if (id == 0) {
			model.addAttribute("bean", new ContactBean());
		} else {
			model.addAttribute("bean", cs.getContactById(id));
		}
		return "/client/contact";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除聯絡人
	@RequestMapping("/delcontact")
	@ResponseBody
	public String delcontact(@RequestParam("id") List<Integer> id) {
		System.out.println("*****刪除聯絡人*****");
		cs.delMarket(id);
		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 搜索聯絡人
	@RequestMapping("/selectContact")
	public String sselectContact(Model model, @RequestParam("name") String name) {
		System.out.println("搜索聯絡人");
		model.addAttribute("list", cs.selectContact(name));
		return "/client/contactList";
	}

}
