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
	// ?????????
	@RequestMapping(path = { "/", "/index" })
	public String index() {
		return "redirect:/billboard?pag=1&sort=createtime";

	}

	// ?????????
	@RequestMapping(path = { "billboard" })
	public String billboard(Model model, HttpSession session, @RequestParam("pag") Integer pag,
			@RequestParam("sort") String sortString) {
		System.out.println("*****?????????*****");
		List<BillboardBean> advice = new ArrayList<BillboardBean>();
		List<BillboardBean> unread = new ArrayList<BillboardBean>();

		// ??????
		if (pag < 1)
			pag = 1;
		pag--;
		Sort sort = Sort.by(Direction.DESC, sortString);
		Pageable p = (Pageable) PageRequest.of(pag, 30, sort);

		Page<BillboardBean> page = (Page<BillboardBean>) br.getByStateAndTop("??????", "", p);
//		???????????????
		model.addAttribute("TotalPages", page.getTotalPages());

		// ???????????????
		AdminBean user = (AdminBean) session.getAttribute("user");
		// ?????????????????? ????????????
		if (user != null) {
			// ??????@?????????
			AdminBean adminBean = ar.getById(user.getAdminid());
			List<BillboardAdviceBean> a = adminBean.getAdvice();
			for (BillboardAdviceBean bean : a) {
				if (bean.getReply().equals("1"))
					advice.add(br.getById(bean.getBillboardid()));
			}
			// ?????????????????????
			List<AdminMailBean> mail = adminBean.getMail();
			for (AdminMailBean bean : mail) {
				unread.add(br.getById(bean.getBillboardid()));
			}

			//
			model.addAttribute("list", ss.getBillboardList("??????", adminBean, pag, sort));
			session.setAttribute("user", adminBean);
			model.addAttribute("advice", advice);// ??????@?????????
			model.addAttribute("unread", unread);// ?????????????????????
		} else {
			AdminBean xxx = null;
			model.addAttribute("list", ss.getBillboardList("??????", xxx, pag, sort));
		}
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//?????????????????????  ?????????
	@RequestMapping("/selectBillboardGroup/{billboardgroupid}")
	public String selectBillboardGroup(Model model, @PathVariable("billboardgroupid") String billboardgroupid) {
//List<BillboardBean> resulet = ss.getBillboardList("??????");
		System.out.println("*****?????????????????????*****");
		model.addAttribute("list", ss.getBillboardList("??????", billboardgroupid));
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????
	@RequestMapping(path = { "/home" })
	public String join(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
			HttpSession session) {
		if (ar.existsByEmailAndPassword(userName, userPassword)) {
			System.out.println(userName + "*****??????*****");
			session.setAttribute("user", ar.findByEmailAndPassword(userName, userPassword));
		} else {
			return "redirect:/time.jsp";
		}
		return "redirect:/";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????
	@RequestMapping(path = { "/Signout" })
	public String Signout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/read/{billboardid}/{adminid}")
	@ResponseBody
	public String read(@PathVariable("billboardid") Integer billboardid, @PathVariable("adminid") Integer adminid,
			HttpSession session) {
		System.out.println("*****????????????*****");
		String result = ss.saveRead(billboardid, adminid);

		session.setAttribute("user", ar.getById(adminid));
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/ReRead/{billboardid}/{adminid}")
	public String ReRead(@PathVariable("billboardid") Integer billboardid, @PathVariable("adminid") Integer adminid,
			HttpSession session) {
		System.out.println("*****????????????*****");
		ss.ReRead(billboardid, adminid);
		session.setAttribute("user", ar.getById(adminid));
		return "redirect:/billboardReply/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????????????????
	@RequestMapping("/billboardReply/{id}")
	public String billboardReply(Model model, @PathVariable("id") Integer id, HttpSession session) {
		System.out.println("*****?????????????????????****");
		//???????????????
		model.addAttribute("uuid",zTools.getUUID());
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		// ????????????????????? ????????????????????????
		model.addAttribute("bean", ss.getBillboardById(id, adminBean));
		// ??????????????? ????????????
		if (adminBean != null)
			session.setAttribute("user", ar.getById(adminBean.getAdminid()));
		// ??????2??????????????????
		model.addAttribute("news", ss.getBillboardByTime());
		// ????????????
		model.addAttribute("reply", ss.getBillboardReply(id));
		return "/system/billboardReply";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/authorize/{uuid}")
	public String authorize(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		System.out.println("*****????????????****");
		if (authorizeRepository.existsById(uuid)) {
			AuthorizeBean authorizeBean = authorizeRepository.getById(uuid);
			AdminBean user = (AdminBean) session.getAttribute("user");
			if (user == null)
				return "redirect:/CRM.jsp?mess=2";
			if (user.getName().equals(authorizeBean.getUsed()) || "?????????".equals(authorizeBean.getUsed())) {
				model.addAttribute("authorizeBean", authorizeBean);
				return "/authorize";
			}
		}
		return "redirect:/CRM.jsp?mess=3";

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ????????????
	@RequestMapping("/saveAuthorize/{uuid}")
	public String saveAuthorize(@PathVariable("uuid") String uuid, BillboardBean bean, HttpSession session) {
		System.out.println("*****????????????*****");
		bean.setRemark("(?????????)");
		bean.setUser(bean.getUser());
		// ??????
		BillboardBean save = ss.SaveBillboard(bean, session);
		// ????????????
		List<BillboardFileBean> list = bfr.findByAuthorize(uuid);
		for (BillboardFileBean b : list) {
			b.setBillboardid(save.getBillboardid());
			bfr.save(b);
		}
		// ????????????
		authorizeRepository.deleteById(uuid);

		return "redirect:/billboardReply/" + save.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????
	@RequestMapping("/selectBillboard")
	public String selectMarket(Model model, @RequestParam("search") String search) {
		System.out.println("???????????????");
		model.addAttribute("list", ss.selectBillboardt(search));
		return "/CRM";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/SaveAdmin")
	@ResponseBody
	public String SaveAdmin(AdminBean abean, HttpServletRequest req, HttpSession session) {
		System.out.println("*****????????????*****");

		String save = ss.SaveAdmin(abean);
		ServletContext sce = req.getServletContext();
		sce.setAttribute("admin", ar.findAll());
		AdminBean user = (AdminBean) session.getAttribute("user");
		if (user != null)
			if (user.getPosition().equals("??????") || user.getPosition().equals("??????"))
				return "????????????,<a href='/CRM/system/adminList/adminid'>??????</a>";
		return save;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/upFile/{authorizeId}")
	@ResponseBody
	public String upFile(MultipartHttpServletRequest multipartRequest,
			@PathVariable("authorizeId") String authorizeId) {
		System.out.println("*****????????????*****");
		String uuid = zTools.getUUID();
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		System.out.println("fileMap " + fileMap);
//????????????
		try {
			for (int i = 0; i <= fileMap.size(); i++) {
//2. ????????????????????????
				if (fileMap.get("file" + i) != null) {
//					????????????
					System.out.println(fileMap.get("file" + i).getOriginalFilename());
//					???????????????
					String lastname = fileMap.get("file" + i).getOriginalFilename()
							.substring(fileMap.get("file" + i).getOriginalFilename().indexOf("."));
					System.out.println(lastname);
					// ??????Tomcat????????????????????????
					String tomcat_path = System.getProperty("user.dir");
					System.out.println("Tomcat????????????????????????: " + tomcat_path);
					// ??????Tomcat????????????????????????????????????????????????
					String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\") + 1, tomcat_path.length());
					System.out.println("Tomcat????????????????????????????????????????????????: " + bin_path);
					System.out.println("bin_path == " + bin_path);
					String path2 = "C:/CRMfile/" + fileMap.get("file" + i).getOriginalFilename();

					String path3 = "C:\\Users\\Rong\\Desktop\\tomcat-9.0.41\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\"
							+ fileMap.get("file" + i).getOriginalFilename();
					// ????????????
					System.out.println("???????????????" + path2);
					fileMap.get("file" + i).transferTo(new File(path2));
					System.out.println("????????????");
					// ????????????
					String pic_path = null;
					try {
						// ???????????????????????????????????????bin??????
						if (("bin").equals(bin_path)) {
							System.out.println("binbinbinbinbinbinbinbinbinbinbinbin");
							// ???????????????????????????????????????
							pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
									+ "/webapps/CRM/WEB-INF/classes/static/file/";
							// ????????????
							System.out.println(pic_path + fileMap.get("file" + i).getOriginalFilename());
							File source = new File(path3);
							File dest = new File(pic_path + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("????????????");
						} else {
							File source = new File(path2);
							File dest = new File(path3);
							System.out.println(path2 + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("????????????");
						}

					} catch (Exception e) {
						System.out.println("????????????");
					}

//3. ??????????????????????????????
					BillboardFileBean billBoardFileBean = new BillboardFileBean();
					billBoardFileBean.setBillboardid(0);
					billBoardFileBean.setAuthorize(authorizeId);
					billBoardFileBean.setFileid(uuid);
//					billBoardFileBean.setUrl(uuid + lastname); // ??????uuid?????????
					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
					ss.saveUrl(billBoardFileBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "????????????";
		}
		return "????????????";
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/selectFile/{authorizeId}")
	@ResponseBody
	public List<BillboardFileBean> selectFile(@PathVariable("authorizeId") String authorizeId) {
		System.out.println("*****????????????*****" + authorizeId);
		return bfr.findByAuthorize(authorizeId);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/remove/{fileId}")
	public String removefile(@PathVariable("fileId") String fileId) {
		BillboardFileBean billBoardFileBean = bfr.getById(fileId);
		File file = new File("E:\\JetecCRM\\src\\main\\resources\\static\\file\\" + billBoardFileBean.getUrl());
		System.out.println(file.delete());
		bfr.delete(billBoardFileBean);
		return "redirect:/authorize/" + billBoardFileBean.getAuthorize();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/top/{billboardid}/{adminid}")
	@ResponseBody
	public String billboardid(@PathVariable("billboardid") Integer billboardid,
			@PathVariable("adminid") Integer adminid, HttpSession session) {
		System.out.println("*****????????????*****");
		String result = ss.setTop(billboardid, adminid);
		session.setAttribute("user", ar.getById(adminid));
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//?????????????????????
	@RequestMapping("/saveReply/{fileuuid}")
	public String saveReply(@PathVariable("fileuuid")String fileuuid, BillboardReplyBean bean, HttpSession session) {
		System.out.println("*****?????????????????????*****");
		// ????????????
		BillboardReplyBean save =ss.SaveReply(bean);
			
			// ????????????
			List<ReplyFileBean> list = rfr.findByAuthorize(fileuuid);
			for (ReplyFileBean b : list) {
				b.setReplyid(save.getReplyid());
				rfr.save(b);
			}
			
			
			BillboardBean bb = br.getById(bean.getBillboardid());// ????????????????????????
			AdminBean user = (AdminBean) session.getAttribute("user");// ?????????
			// ??????????????????????????????
			Date date = new Date();
			bb.setReplytime(zTools.getTime(date));
			AdminBean abean = ar.findByName(bb.getUser());// ???????????????
			// ???Emai ????????????
			String mailTo = abean.getEmail();
			String Subject = bean.getName() + "????????????";
			String text = "?????? :" + bb.getTheme() + "<br>?????? :" + bean.getContent();
			StringBuilder maillist = new StringBuilder();
			zTools.mail(mailTo, text, Subject, maillist.toString());
			// ???????????? ???mail
			if (!user.getName().equals(abean.getName()))
				ss.saveMail(abean.getAdminid(), bean.getBillboardid(), "?????????");
			// ??????@?????? ???mail
			List<BillboardAdviceBean> adviceList = bb.getAdvice();
			for (BillboardAdviceBean advice : adviceList) {
				if (!user.getName().equals(advice.getFormname()))
					ss.saveMail(advice.getAdviceto(), bean.getBillboardid(), "?????????");
			}
			// ??????????????? ???mail
			List<BillboardReplyBean> replyList = bb.getReply();
			for (BillboardReplyBean reply : replyList) {
				if (!user.getName().equals(reply.getName()))
					ss.saveMail(ar.findByName(reply.getName()).getAdminid(), bean.getBillboardid(), "?????????");// ????????????admin??????
																											// ???????????????Adminid
			}
		
		return "redirect:/billboardReply/" + bean.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/replyChange")
	public String replyChange(BillboardReplyBean bean) {
		System.out.println("*****????????????*****");
		ss.SaveReply(bean) ;
		return "redirect:/billboardReply/" + bean.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/replyRemove/{replyId}")
	public String replyRemove(@PathVariable("replyId") String replyId) {
		System.out.println("*****????????????*****");
		Integer Billboardid = ss.delReply(replyId);
		return "redirect:/billboardReply/" + Billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//?????????????????????
	@RequestMapping("/saveReplyreply")
	public String saveReplyreply(ReplyreplyBean replyreplyBean, @RequestParam("billboardid") Integer billboardid) {
		System.out.println("*****?????????????????????*****");
		System.out.println(replyreplyBean);
		ss.saveReplyreply(replyreplyBean);
		return "redirect:/billboardReply/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//?????????????????????
	@RequestMapping("/removeReplyreply/{ReplyreplyId}")
	@ResponseBody
	public String removeReplyreply(@PathVariable("ReplyreplyId") String ReplyreplyId) {
		System.out.println("*****?????????????????????*****");
		String result = ss.removeReplyreply(ReplyreplyId);
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????@
	@RequestMapping("/replyAdvice/{ReplyId}")
	@ResponseBody
	public List<ReplyAdviceBbean> replyAdvice(@PathVariable("ReplyId") String ReplyId) {
		System.out.println("*****???????????????@*****");
		List<ReplyAdviceBbean> result = ss.replyAdvice(ReplyId);
		return result;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????@
	@RequestMapping("/saveReplyAdvice/{ReplyId}")
	@ResponseBody
	public String saveReplyAdvice(@RequestParam("adviceto") Integer[] adviceto, @PathVariable("ReplyId") String ReplyId,
			@RequestParam("billboardid") Integer billboardid) {
		System.out.println("*****???????????????@*****");
		// ??????ReplyAdvice
		ReplyAdviceBbean raBean = new ReplyAdviceBbean();
		raBean.setReplyid(ReplyId);
		// ????????????
		BillboardBean billboardBean = br.getById(billboardid);
		BillboardReplyBean replyBean = brr.getById(ReplyId);
		String mailTo = ar.findByName(replyBean.getName()).getEmail();
		String Subject = billboardBean.getTheme() + "??????????????????";
		String text = replyBean.getContent();
		StringBuilder maillist = new StringBuilder();
		// ????????????ReplyAdvice
		if (adviceto.length == 1 & adviceto[0] == 0) {
			return ss.delReplyAdviceByReplyid(ReplyId);// ??????????????????
		} else {// ??????
			// ???????????????
			ss.delReplyAdviceByReplyid(ReplyId);
			// ???????????????
			for (Integer a : adviceto) {
				if (a != 0) {
					// ??????ReplyAdvice
					raBean.setReplyadvice(zTools.getUUID());
					raBean.setAdviceto(a);
					ss.saveReplyAdvice(raBean);
					// ??????maill
					ss.saveMail(a, billboardid, "??????????????????");
					// ??????
					AdminBean adminBean = ar.getById(a);
					maillist.append(adminBean.getEmail());
					maillist.append(",");
				}
			}
			maillist.append("jeter.tony56@gmail.com");
			zTools.mail(mailTo, text, Subject, maillist.toString());
		}
		return "@??????";

	}



}
