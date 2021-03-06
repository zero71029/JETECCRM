package com.JetecCRM.JetecCRM.controler;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
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
import com.JetecCRM.JetecCRM.model.AuthorizeBean;
import com.JetecCRM.JetecCRM.model.BillboardBean;
import com.JetecCRM.JetecCRM.model.BillboardFileBean;
import com.JetecCRM.JetecCRM.model.LibraryBean;
import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.AuthorizeRepository;
import com.JetecCRM.JetecCRM.repository.BillboardFileRepository;
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
	BillboardFileRepository bfr;
	@Autowired
	ZeroTools zTools;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????????????????
	@RequestMapping("/adminList/{so}")
	public String adminList(Model model, @PathVariable("so") String so) {
		System.out.println("*****??????????????????*****");
		model.addAttribute("list", ss.getAdminList(so));
		return "/system/adminList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	// ??????????????????
	@RequestMapping("/adminList/{so}/{name}")
	public String adminListSort(Model model, @PathVariable("so") String so, @PathVariable("name") String name,
			HttpServletRequest sce) {
		System.out.println("*****??????????????????*****");
		ServletContext app = sce.getServletContext();
		List<LibraryBean> list = (List<LibraryBean>) app.getAttribute("library");
		List<String> department = new ArrayList<String>();
		List<String> nnn = new ArrayList<String>();
		if (so.equals("department")) {
			for (LibraryBean library : list) {
				if (library.getLibrarygroup().equals("department"))
					department.add(library.getLibraryoption());
			}
			boolean index = false;
			for (String a : department) {
				if (index) {
					nnn.add(a);
				}
				if (a.equals(name)) {
					index = true;
				}
			}
			for (String a : department) {
				if (a.equals(name)) {
					index = false;
				}
				if (index) {
					nnn.add(a);
				}
			}
		}
		// ???????????????position??????
		if (so.equals("position")) {
			// ???library ??????position??????
			for (LibraryBean library : list) {
				if (library.getLibrarygroup().equals("position"))
					department.add(library.getLibraryoption());
			}
			// ?????????????????????
			boolean index = false;
			for (String a : department) {
				if (index) {
					nnn.add(a);
				}
				if (a.equals(name)) {
					index = true;
				}
			}
			// ??????????????????
			for (String a : department) {
				if (a.equals(name)) {
					index = false;
				}
				if (index) {
					nnn.add(a);
				}
			}
		}
//		System.out.println(department);
//		System.out.println(nnn);
		nnn.add(name);
		List<AdminBean> Billboard = new ArrayList<AdminBean>();
		List<AdminBean> dList = new ArrayList<AdminBean>();
		for (String d : nnn) {
			if (so.equals("department")) {
				dList = ar.getByDepartment(d);
			} else {
				dList = ar.getByPosition(d);
			}
			for (AdminBean r : dList)
				Billboard.add(r);
		}

		model.addAttribute("list", Billboard);
		return "/system/adminList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????????????????
	@RequestMapping("/adminDetail/{id}")
	public String adminDetail(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****??????????????????****");
		model.addAttribute("bean", ss.getById(id));
		return "/system/admin";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/delAdmin")
	@ResponseBody
	public String delAdmin(@RequestParam("id") List<Integer> id, HttpServletRequest sce) {
		System.out.println("*****????????????*****");
		ss.delAdmin(id, sce);
		return "????????????";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//?????????????????????
	@RequestMapping("/billboardList")
	public String billboardList(Model model, HttpSession session, @RequestParam("pag") Integer pag) {
		System.out.println("*****?????????????????????*****");
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		if (pag < 1)
			pag = 1;
		pag--;
		// ?????? ???????????????
		Pageable p = (Pageable) PageRequest.of(pag, 30, Direction.DESC, "createtime");
		Page<BillboardBean> page = (Page<BillboardBean>) br.getByStateAndTop("??????", "", p);
		model.addAttribute("TotalPages", page.getTotalPages());
		// ???????????????
		Sort sort = Sort.by(Direction.DESC, "createtime");
		model.addAttribute("list", ss.getBillboardList("??????", adminBean, pag, sort));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????????????? ??????
	@RequestMapping("/OffShelf")
	public String OffShelf(Model model, HttpSession session) {
		System.out.println("*****????????????????????? ??????*****");
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		Sort sort = Sort.by(Direction.DESC, "createtime");
		model.addAttribute("list", ss.getBillboardList("??????", adminBean, 0, sort));
		return "/system/OffShelf";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????
	@RequestMapping("/SaveBillboard")
	public String SaveBillboard(BillboardBean bean, HttpSession session) {
		System.out.println("*****???????????????*****");
		// ???????????????
		BillboardBean save = ss.SaveBillboard(bean, session);
		return "redirect:/system/billboard/" + save.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//?????????????????????
	@RequestMapping("/billboard/{id}")
	public String billboard(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****?????????????????????****");
		BillboardBean bean = ss.getBillboard(id);
		bean.setContent(bean.getContent().replaceAll("<br>", "\n"));
		model.addAttribute("bean", bean);
		model.addAttribute("uuid", zTools.getUUID());
		return "/system/billboard";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????
	@RequestMapping("/delBillboard")
	@ResponseBody
	public String delMarket(@RequestParam("id") List<Integer> id) {
		System.out.println("*****???????????????*****");
		ss.delBillboard(id);
		return "????????????";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????
	@RequestMapping("/authorize")
	@ResponseBody
	public String authorize(@RequestParam("adminid") Integer adminid) {
		System.out.println("*****???????????????*****");
		String uuid = zTools.getUUID();
		if (adminid != 0) {
			AdminBean aBean = ar.getById(adminid);
			String mailTo = aBean.getEmail();
			String Subject = "???????????????";
			AuthorizeBean authorizeBean = new AuthorizeBean();
			authorizeBean.setId(uuid);
			authorizeBean.setUsed(aBean.getName());
			authorizeRepository.save(authorizeBean);
			String text = String.format("<a href='http://192.168.11.114:8080/CRM/authorize/%s'>??????????????????</a>", uuid);
			String maillist = "";
			zTools.mail(mailTo, text, Subject, maillist);
		} else {
			AuthorizeBean authorizeBean = new AuthorizeBean();
			authorizeBean.setId(uuid);
			authorizeBean.setUsed("?????????");
			authorizeRepository.save(authorizeBean);
		}
		return String.format("http://192.168.11.114:8080/CRM/authorize/%s", uuid);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????????????????
	@RequestMapping("/addOption/{group}/{option}")
	@ResponseBody
	public String addOption(@PathVariable("group") String group, @PathVariable("option") String option,
			HttpServletRequest sce) {
		System.out.println("*****??????????????????*****");
		String resuly = ss.saveOption(group, option);
		ss.updataOption(sce);
		return resuly;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????????????????
	@RequestMapping("/delOption/{group}/{option}")
	@ResponseBody
	public String delOption(@PathVariable("group") String group, @PathVariable("option") String option,
			HttpServletRequest sce) {
		System.out.println("*****??????????????????*****");
		if (option.equals("??????"))
			return "?????? ????????????";
		String result = ss.delOption(group, option);
		ss.updataOption(sce);
		return result;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/upFile/{billboardid}")
	@ResponseBody
	public String upFile(MultipartHttpServletRequest multipartRequest, @PathVariable("billboardid") Integer billboardid,
			Model model) {
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
					String path2 = "E:/CRMfile/";
//					String path2 = "E:/CRMfile/";
					String path3 = "C:\\Users\\Rong\\Desktop\\tomcat-9.0.41\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\";
					// ????????????
//					String filePath =path2 + uuid+lastname;
//					System.out.println("???????????????"+filePath);
//					fileMap.get("file" + i).transferTo(new File(filePath));
//					// ????????????
//					String pic_path = null;
//					try {
//						// ???????????????????????????????????????bin??????
//						if (("bin").equals(bin_path)) {
//							System.out.println("binbinbinbinbinbinbinbinbinbinbinbin");
//							// ???????????????????????????????????????
//							pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
//									+ "/webapps/CRM/WEB-INF/classes/static/file/";
//							// ????????????							
//							File source = new File(filePath);
//							File dest = new File(pic_path + uuid+lastname);
//							System.out.println("?????????"+pic_path+uuid+lastname);
//							Files.copy(source.toPath(), dest.toPath());
//							System.out.println("????????????");
//						} else {
//							File source = new File(filePath);
//							File dest = new File(path3 + uuid+lastname);
//							System.out.println("??????2???"+pic_path+path3 + uuid+lastname);
//							Files.copy(source.toPath(), dest.toPath());
//							System.out.println("??????2??????");
//						}
//
//					} catch (Exception e) {
//						System.out.println("????????????");
//					}
//
////3. ??????????????????????????????
//					BillboardFileBean billBoardFileBean = new BillboardFileBean();
//					billBoardFileBean.setBillboardid(billboardid);
//					billBoardFileBean.setFileid(uuid);
//					billBoardFileBean.setUrl(uuid + lastname); //??????uuid?????????
////					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
//					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
//					ss.saveUrl(billBoardFileBean);

					// ????????????
					System.out.println("???????????????" + path2 + fileMap.get("file" + i).getOriginalFilename());
					fileMap.get("file" + i).transferTo(new File(path2 + fileMap.get("file" + i).getOriginalFilename()));
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
							File source = new File(path3 + fileMap.get("file" + i).getOriginalFilename());
							File dest = new File(pic_path + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("????????????");
						} else {
							File source = new File(path2 + fileMap.get("file" + i).getOriginalFilename());
							File dest = new File(path3 + fileMap.get("file" + i).getOriginalFilename());
							System.out.println(path2 + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("????????????");
						}

					} catch (Exception e) {
						System.out.println("????????????");
					}

//3. ??????????????????????????????
					BillboardFileBean billBoardFileBean = new BillboardFileBean();
					billBoardFileBean.setBillboardid(billboardid);
					billBoardFileBean.setFileid(uuid);
//					billBoardFileBean.setUrl(uuid + lastname); //??????uuid?????????
					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
					ss.saveUrl(billBoardFileBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "???????????? ??????????????????";
		}
		return "????????????";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//????????????
	@RequestMapping("/remove/{fileid}/{billboardid}")
	public String remove(@PathVariable("fileid") String fileid, @PathVariable("billboardid") String billboardid) {
		System.out.println("*****????????????*****");
		ss.removefile(fileid);
		return "redirect:/system/billboard/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//@??????
	@RequestMapping("/advice/{adminid}/{billboardid}")
	public String advice(@RequestParam("adviceto") Integer[] adviceto, @PathVariable("adminid") Integer adminid,
			@PathVariable("billboardid") Integer billboardid) {
		System.out.println("*****@??????*****");
		System.out.println(adviceto.length);
		System.out.println(adviceto[0]);

		if (adviceto.length == 1 & adviceto[0] == 0) {
			//????????? ???????????????
			ss.saveAdvice(adminid, billboardid);
		} else {
			ss.saveAdvice(adviceto, adminid, billboardid);
		}
		return "redirect:/system/billboard/" + billboardid;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//??????????????????
	@RequestMapping("/billboard")
	public String billboard(Model model) {
		System.out.println("*****??????????????????*****");		
		model.addAttribute("uuid",zTools.getUUID());
		return "/system/NewBillboard";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ??????
	@RequestMapping("/saveNewBillboard/{uuid}")
	public String saveNewBillboard(@PathVariable("uuid") String uuid, BillboardBean bean, HttpSession session) {
		System.out.println("*****??????*****");

//??????		
		BillboardBean save = ss.SaveBillboard(bean, session);
//????????????
		List<BillboardFileBean> list = bfr.findByAuthorize(uuid);
		for (BillboardFileBean b : list) {
			b.setBillboardid(save.getBillboardid());
			bfr.save(b);
		}

		return "redirect:/billboardReply/" + save.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//???????????????
	@RequestMapping("/selectBillboard")
	public String selectMarket(Model model, @RequestParam("search") String search) {
		System.out.println("???????????????");
		model.addAttribute("list", ss.selectBillboardt(search));
		return "/system/billboardList";
	}
}
