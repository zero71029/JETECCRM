package com.JetecCRM.JetecCRM.controler;

import java.io.File;
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
import com.JetecCRM.JetecCRM.model.BillboardAdviceBean;
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
		public String index() {
			return "redirect:/billboard?pag=1";
			
		}
	
	
	
	//主頁面
	@RequestMapping(path = { "billboard" })
	public String billboard(Model model, HttpSession session, @RequestParam("pag") Integer pag) {
		System.out.println("*****主頁面*****");
		List<BillboardBean> advice = new ArrayList<BillboardBean>();
		List<BillboardBean> unread = new ArrayList<BillboardBean>();		
		
		
		// 抓取登入者
		AdminBean user = (AdminBean) session.getAttribute("user");
		// 如果有登入者 更新資料
		if (user != null) {
			// 抓被@的資料	
			AdminBean adminBean = ar.getById(user.getAdminid()); 
			List<BillboardAdviceBean> a = adminBean.getAdvice();
			for (BillboardAdviceBean bean : a) {
				if(bean.getReply().equals("1"))
					advice.add(br.getById(bean.getBillboardid()));
			}
			// 抓被未讀的資料				
			List<AdminMailBean> mail = adminBean.getMail();
			for (AdminMailBean bean : mail) {				
				unread.add(br.getById(bean.getBillboardid()));
			}
			//
			model.addAttribute("list", ss.getBillboardList("發佈",adminBean,pag));
			session.setAttribute("user", adminBean);
			model.addAttribute("advice", advice);
			model.addAttribute("unread", unread);
		}else {
			AdminBean xxx =null;
			model.addAttribute("list", ss.getBillboardList("發佈",xxx,pag));
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
		String result = ss.saveRead(billboardid, adminid);

		session.setAttribute("user", ar.getById(adminid));
		return result;
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
		model.addAttribute("reply", ss.getBillboardReply(id));
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
	public String saveAuthorize(@PathVariable("uuid") String uuid, BillboardBean bean, HttpSession session,Model model) {
		System.out.println("*****儲存授權*****");

		BillboardBean save = ss.SaveBillboard(bean, session);
		List<BillboardFileBean> list = bfr.findByAuthorize(uuid);
		for (BillboardFileBean b : list) {
			b.setBillboardid(save.getBillboardid());
			bfr.save(b);
		}
			authorizeRepository.deleteById(uuid);

		return "redirect:/billboardReply/" + save.getBillboardid();
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
	public String SaveAdmin(AdminBean abean, HttpServletRequest req,HttpSession session) {
		System.out.println("*****儲存員工*****");
		

		String save = ss.SaveAdmin(abean);
		ServletContext sce = req.getServletContext();
		sce.setAttribute("admin", ar.findAll());
		AdminBean user = (AdminBean) session.getAttribute("user");
		if( user.getPosition().equals("系統")) return "儲存成功,<a href='/CRM/system/adminList'>返回</a>";
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
					
					
					
					// 獲取Tomcat伺服器所在的路徑
					String tomcat_path = System.getProperty( "user.dir" );
					System.out.println("Tomcat伺服器所在的路徑: "+tomcat_path);
					// 獲取Tomcat伺服器所在路徑的最後一個檔案目錄
					String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("/")+1,tomcat_path.length());
					System.out.println("Tomcat伺服器所在路徑的最後一個檔案目錄: "+bin_path);
					// 判斷最後一個檔案目錄是否為bin目錄					
					if(("bin").equals(bin_path)){ 
						// 獲取儲存上傳圖片的檔案路徑
						String pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("/")) +"/webapps/CRM"+"/file/";
						fileMap.get("file" + i).transferTo( new File(pic_path +fileMap.get("file" + i).getOriginalFilename()));
						System.out.println(pic_path +fileMap.get("file" + i).getOriginalFilename());
					}else{					
						String path2 = "C:\\Users\\Rong\\Desktop\\apache-tomcat-9.0.53\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\";
						fileMap.get("file" + i).transferTo( new File(path2 +fileMap.get("file" + i).getOriginalFilename()));
						System.out.println(path2 +fileMap.get("file" + i).getOriginalFilename());
					}
					
					
//3. 儲存檔案名稱到資料庫
					BillboardFileBean billBoardFileBean = new BillboardFileBean();
					billBoardFileBean.setBillboardid(0);
					billBoardFileBean.setAuthorize(authorizeId);
					billBoardFileBean.setFileid(uuid);
					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
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
		} catch (Exception e) {
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
	@RequestMapping("/top/{billboardid}/{adminid}")
	@ResponseBody
	public String billboardid(@PathVariable("billboardid") Integer billboardid,@PathVariable("adminid") Integer adminid ,HttpSession session)  {
		System.out.println("*****置頂設定*****");
		String result =ss.setTop(billboardid,adminid);
		session.setAttribute("user", ar.getById(adminid));
		return result;
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
		Integer Billboardid = ss.delReply(replyId);
		return "redirect:/billboardReply/" + Billboardid;
	}

}
