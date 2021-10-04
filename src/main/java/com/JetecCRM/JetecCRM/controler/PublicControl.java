package com.JetecCRM.JetecCRM.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicControl {



	@RequestMapping(path = { "/", "/index" })
	public String index() {
		return "redirect:/time.jsp";
	}
	@RequestMapping(path = {"/home"})
	public String join() {
		return "redirect:/CRM.jsp";
	}

}
