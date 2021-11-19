package com.JetecCRM.JetecCRM.controler;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.JetecCRM.JetecCRM.Tool.ZeroTools;
import com.JetecCRM.JetecCRM.model.BillboardFileBean;
import com.JetecCRM.JetecCRM.model.ReplyFileBean;
import com.JetecCRM.JetecCRM.repository.ReplyFileRepository;

@Controller
public class UpfileController {

	@Autowired
	ZeroTools zTools;
	@Autowired
	ReplyFileRepository rfr;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//上傳留言型錄
	@RequestMapping("/upFileToReply/{authorizeId}")
	@ResponseBody
	public String upFileToReply(MultipartHttpServletRequest multipartRequest,
			@PathVariable("authorizeId") String authorizeId) {
		System.out.println("*****上傳型錄***** 留言" + authorizeId);
		String uuid = zTools.getUUID();

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		System.out.println("fileMap " + fileMap);
//圖片儲存
		try {
			for (int i = 0; i <= fileMap.size(); i++) {
//2. 儲存圖片到資料夾
				if (fileMap.get("file" + i) != null) {
//讀取檔眳
					System.out.println(fileMap.get("file" + i).getOriginalFilename());
//讀取副檔名
					String lastname = fileMap.get("file" + i).getOriginalFilename()
							.substring(fileMap.get("file" + i).getOriginalFilename().indexOf("."));
					System.out.println(lastname);
//獲取Tomcat伺服器所在的路徑
					String tomcat_path = System.getProperty("user.dir");
					System.out.println("Tomcat伺服器所在的路徑: " + tomcat_path);
//獲取Tomcat伺服器所在路徑的最後一個檔案目錄
					String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\") + 1, tomcat_path.length());
					System.out.println("Tomcat伺服器所在路徑的最後一個檔案目錄: " + bin_path);
					System.out.println("bin_path == " + bin_path);
					String path2 = "E:/CRMfile/" + fileMap.get("file" + i).getOriginalFilename();
//					String path2 = "C:/CRMfile/" + fileMap.get("file" + i).getOriginalFilename();
					String path3 = "C:\\Users\\Rong\\Desktop\\tomcat-9.0.41\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\"
							+ fileMap.get("file" + i).getOriginalFilename();
//檔案輸出
					System.out.println("檔案輸出到" + path2);
					fileMap.get("file" + i).transferTo(new File(path2));
					System.out.println("輸出成功");
//檔案複製
					String pic_path = null;
					try {
//判斷最後一個檔案目錄是否為bin目錄
						if (("bin").equals(bin_path)) {
							System.out.println("binbinbinbinbinbinbinbinbinbinbinbin");
//獲取儲存上傳圖片的檔案路徑
							pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
									+ "/webapps/CRM/WEB-INF/classes/static/file/";
//列印路徑
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
					ReplyFileBean replyFileBean = new ReplyFileBean();
					replyFileBean.setReplyid("0");
					replyFileBean.setAuthorize(authorizeId);
					replyFileBean.setReplyfileid(uuid);
					replyFileBean.setUrl(fileMap.get("file" + i).getOriginalFilename());
					replyFileBean.setName(fileMap.get("file" + i).getOriginalFilename());
					rfr.save(replyFileBean);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "儲存失敗";
		}
		return "上傳成功";
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//要求留言附件
	@RequestMapping("/selectReplyFile/{authorizeId}")
	@ResponseBody
	public List<ReplyFileBean> selectFile(@PathVariable("authorizeId") String authorizeId) {
		System.out.println("留言*****要求附件*****" + authorizeId);
		return rfr.findByAuthorize(authorizeId);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除留言型錄
	@RequestMapping("/removeReplyFile/{fileId}")
	@ResponseBody
	public String removefile(@PathVariable("fileId") String fileId) {
		ReplyFileBean replyFileBean = rfr.getById(fileId);
		File file = new File("E:\\JetecCRM\\src\\main\\resources\\static\\file\\" + replyFileBean.getUrl());
		System.out.println("刪除結果"+file.delete());
		rfr.delete(replyFileBean);
		return "刪除成功" ;
	}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//下載檔案
//@RequestMapping("/download/{fileUrl}")	
//public String download(@PathVariable("fileUrl") String fileUrl) {
//System.out.println("*****下載檔案*****");
//BillboardFileBean billBoardFileBean = bfr.getByUrl(fileUrl);
//// 獲取Tomcat伺服器所在的路徑
//String tomcat_path = System.getProperty("user.dir");
//System.out.println("Tomcat伺服器所在的路徑: " + tomcat_path);
//// 獲取Tomcat伺服器所在路徑的最後一個檔案目錄
//String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\") + 1, tomcat_path.length());
//// 判斷最後一個檔案目錄是否為bin目錄
//String pic_path = null;
//System.out.println("Tomcat伺服器所在路徑的最後一個檔案目錄: " + bin_path);
//// 獲取儲存上傳圖片的檔案路徑
//pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("\\")) + "/webapps/CRM/WEB-INF/classes/static/file/";
//System.out.println(pic_path);
//System.out.println("來源:"+pic_path + billBoardFileBean.getUrl());
//File source = new File(pic_path + billBoardFileBean.getUrl());
//System.out.println("輸出:"+pic_path + billBoardFileBean.getName());
//File dest = new File(pic_path + billBoardFileBean.getName());
//try {
//System.out.println("複製前");
//Files.copy(source.toPath(), dest.toPath());			
//} catch (IOException e) {
//
//System.out.println("失敗1******************************************************************");
//e.printStackTrace();
////Rong
//pic_path = "E:/CRMfile/";
//System.out.println("來源2:"+pic_path + billBoardFileBean.getUrl());
//source = new File(pic_path + billBoardFileBean.getUrl());
//System.out.println("輸出2:"+pic_path + billBoardFileBean.getName());
//dest = new File(pic_path + billBoardFileBean.getName());
//try {
//System.out.println("複製2前");
//Files.copy(source.toPath(), dest.toPath());
//} catch (IOException ee) {
//System.out.println("失敗2");
//
//ee.printStackTrace();			
//}
//}
//
//return "redirect:/file/" + billBoardFileBean.getName();
//}

}
