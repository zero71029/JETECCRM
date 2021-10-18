package com.JetecCRM.JetecCRM.controler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import com.JetecCRM.JetecCRM.model.AdminMailBean;
import com.JetecCRM.JetecCRM.model.AuthorizeBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardFileBean;
import com.JetecCRM.JetecCRM.model.BillboardReplyBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.AuthorizeRepository;
import com.JetecCRM.JetecCRM.repository.BillboardFileRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;

@Controller
public class PublicControl {

	@Autowired
	AdminRepository ar;
	@Autowired
	SystemService ss;
	@Autowired
	ZeroTools zTools;
	@Autowired
	AuthorizeRepository authorizeRepository;
	@Autowired
	BillboardRepository br;
	@Autowired
	BillboardFileRepository bfr;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//主頁面
	@RequestMapping(path = { "/", "/index" })
	public String index(Model model, HttpSession session) {
		System.out.println("*****主頁面*****");
		List<String> unread = new ArrayList<String>();
		model.addAttribute("list", ss.getBillboardList("發佈"));
		AdminBean user = (AdminBean) session.getAttribute("user");

		if (user != null) {
			AdminBean adminBean = ar.getById(user.getAdminid());

			List<AdminMailBean> a = adminBean.getMail();
			for (AdminMailBean bean : a) {
				unread.add(br.getById(bean.getBillboardid()).getContent());
			}
			model.addAttribute("unread", unread);
		}
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
	@RequestMapping("/read/{billboardid}/{adminid}")
	@ResponseBody
	public String read(@PathVariable("billboardid") Integer billboardid, @PathVariable("adminid") Integer adminid,
			HttpSession session) {
		System.out.println("*****點擊已讀*****");
		session.setAttribute("user", ar.getById(adminid));
		return ss.saveRead(billboardid, adminid);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//取消已讀
	@RequestMapping("/ReRead/{billboardid}/{adminid}")
	public String ReRead(@PathVariable("billboardid") Integer billboardid, @PathVariable("adminid") Integer adminid,
			HttpSession session) {
		System.out.println("*****取消已讀*****");
		ss.ReRead(billboardid, adminid);
		session.setAttribute("user", ar.getById(adminid));
		return "redirect:/billboardReply/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//進入公佈欄留言頁面
	@RequestMapping("/billboardReply/{id}")
	public String billboardReply(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取公佈欄細節****");
		model.addAttribute("bean", ss.getBillboardById(id));
		model.addAttribute("news", ss.getBillboardByTime());
		return "/system/billboardReply";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//授權的公佈欄
	@RequestMapping("/authorize/{uuid}")
	public String authorize(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		System.out.println("*****授權的公佈欄****");
		if (authorizeRepository.existsById(uuid)) {
			AuthorizeBean authorizeBean = authorizeRepository.getById(uuid);
			AdminBean user = (AdminBean) session.getAttribute("user");
			if (user == null)
				return "redirect:/CRM.jsp?mess=2";
			if (user.getName().equals(authorizeBean.getUsed()) || "所有人".equals(authorizeBean.getUsed())) {
				model.addAttribute("authorizeBean", authorizeBean);
				return "/authorize";
			}
		}
		return "redirect:/CRM.jsp?mess=3";

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 儲存授權
	@RequestMapping("/saveAuthorize/{uuid}")
	public String saveAuthorize(@PathVariable("uuid") String uuid, BillboardBean bean, HttpSession session) {
		System.out.println("*****儲存授權*****");

		BillboardBean save = ss.SaveBillboard(bean, session);
		List<BillboardFileBean> list = bfr.findByAuthorize(uuid);
		for (BillboardFileBean b : list) {
			b.setBillboardid(save.getBillboardid());
			bfr.save(b);
		}
//			authorizeRepository.deleteById(uuid);

		return "redirect:/";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表  依分類
	@RequestMapping("/selectBillboardGroup/{billboardgroupid}")
	public String selectBillboardGroup(Model model, @PathVariable("billboardgroupid") String billboardgroupid) {
//		List<BillboardBean> resulet = ss.getBillboardList("發佈");
		System.out.println("*****讀取公佈欄列表*****");
		model.addAttribute("list", ss.getBillboardList("發佈", billboardgroupid));
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//搜索公布欄
	@RequestMapping("/selectBillboard")
	public String selectMarket(Model model, @RequestParam("search") String search) {
		System.out.println("搜索公布欄");
		model.addAttribute("list", ss.selectBillboardt(search));
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存員工
	@RequestMapping("/SaveAdmin")
	@ResponseBody
	public String SaveAdmin(AdminBean abean, HttpServletRequest req) {
		System.out.println("*****儲存員工*****");
		String save = ss.SaveAdmin(abean);
		ServletContext sce = req.getServletContext();
		sce.setAttribute("admin", ar.findAll());
		return save;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//上傳型錄
	@RequestMapping("/upFile/{authorizeId}")
	@ResponseBody
	public String upFile(MultipartHttpServletRequest multipartRequest,
			@PathVariable("authorizeId") String authorizeId) {
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
					billBoardFileBean.setBillboardid(0);
					billBoardFileBean.setAuthorize(authorizeId);
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//要求附件
	@RequestMapping("/selectFile/{authorizeId}")
	@ResponseBody
	public List<BillboardFileBean> selectFile(@PathVariable("authorizeId") String authorizeId) {
		System.out.println("*****要求附件*****");
		return bfr.findByAuthorize(authorizeId);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除型錄
	@RequestMapping("/remove/{fileId}")
	public String removefile(@PathVariable("fileId") String fileId) {
		BillboardFileBean billBoardFileBean = bfr.getById(fileId);
		File file = new File("E:\\JetecCRM\\src\\main\\resources\\static\\file\\" + billBoardFileBean.getUrl());
		System.out.println(file.delete());
		bfr.delete(billBoardFileBean);
		return "redirect:/authorize/" + billBoardFileBean.getAuthorize();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//置頂設定
	@RequestMapping("/top/{billboardid}")
	@ResponseBody
	public String billboardid(@PathVariable("billboardid") Integer billboardid) {
		System.out.println("*****置頂設定*****");
		BillboardBean bean = br.getById(billboardid);
		if (bean.getTop().equals("置頂")) {
			bean.setTop("");
			br.save(bean);
			return "取消成功";
		}
		bean.setTop("置頂");
		br.save(bean);
		return "置頂成功";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄留言
	@RequestMapping("/saveReply")
	public String saveReply(BillboardReplyBean bean) {
		System.out.println("*****儲存公佈欄留言*****");
		if (ss.SaveReply(bean)) {
			BillboardBean bb = br.getById(bean.getBillboardid());
			AdminBean abean = ar.findByName(bb.getUser());
			String mailTo = abean.getEmail();
			String Subject = bean.getName() + "回覆留言";
			String text = "主題 :" + bb.getTheme() + "<br>回覆 :" + bean.getContent();
			StringBuilder maillist = new StringBuilder();
			zTools.mail(mailTo, text, Subject, maillist.toString());
		}
		return "redirect:/billboardReply/" + bean.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//修改留言
	@RequestMapping("/replyChange")
	public String replyChange(BillboardReplyBean bean) {
		System.out.println("*****修改留言*****");
		if (ss.SaveReply(bean)) {

		}
		return "redirect:/billboardReply/" + bean.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除留言
	@RequestMapping("/replyRemove/{replyId}")
	public String replyRemove(@PathVariable("replyId") String replyId) {
		System.out.println("*****刪除留言*****");
		Integer Billboardid =  ss.delReply(replyId);

		
		return "redirect:/billboardReply/" + Billboardid;
	}

}
