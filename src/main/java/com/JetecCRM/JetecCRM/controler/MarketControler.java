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
import com.JetecCRM.JetecCRM.controler.service.MarketService;
import com.JetecCRM.JetecCRM.controler.service.PotentialCustomerService;
import com.JetecCRM.JetecCRM.model.AgreementBean;
import com.JetecCRM.JetecCRM.model.MarketBean;
import com.JetecCRM.JetecCRM.model.MarketRemarkBean;
import com.JetecCRM.JetecCRM.model.PotentialCustomerBean;
import com.JetecCRM.JetecCRM.model.QuotationBean;
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
	ClientService cs;
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
//		model.addAttribute("admin", ar.findAll());

		return "/Market/potentialcustomer";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//銷售機會列表
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//轉換
	@RequestMapping("/goQuotation.action")
	public String goQuotation(MarketBean marketBean, Model model) {
		System.out.println(marketBean);
		QuotationBean qBean = new QuotationBean();
		qBean.setName(marketBean.getClient());
		qBean.setPhone(marketBean.getContactphone());
		qBean.setContactname(marketBean.getContactname());
		qBean.setContactmoblie(marketBean.getContactmoblie());
		qBean.setRemark(marketBean.getMessage());
		qBean.setUser(marketBean.getUser());
		model.addAttribute("bean", qBean);
		return "/Market/Quotation";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存報價單
	@RequestMapping("/SaveQuotation")
	public String SaveQuotation(QuotationBean qBean) {
		System.out.println("存報價單");
		System.out.println(qBean);
		System.out.println(qBean.getQdb());

		QuotationBean save  = ms.SaveQuotation(qBean);
		return "redirect:/CRM/Quotation/" +save.getQuotationid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取報價單細節
	@RequestMapping("/Quotation/{id}")
	public String Quotation(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取報價單細節****");

		if (id == 0) {
			model.addAttribute("bean", new QuotationBean());
		} else {
			QuotationBean qBean = ms.getQuotationById(id);
			model.addAttribute("bean", qBean);
			model.addAttribute("contact", cs.selectContactByClientName(qBean.getName()));// 讀取聯絡人by客戶
		}
//model.addAttribute("admin", ar.findAll());

		return "/Market/Quotation";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//報價單列表
	@RequestMapping("/QuotationList")
	public String QuotationList(Model model) {
		model.addAttribute("list", ms.getQuotationList());
		return "/Market/QuotationList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//存合約	
	@RequestMapping("/SaveAgreement")
	public String SaveAgreement(AgreementBean aBean) {
		System.out.println("存合約");
		System.out.println(aBean);
		ms.SaveAgreement(aBean);
		return "redirect:/CRM/Agreement/1";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取合約細節
	@RequestMapping("/Agreement/{id}")
	public String Agreement(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取合約細節****");

		if (id == 0) {
			model.addAttribute("bean", new AgreementBean());
		} else {			
			model.addAttribute("bean", ms.getAgreementBeanById(id));

		}
//model.addAttribute("admin", ar.findAll());

		return "/Market/agreement";
	}

}
