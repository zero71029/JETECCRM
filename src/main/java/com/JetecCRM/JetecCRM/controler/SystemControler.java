package com.JetecCRM.JetecCRM.controler;

import java.io.File;
import java.io.IOException;
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
	@RequestMapping("/adminList/{so}")
	public String adminList(Model model, @PathVariable("so") String so) {
		System.out.println("*****讀取員工列表*****");
		model.addAttribute("list", ss.getAdminList(so));
		return "/system/adminList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	// 員工列表排序
	@RequestMapping("/adminList/{so}/{name}")
	public String adminListSort(Model model, @PathVariable("so") String so, @PathVariable("name") String name,
			HttpServletRequest sce) {
		System.out.println("*****員工列表排序*****");
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
		// 如果是依照position排序
		if (so.equals("position")) {
			// 從library 抓取position列表
			for (LibraryBean library : list) {
				if (library.getLibrarygroup().equals("position"))
					department.add(library.getLibraryoption());
			}
			// 從輸入開始抓取
			boolean index = false;
			for (String a : department) {
				if (index) {
					nnn.add(a);
				}
				if (a.equals(name)) {
					index = true;
				}
			}
			// 填充前面資料
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
	public String billboardList(Model model, HttpSession session, @RequestParam("pag") Integer pag) {
		System.out.println("*****讀取公佈欄列表*****");
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		if (pag < 1)
			pag = 1;
		pag--;
		// 分頁 全部有幾頁
		Pageable p = (Pageable) PageRequest.of(pag, 30, Direction.DESC, "createtime");
		Page<BillboardBean> page = (Page<BillboardBean>) br.getByStateAndTop("公開", "", p);
		model.addAttribute("TotalPages", page.getTotalPages());
		// 公佈欄列表
		model.addAttribute("list", ss.getBillboardList("公開", adminBean, pag));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄列表 封存
	@RequestMapping("/OffShelf")
	public String OffShelf(Model model, HttpSession session) {
		System.out.println("*****讀取公佈欄列表*****");
		AdminBean adminBean = (AdminBean) session.getAttribute("user");
		model.addAttribute("list", ss.getBillboardList("封存", adminBean, 0));
		return "/system/billboardList";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存公佈欄
	@RequestMapping("/SaveBillboard")
	public String SaveBillboard(BillboardBean bean, HttpSession session) {
		System.out.println("*****儲存公佈欄*****");
		BillboardBean save = ss.SaveBillboard(bean, session);
		return "redirect:/system/billboard/" + save.getBillboardid();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取公佈欄細節
	@RequestMapping("/billboard/{id}")
	public String billboard(Model model, @PathVariable("id") Integer id) {
		System.out.println("*****讀取公佈欄細節****");
		BillboardBean bean = ss.getBillboard(id);
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
			String text = String.format("<a href='http://192.168.11.114:8080/CRM/authorize/%s'>點擊鍊接留言</a>", uuid);
			String maillist = "";
			zTools.mail(mailTo, text, Subject, maillist);
		} else {
			AuthorizeBean authorizeBean = new AuthorizeBean();
			authorizeBean.setId(uuid);
			authorizeBean.setUsed("所有人");
			authorizeRepository.save(authorizeBean);
		}
		return String.format("http://192.168.11.114:8080/CRM/authorize/%s", uuid);
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
					String path2 = "E:/CRMfile/";
//					String path2 = "E:/CRMfile/";
					String path3 = "C:\\Users\\Rong\\Desktop\\tomcat-9.0.41\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\";
					// 檔案輸出
//					String filePath =path2 + uuid+lastname;
//					System.out.println("檔案輸出到"+filePath);
//					fileMap.get("file" + i).transferTo(new File(filePath));
//					// 檔案複製
//					String pic_path = null;
//					try {
//						// 判斷最後一個檔案目錄是否為bin目錄
//						if (("bin").equals(bin_path)) {
//							System.out.println("binbinbinbinbinbinbinbinbinbinbinbin");
//							// 獲取儲存上傳圖片的檔案路徑
//							pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
//									+ "/webapps/CRM/WEB-INF/classes/static/file/";
//							// 列印路徑							
//							File source = new File(filePath);
//							File dest = new File(pic_path + uuid+lastname);
//							System.out.println("複製到"+pic_path+uuid+lastname);
//							Files.copy(source.toPath(), dest.toPath());
//							System.out.println("複製成功");
//						} else {
//							File source = new File(filePath);
//							File dest = new File(path3 + uuid+lastname);
//							System.out.println("複製2到"+pic_path+path3 + uuid+lastname);
//							Files.copy(source.toPath(), dest.toPath());
//							System.out.println("複製2成功");
//						}
//
//					} catch (Exception e) {
//						System.out.println("複製失敗");
//					}
//
////3. 儲存檔案名稱到資料庫
//					BillboardFileBean billBoardFileBean = new BillboardFileBean();
//					billBoardFileBean.setBillboardid(billboardid);
//					billBoardFileBean.setFileid(uuid);
//					billBoardFileBean.setUrl(uuid + lastname); //使用uuid建檔名
////					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
//					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
//					ss.saveUrl(billBoardFileBean);

					
					
					// 檔案輸出
					System.out.println("檔案輸出到"+path2 + fileMap.get("file" + i).getOriginalFilename());
					fileMap.get("file" + i).transferTo(new File(path2 + fileMap.get("file" + i).getOriginalFilename()));
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
							File source = new File(path3 + fileMap.get("file" + i).getOriginalFilename());
							File dest = new File(pic_path + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("複製成功");
						} else {
							File source = new File(path2 + fileMap.get("file" + i).getOriginalFilename());
							File dest = new File(path3 + fileMap.get("file" + i).getOriginalFilename());
							System.out.println(path2 + fileMap.get("file" + i).getOriginalFilename());
							Files.copy(source.toPath(), dest.toPath());
							System.out.println("複製成功");
						}

					} catch (Exception e) {
						System.out.println("複製失敗");
					}

//3. 儲存檔案名稱到資料庫
					BillboardFileBean billBoardFileBean = new BillboardFileBean();
					billBoardFileBean.setBillboardid(billboardid);
					billBoardFileBean.setFileid(uuid);
//					billBoardFileBean.setUrl(uuid + lastname); //使用uuid建檔名
					billBoardFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
					billBoardFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
					ss.saveUrl(billBoardFileBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "儲存失敗 請聯絡管理員";
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
		System.out.println(adviceto.length);
		System.out.println(adviceto[0]);

		if (adviceto.length == 1 & adviceto[0] == 0) {
			ss.saveAdvice(adminid, billboardid);
		} else {
			ss.saveAdvice(adviceto, adminid, billboardid);
		}
		return "redirect:/system/billboard/" + billboardid;
	}
}
