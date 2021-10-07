package com.JetecCRM.JetecCRM.controler;

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
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;

@Controller
public class PublicControl {

	@Autowired
	AdminRepository ar;
	@Autowired
	SystemService ss;
	@Autowired
	ZeroTools zTool;

	@RequestMapping(path = { "/", "/index" })
	public String index(Model model) {
//		List<BillboardBean> resulet = ss.getBillboardList("發佈");

		model.addAttribute("list", ss.getBillboardList("發佈"));
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//登入
	@RequestMapping(path = { "/home" })
	public String join(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
			HttpSession session) {
		if (ar.existsByEmailAndPassword(userName, userPassword)) {
			System.out.println(userName + "*****登入*****");
			session.setAttribute("user", ar.findByEmailAndPassword(userName, userPassword));
		} else {
			return "redirect:/time.jsp";
		}
		return "redirect:/";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//登出
	@RequestMapping(path = { "/Signout" })
	public String Signout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//點擊已讀
	@RequestMapping("/read/{billboardid}/{username}")
	@ResponseBody
	public String read(@PathVariable("billboardid") Integer billboardid, @PathVariable("username") String username) {
		System.out.println("*****點擊已讀*****");

		return ss.saveRead(billboardid, username);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//取消已讀
	@RequestMapping("/ReRead/{billboardid}/{username}")
	public String ReRead(@PathVariable("billboardid") Integer billboardid, @PathVariable("username") String username) {
		System.out.println("*****點擊已讀*****");
		ss.ReRead(billboardid, username);
		return "redirect:/";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//進入公佈欄留言頁面
	@RequestMapping("/billboardReply/{id}")
	public String billboardReply(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取公佈欄細節****");
		BillboardBean bean = ss.getBillboardById(id);
//		bean.setContent(bean.getContent().replaceAll("<br>", "\n"));
		model.addAttribute("bean", bean);
		return "/system/billboardReply";
	}

}
