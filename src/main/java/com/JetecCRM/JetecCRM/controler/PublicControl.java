package com.JetecCRM.JetecCRM.controler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.JetecCRM.JetecCRM.Tool.ZeroTools;
import com.JetecCRM.JetecCRM.controler.service.SystemService;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;

@Controller
public class PublicControl {

	@Autowired
	AdminRepository ar ;
	@Autowired
	SystemService ss;
	@Autowired
	ZeroTools zTool;
	
	
	
	@RequestMapping(path = { "/", "/index" })
	public String index(Model model) {
		List<BillboardBean> resulet = ss.getBillboardList("發佈");
		for(BillboardBean bean : resulet) {
			bean.setEndtime(zTool.getTime(bean.getCreatetime()));
		}
		model.addAttribute("list", resulet);
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//登入
	@RequestMapping(path = {"/home"})
	public String join(@RequestParam("userName")String userName,@RequestParam("userPassword")String userPassword,HttpSession session) {
		System.out.println(userName);
		System.out.println(userPassword);		
		if(ar.existsByEmailAndPassword(userName,userPassword)) {
			System.out.println(userName+"*****登入*****");
			session.setAttribute("user", ar.findByEmailAndPassword(userName,userPassword));	
		}else {
			return "redirect:/time.jsp";
		}		
	return "redirect:/";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//登出
	@RequestMapping(path = { "/Signout"})
	public String Signout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
