package com.JetecCRM.JetecCRM.controler;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import com.JetecCRM.JetecCRM.model.ReplyAdviceBbean;
import com.JetecCRM.JetecCRM.model.ReplyFileBean;
import com.JetecCRM.JetecCRM.model.ReplyreplyBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.AuthorizeRepository;
import com.JetecCRM.JetecCRM.repository.BillboardFileRepository;
import com.JetecCRM.JetecCRM.repository.BillboardReplyRepository;
import com.JetecCRM.JetecCRM.repository.BillboardRepository;
import com.JetecCRM.JetecCRM.repository.ReplyAdviceRepository;
import com.JetecCRM.JetecCRM.repository.ReplyFileRepository;

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
	@Autowired
	BillboardReplyRepository brr;
	@Autowired
	ReplyAdviceRepository rar;
	@Autowired
	ReplyFileRepository rfr;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 主頁面
	@RequestMapping(path = { "/", "/index" })
	public String index() {
		return "redirect:/billboard?pag=1&sort=createtime";

	}

	// 主頁面
	@RequestMapping(path = { "billboard" })
	public String billboard(Model model, HttpSession session, @RequestParam("pag") Integer pag,
			@RequestParam("sort") String sortString) {
		System.out.println("*****主頁面*****");
		List<BillboardBean> advice = new ArrayList<BillboardBean>();
		List<BillboardBean> unread = new ArrayList<BillboardBean>();

		// 分頁
		if (pag < 1)
			pag = 1;
		pag--;
		Sort sort = Sort.by(Direction.DESC, sortString);
		Pageable p = (Pageable) PageRequest.of(pag, 30, sort);

		Page<BillboardBean> page = (Page<BillboardBean>) br.getByStateAndTop("公開", "", p);
//		全部有幾頁
		model.addAttribute("TotalPages", page.getTotalPages());

		// 抓取登入者
		AdminBean user = (AdminBean) session.getAttribute("user");
		// 如果有登入者 更新資料
		if (user != null) {
			// 抓被@的資料
			AdminBean adminBean = ar.getById(user.getAdminid());
			List<BillboardAdviceBean> a = adminBean.getAdvice();
			for (BillboardAdviceBean bean : a) {
				if (bean.getReply().equals("1"))
					advice.add(br.getById(bean.getBillboardid()));
			}
			// 抓被未讀的資料
			List<AdminMailBean> mail = adminBean.getMail();
			for (AdminMailBean bean : mail) {
				unread.add(br.getById(bean.getBillboardid()));
			}

			//
			model.addAttribute("list", ss.getBillboardList("公開", adminBean, pag, sort));
			session.setAttribute("user", adminBean);
			model.addAttribute("advice", advice);// 抓被@的資料
			model.addAttribute("unread", unread);// 抓被未讀的資料
		} else {
			AdminBean xxx = null;
			model.addAttribute("list", ss.getBillboardList("公開", xxx, pag, sort));
		}
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表  依分類
	@RequestMapping("/selectBillboardGroup/{billboardgroupid}")
	public String selectBillboardGroup(Model model, @PathVariable("billboardgroupid") String billboardgroupid) {
//List<BillboardBean> resulet = ss.getBillboardList("公開");
		System.out.println("*****讀取公佈欄列表*****");
		model.addAttribute("list", ss.getBillboardList("公開", billboardgroupid));
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
	public String billboardReply(Model model, @PathVariable("id") Integer id, HttpSession session) {
		System.out.println("*****讀取公佈欄細節****");
		//上傳檔案用
		model.addAttribute("uuid",zTools.getUUID());
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		// 讀取公佈欄細節 如果有登入就已讀
		model.addAttribute("bean", ss.getBillboardById(id, adminBean));
		// 如果有登入 更新資料
		if (adminBean != null)
			session.setAttribute("user", ar.getById(adminBean.getAdminid()));
		// 讀取2星期內的訊息
		model.addAttribute("news", ss.getBillboardByTime());
		// 讀取回覆
		model.addAttribute("reply", ss.getBillboardReply(id));
		return "/system/billboardReply";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//進入授權
	@RequestMapping("/authorize/{uuid}")
	public String authorize(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		System.out.println("*****進入授權****");
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
		bean.setRemark("(被授權)");
		bean.setUser(bean.getUser());
		// 存檔
		BillboardBean save = ss.SaveBillboard(bean, session);
		// 附件處理
		List<BillboardFileBean> list = bfr.findByAuthorize(uuid);
		for (BillboardFileBean b : list) {
			b.setBillboardid(save.getBillboardid());
			bfr.save(b);
		}
		// 刪除授權
		authorizeRepository.deleteById(uuid);

		return "redirect:/billboardReply/" + save.getBillboardid();
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
	public String SaveAdmin(AdminBean abean, HttpServletRequest req, HttpSession session) {
		System.out.println("*****儲存員工*****");

		String save = ss.SaveAdmin(abean);
		ServletContext sce = req.getServletContext();
		sce.setAttribute("admin", ar.findAll());
		AdminBean user = (AdminBean) session.getAttribute("user");
		if (user != null)
			if (user.getPosition().equals("系統") || user.getPosition().equals("主管"))
				return "儲存成功,<a href='/CRM/system/adminList/adminid'>返回</a>";
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
//					讀取檔眳
					System.out.println(fileMap.get("file" + i).getOriginalFilename());
//					讀取副檔名
					String lastname = fileMap.get("file" + i).getOriginalFilename()
							.substring(fileMap.get("file" + i).getOriginalFilename().indexOf("."));
					System.out.println(lastname);
					// 獲取Tomcat伺服器所在的路徑
					String tomcat_path = System.getProperty("user.dir");
					System.out.println("Tomcat伺服器所在的路徑: " + tomcat_path);
					// 獲取Tomcat伺服器所在路徑的最後一個檔案目錄
					String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\") + 1, tomcat_path.length());
					System.out.println("Tomcat伺服器所在路徑的最後一個檔案目錄: " + bin_path);
					System.out.println("bin_path == " + bin_path);
					String path2 = "C:/CRMfile/" + fileMap.get("file" + i).getOriginalFilename();

					String path3 = "C:\\Users\\Rong\\Desktop\\tomcat-9.0.41\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\"
							+ fileMap.get("file" + i).getOriginalFilename();
					// 檔案輸出
					System.out.println("檔案輸出到" + path2);
					fileMap.get("file" + i).transferTo(new File(path2));
					System.out.println("輸出成功");
					// 檔案複製
					String pic_path = null;
					try {
						// 判斷最後一個檔案目錄是否為bin目錄
						if (("bin").equals(bin_path)) {
							System.out.println("binbinbinbinbinbinbinbinbinbinbinbin");
							// 獲取儲存上傳圖片的檔案路徑
							pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
									+ "/webapps/CRM/WEB-INF/classes/static/file/";
							// 列印路徑
							System.out.println(pic_path + fileMap.get("file" + i).getOriginalFilename());
							File source = new File(path3);
							File dest = new File(pic_path + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("複製成功");
						} else {
							File source = new File(path2);
							File dest = new File(path3);
							System.out.println(path2 + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("複製成功");
						}

					} catch (Exception e) {
						System.out.println("複製失敗");
					}

//3. 儲存檔案名稱到資料庫
					BillboardFileBean billBoardFileBean = new BillboardFileBean();
					billBoardFileBean.setBillboardid(0);
					billBoardFileBean.setAuthorize(authorizeId);
					billBoardFileBean.setFileid(uuid);
//					billBoardFileBean.setUrl(uuid + lastname); // 使用uuid建檔名
					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
					ss.saveUrl(billBoardFileBean);
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
		System.out.println("*****要求附件*****" + authorizeId);
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
	public String billboardid(@PathVariable("billboardid") Integer billboardid,
			@PathVariable("adminid") Integer adminid, HttpSession session) {
		System.out.println("*****置頂設定*****");
		String result = ss.setTop(billboardid, adminid);
		session.setAttribute("user", ar.getById(adminid));
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄留言
	@RequestMapping("/saveReply/{fileuuid}")
	public String saveReply(@PathVariable("fileuuid")String fileuuid, BillboardReplyBean bean, HttpSession session) {
		System.out.println("*****儲存公佈欄留言*****");
		// 儲存留言
		BillboardReplyBean save =ss.SaveReply(bean);
			
			// 附件處理
			List<ReplyFileBean> list = rfr.findByAuthorize(fileuuid);
			for (ReplyFileBean b : list) {
				b.setReplyid(save.getReplyid());
				rfr.save(b);
			}
			
			
			BillboardBean bb = br.getById(bean.getBillboardid());// 取出公布欄的訊息
			AdminBean user = (AdminBean) session.getAttribute("user");// 登入者
			// 插入最後回覆時間時間
			Date date = new Date();
			bb.setReplytime(zTools.getTime(date));
			AdminBean abean = ar.findByName(bb.getUser());// 取出發佈人
			// 寄Emai 給發佈人
			String mailTo = abean.getEmail();
			String Subject = bean.getName() + "回覆留言";
			String text = "主題 :" + bb.getTheme() + "<br>回覆 :" + bean.getContent();
			StringBuilder maillist = new StringBuilder();
			zTools.mail(mailTo, text, Subject, maillist.toString());
			// 給發佈人 存mail
			if (!user.getName().equals(abean.getName()))
				ss.saveMail(abean.getAdminid(), bean.getBillboardid(), "新留言");
			// 給被@的人 存mail
			List<BillboardAdviceBean> adviceList = bb.getAdvice();
			for (BillboardAdviceBean advice : adviceList) {
				if (!user.getName().equals(advice.getFormname()))
					ss.saveMail(advice.getAdviceto(), bean.getBillboardid(), "新留言");
			}
			// 留言過的人 存mail
			List<BillboardReplyBean> replyList = bb.getReply();
			for (BillboardReplyBean reply : replyList) {
				if (!user.getName().equals(reply.getName()))
					ss.saveMail(ar.findByName(reply.getName()).getAdminid(), bean.getBillboardid(), "新留言");// 用名子去admin找人
																											// 找到後取出Adminid
			}
		
		return "redirect:/billboardReply/" + bean.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//修改留言
	@RequestMapping("/replyChange")
	public String replyChange(BillboardReplyBean bean) {
		System.out.println("*****修改留言*****");
		ss.SaveReply(bean) ;
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存留言的留言
	@RequestMapping("/saveReplyreply")
	public String saveReplyreply(ReplyreplyBean replyreplyBean, @RequestParam("billboardid") Integer billboardid) {
		System.out.println("*****儲存留言的留言*****");
		System.out.println(replyreplyBean);
		ss.saveReplyreply(replyreplyBean);
		return "redirect:/billboardReply/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除留言的留言
	@RequestMapping("/removeReplyreply/{ReplyreplyId}")
	@ResponseBody
	public String removeReplyreply(@PathVariable("ReplyreplyId") String ReplyreplyId) {
		System.out.println("*****刪除留言的留言*****");
		String result = ss.removeReplyreply(ReplyreplyId);
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取留言的@
	@RequestMapping("/replyAdvice/{ReplyId}")
	@ResponseBody
	public List<ReplyAdviceBbean> replyAdvice(@PathVariable("ReplyId") String ReplyId) {
		System.out.println("*****讀取留言的@*****");
		List<ReplyAdviceBbean> result = ss.replyAdvice(ReplyId);
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存留言的@
	@RequestMapping("/saveReplyAdvice/{ReplyId}")
	@ResponseBody
	public String saveReplyAdvice(@RequestParam("adviceto") Integer[] adviceto, @PathVariable("ReplyId") String ReplyId,
			@RequestParam("billboardid") Integer billboardid) {
		System.out.println("*****儲存留言的@*****");
		// 準備ReplyAdvice
		ReplyAdviceBbean raBean = new ReplyAdviceBbean();
		raBean.setReplyid(ReplyId);
		// 寄信準備
		BillboardBean billboardBean = br.getById(billboardid);
		BillboardReplyBean replyBean = brr.getById(ReplyId);
		String mailTo = ar.findByName(replyBean.getName()).getEmail();
		String Subject = billboardBean.getTheme() + "有回復被標記";
		String text = replyBean.getContent();
		StringBuilder maillist = new StringBuilder();
		// 沒人刪除ReplyAdvice
		if (adviceto.length == 1 & adviceto[0] == 0) {
			return ss.delReplyAdviceByReplyid(ReplyId);// 返回刪除結果
		} else {// 有人
			// 刪除舊資料
			ss.delReplyAdviceByReplyid(ReplyId);
			// 插入新資料
			for (Integer a : adviceto) {
				if (a != 0) {
					// 儲存ReplyAdvice
					raBean.setReplyadvice(zTools.getUUID());
					raBean.setAdviceto(a);
					ss.saveReplyAdvice(raBean);
					// 儲存maill
					ss.saveMail(a, billboardid, "有回復被標記");
					// 寄信
					AdminBean adminBean = ar.getById(a);
					maillist.append(adminBean.getEmail());
					maillist.append(",");
				}
			}
			maillist.append("jeter.tony56@gmail.com");
			zTools.mail(mailTo, text, Subject, maillist.toString());
		}
		return "@成功";

	}



}
