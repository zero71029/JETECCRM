package com.JetecCRM.JetecCRM.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JetecCRM.JetecCRM.controler.service.MarketService;
import com.JetecCRM.JetecCRM.model.MarketBean;

@Controller
@RequestMapping("/CRM")
public class MarketControler {
	@Autowired
	MarketService ms;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/MarketList")
	public String Market(Model model) {
		model.addAttribute("list", ms.getList());
		return "/Market/MarketList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/SaveMarket")
	public String SaveMarket(MarketBean marketBean) {
		System.out.println(marketBean);
		ms.save(marketBean);
		return "redirect:/CRM/MarketList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/Market/{id}")
	public String Market(Model model,@PathVariable("id")Integer id) {
		model.addAttribute("bean",ms.getById(id));
		return "/Market/Market";
	}

}
