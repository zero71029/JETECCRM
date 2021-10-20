package com.JetecCRM.JetecCRM.controler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.JetecCRM.JetecCRM.Tool.ZeroTools;
import com.JetecCRM.JetecCRM.controler.service.SystemService;
import com.JetecCRM.JetecCRM.model.AdminBean;
import com.JetecCRM.JetecCRM.model.AuthorizeBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardFileBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.AuthorizeRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Controller
@RequestMapping("/system")
public class SystemControler {

	@Autowired
	SystemService ss;
	@Autowired
	AdminRepository ar;
	@Autowired
	BillboardRepository br;
	@Autowired
	AuthorizeRepository authorizeRepository;

	@Autowired
	ZeroTools zTools;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取員工列表
	@RequestMapping("/adminList")
	public String adminList(Model model) {
		System.out.println("*****讀取員工列表*****");
		model.addAttribute("list", ss.getAdminList());
		return "/system/adminList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取員工細節
	@RequestMapping("/adminDetail/{id}")
	public String adminDetail(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取員工細節****");
		model.addAttribute("bean", ss.getById(id));
		return "/system/admin";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除員工
	@RequestMapping("/delAdmin")
	@ResponseBody
	public String delAdmin(@RequestParam("id") List<Integer> id, HttpServletRequest sce) {
		System.out.println("*****刪除員工*****");
		ss.delAdmin(id, sce);

		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表
	@RequestMapping("/billboardList")
	public String billboardList(Model model, HttpSession session) {
		System.out.println("*****讀取公佈欄列表*****");
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		model.addAttribute("list", ss.getBillboardList("發佈", adminBean));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表
	@RequestMapping("/OffShelf")
	public String OffShelf(Model model, HttpSession session) {
		System.out.println("*****讀取公佈欄列表*****");
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		model.addAttribute("list", ss.getBillboardList("封存", adminBean));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	@RequestMapping("/SaveBillboard")
	public String SaveBillboard(BillboardBean bean, HttpSession session) {
		System.out.println("*****儲存公佈欄*****");
		ss.SaveBillboard(bean, session);
		return "redirect:/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄細節
	@RequestMapping("/billboard/{id}")
	public String billboard(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取公佈欄細節****");
		BillboardBean bean = ss.getBillboardById(id);
		bean.setContent(bean.getContent().replaceAll("<br>", "\n"));
		model.addAttribute("bean", bean);
		return "/system/billboard";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除公佈欄
	@RequestMapping("/delBillboard")
	@ResponseBody
	public String delMarket(@RequestParam("id") List<Integer> id) {
		System.out.println("*****刪除公佈欄*****");
		ss.delBillboard(id);
		return "刪除成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//公佈欄授權
	@RequestMapping("/authorize")
	@ResponseBody
	public String authorize(@RequestParam("adminid") Integer adminid) {
		System.out.println("*****公佈欄授權*****");
		String uuid = zTools.getUUID();
		if (adminid != 0) {
			AdminBean aBean = ar.getById(adminid);
			String mailTo = aBean.getEmail();
			String Subject = "公佈欄授權";
			AuthorizeBean authorizeBean = new AuthorizeBean();
			authorizeBean.setId(uuid);
			authorizeBean.setUsed(aBean.getName());
			authorizeRepository.save(authorizeBean);
			String text = String.format("<a href='http://192.168.11.114:8081/authorize/%s'>點擊鍊接留言</a>", uuid);
			String maillist = "";
			zTools.mail(mailTo, text, Subject, maillist);
		} else {
			AuthorizeBean authorizeBean = new AuthorizeBean();
			authorizeBean.setId(uuid);
			authorizeBean.setUsed("所有人");
			authorizeRepository.save(authorizeBean);
		}
		return String.format("http://192.168.11.114:8081/authorize/%s", uuid);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//新增群組子項
	@RequestMapping("/addOption/{group}/{option}")
	@ResponseBody
	public String addOption(@PathVariable("group") String group, @PathVariable("option") String option,
			HttpServletRequest sce) {
		System.out.println("*****新增群組子項*****");
		String resuly = ss.saveOption(group, option);
		ss.updataOption(sce);
		return resuly;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除群組子項
	@RequestMapping("/delOption/{group}/{option}")
	@ResponseBody
	public String delOption(@PathVariable("group") String group, @PathVariable("option") String option,
			HttpServletRequest sce) {
		System.out.println("*****刪除群組子項*****");
		if (option.equals("全部"))
			return "全部 不能刪除";
		String result = ss.delOption(group, option);
		ss.updataOption(sce);
		return result;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//上傳型錄
	@RequestMapping("/upFile/{billboardid}")
	@ResponseBody
	public String upFile(MultipartHttpServletRequest multipartRequest, @PathVariable("billboardid") Integer billboardid,
			Model model) {
		System.out.println("*****上傳型錄*****");
		String uuid = zTools.getUUID();
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		System.out.println("fileMap " + fileMap);
//圖片儲存
		try {
			for (int i = 0; i <= fileMap.size(); i++) {
//2. 儲存圖片到資料夾
				if (fileMap.get("file" + i) != null) {
					System.out.println(fileMap.get("file" + i).getOriginalFilename());
//改名+存檔
					String lastname = fileMap.get("file" + i).getOriginalFilename()
							.substring(fileMap.get("file" + i).getOriginalFilename().indexOf("."));
					System.out.println(lastname);
					fileMap.get("file" + i).transferTo(
							new File("E:\\JetecCRM\\src\\main\\resources\\static\\file\\" + uuid + lastname));
//fileMap.get("file" + i).transferTo(new File("classpath:/resources/static\\images\\product\\" + Productmodel + ".jpg"));
//3. 儲存檔案名稱到資料庫
					BillboardFileBean billBoardFileBean = new BillboardFileBean();
					billBoardFileBean.setBillboardid(billboardid);
					billBoardFileBean.setFileid(uuid);
					billBoardFileBean.setUrl(uuid + lastname);
					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
					ss.saveUrl(billBoardFileBean);

//ProductPictureBean pBean = productPictureJpaReposit.findProducturl(Productmodel + "-" + i);
//if (pBean == null) {
//pBean = new ProductPictureBean();
//}
//pBean.setProducturl(Productmodel + "-" + i);
//pBean.setProductid(Productid);
//productPictureJpaReposit.save(pBean);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "儲存失敗";
		}
		return "上傳成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除型錄
	@RequestMapping("/remove/{fileid}/{billboardid}")
	public String remove(@PathVariable("fileid") String fileid, @PathVariable("billboardid") String billboardid) {
		System.out.println("*****刪除型錄*****");
		ss.removefile(fileid);
		return "redirect:/system/billboard/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//@他人
	@RequestMapping("/advice/{adminid}/{billboardid}")
	public String advice(@RequestParam("adviceto") Integer[] adviceto, @PathVariable("adminid") Integer adminid,
			@PathVariable("billboardid") Integer billboardid) {
		System.out.println("*****@他人*****");
		ss.saveAdvice(adviceto, adminid, billboardid);
		return "redirect:/system/billboard/" + billboardid;
	}
}
