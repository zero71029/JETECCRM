package com.JetecCRM.JetecCRM.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.JetecCRM.JetecCRM.Tool.ZeroTools;

@Controller
public class PublicControl {

	@Autowired
	private ZeroTools zTools;

	@RequestMapping(path = { "/", "/index" })
	public String index() {
		return "redirect:/time.jsp";
	}
	@RequestMapping(path = {"/home"})
	public String join() {
		return "redirect:/CRM.jsp";
	}

}
