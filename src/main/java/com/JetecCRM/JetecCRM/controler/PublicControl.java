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
import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.model.AuthorizeBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.AuthorizeRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Controller
public class PublicControl {

	@Autowired
	AdminRepository ar;
	@Autowired
	SystemService ss;
	@Autowired
	ZeroTools zTool;
	@Autowired
	AuthorizeRepository authorizeRepository;
	@Autowired
	BillboardRepository br;

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
		return "redirect:/billboardReply/"+billboardid ;
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//公佈欄授權
	@RequestMapping("/authorize/{uuid}")
	public String authorize(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		if (authorizeRepository.existsById(uuid)) {
			AuthorizeBean authorizeBean = authorizeRepository.getById(uuid);
			AdminBean user = (AdminBean) session.getAttribute("user");
			if (user.getName().equals(authorizeBean.getUsed())) {
				model.addAttribute("authorizeBean", authorizeBean);
				return "/system/billboard";
			}
		}
		return "";

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 儲存授權
	@RequestMapping("/saveAuthorize/{uuid}")
	public String saveAuthorize(Model model, @PathVariable("uuid") String uuid, BillboardBean bean,HttpSession session) {
		System.out.println("*****儲存授權*****");
		if(ss.SaveBillboard(bean,session)) {
			authorizeRepository.deleteById(uuid);
		};		
		return "redirect:/";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表  依分類
	@RequestMapping("/selectBillboardGroup/{billboardgroupid}")
	public String selectBillboardGroup(Model model,@PathVariable("billboardgroupid") String billboardgroupid) {
//		List<BillboardBean> resulet = ss.getBillboardList("發佈");
		System.out.println("*****讀取公佈欄列表*****");
		model.addAttribute("list", ss.getBillboardList("發佈",billboardgroupid));
		return "/CRM";
	}	
	
}
