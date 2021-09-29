package com.JetecCRM.JetecCRM.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JetecCRM.JetecCRM.controler.service.MarketService;
import com.JetecCRM.JetecCRM.controler.service.PotentialCustomerService;
import com.JetecCRM.JetecCRM.model.MarketBean;
import com.JetecCRM.JetecCRM.model.MarketRemarkBean;
import com.JetecCRM.JetecCRM.model.PotentialCustomerBean;
import com.JetecCRM.JetecCRM.model.TrackBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;

@Controller
@RequestMapping("/CRM")
public class MarketControler {
	@Autowired
	MarketService ms;
	@Autowired
	PotentialCustomerService PCS;
	@Autowired
	AdminRepository ar;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/SavePotentialCustomer")
	public String SavePotentialCustomer(PotentialCustomerBean pcb) {
		System.out.println("*****儲存潛在客戶*****");
		System.out.println(pcb);
		PCS.SavePotentialCustomer(pcb);
		return "redirect:/CRM/PotentialCustomerList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取潛在客戶列表
	@RequestMapping("/PotentialCustomerList")
	public String clientList(Model model) {
		System.out.println("*****讀取潛在客戶列表*****");
		model.addAttribute("list", PCS.getList());
		return "/Market/potentialcustomerList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	讀取潛在客戶細節
	@RequestMapping("/potentialcustomer/{id}")
	public String potentialcustomer(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取潛在客戶細節****");
		if (id == 0) {
			model.addAttribute("bean", new PotentialCustomerBean());
		} else {
			model.addAttribute("bean", PCS.getById(id));
		}
		model.addAttribute("admin", ar.findAll());

		return "/Market/potentialcustomer";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//銷售機會
	@RequestMapping("/MarketList")
	public String Market(Model model) {
		model.addAttribute("list", ms.getList());
		return "/Market/MarketList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 存銷售機會
	@RequestMapping("/SaveMarket")
	public String SaveMarket(MarketBean marketBean) {
		System.out.println(marketBean);
		ms.save(marketBean);
		return "redirect:/CRM/MarketList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/Market/{id}")
	public String Market(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("bean", ms.getById(id));
		return "/Market/Market";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存備註
	@RequestMapping("/SaveRemark")
	public String SaveRemark(MarketRemarkBean mrb) {
		System.out.println("存備註");
		ms.SaveRemark(mrb);
		return "redirect:/CRM/Market/" + mrb.getMarketid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除銷售機會
	@RequestMapping("/delMarket")
	@ResponseBody
	public String delMarket(@RequestParam("id") List<Integer> id) {
		System.out.println("*****刪除銷售機會*****");
		System.out.println(id.toString());
		ms.delMarket(id);
		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/selectMarket")
	// 搜索銷售機會
	public String selectMarket(Model model, @RequestParam("name") String name) {
		System.out.println("搜索銷售機會");
		model.addAttribute("list", ms.selectMarket(name));
		return "/Market/MarketList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除備註
	@RequestMapping("/delRemark/{remarkId}/{MarketId}")
	public String delRemark(@PathVariable("remarkId") Integer remarkId, @PathVariable("MarketId") Integer MarketId) {
		System.out.println("*****刪除備註*****");
		ms.delRemark(remarkId);
		return "redirect:/CRM/Market/" + MarketId;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存追蹤	
	@RequestMapping("/SaveTrack")
	public String SaveTrack(TrackBean trackBean) {
		System.out.println("存追蹤");
		ms.SaveTrack(trackBean);
		return "redirect:/CRM/potentialcustomer/" + trackBean.getCustomerid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除潛在客戶
	@RequestMapping("/delPotentialCustomer")
	@ResponseBody
	public String delPotentialCustomer(@RequestParam("id") List<Integer> id) {
		System.out.println("*****刪除潛在客戶*****");
		System.out.println(id.toString());
		PCS.delPotentialCustomer(id);
		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 搜索潛在客戶
	@RequestMapping("/selectPotentialCustomer")
	public String selectPotentialCustomer(Model model, @RequestParam("name") String name) {
		System.out.println("搜索潛在客戶");
		model.addAttribute("list", PCS.selectPotentialCustomer(name));
		return "/Market/potentialcustomerList";
	}

}
