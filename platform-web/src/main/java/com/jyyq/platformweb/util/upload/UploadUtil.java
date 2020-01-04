package com.jyyq.platformweb.util.upload;

import com.jyyq.platformcommon.common.FileUtils;
import com.jyyq.platformcommon.common.RandomDigtalUtil;
import com.jyyq.platformcommon.common.ShowVarUtil;
import com.jyyq.platformcommon.common.TestModeUtil;
import com.jyyq.platformcommon.util.config.PrjConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传工具类
 * 
 * @author yu 20160818
 */
@Controller
@RequestMapping("/upload")
public class UploadUtil {


	/**
	 * 通过传入页面读取到的文件，处理后保存到本地磁盘，并返回一个已经创建好的File
	 * 
	 * @param uploaddiy
	 *            从页面中读取到的文件
	 * @param typeName
	 *            一级目录
	 * @param brandName
	 *            二级目录
	 * @param fileTypes
	 *            允许的文件扩展名集合
	 * @return
	 */
	private File getFile(HttpServletRequest request, MultipartFile uploaddiy,
                         List fileTypes, String typeName, String brandName, Boolean compress) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String fileName = uploaddiy.getOriginalFilename();
		fileName = System.currentTimeMillis() + RandomDigtalUtil.getByLength(5) + fileName.substring(fileName.lastIndexOf("."), fileName.length());
		/*fileName = fileName.substring(0, fileName.lastIndexOf("."))
				+ df.format(new Date()).toString()
				+ fileName.substring(fileName.lastIndexOf("."),
						fileName.length());*/

		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 对扩展名进行小写转换
		ext = ext.toLowerCase();
		File file = null;
		if (fileTypes.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
			file = this.creatFolder(request, typeName, brandName, fileName);
			try {
				if(ext.equals("mp4")||ext.equals("mp3")||ext.equals("ogg")||ext.equals("png")||ext.equals("jpg")||ext.equals("JPG")||ext.equals("pdf")||ext.equals("ppt") || ext.equals("jpeg")){
					uploaddiy.transferTo(file); // 保存上传的文件										
				}else if(ext.equals("mov")){
					file = new File(file.toString().substring(0, file.toString().length()-3)+"mp4");//输出本地路径文件
					uploaddiy.transferTo(file); // 保存上传的文件	
				}
				System.out.println("测试传输文件后缀格式ext==============="+ext);
				if(ext.equals("mp4")||ext.equals("mp3")||ext.equals("ogg")||ext.equals("png")||ext.equals("jpg")||ext.equals("JPG")||ext.equals("pdf")||ext.equals("ppt") || ext.equals("jpeg")){
					file = new File(request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()+"/"+PrjConfig.virtualUploadDir+"/"
							+ typeName +"/"+ brandName+"/"+ fileName); 
				}else if(ext.equals("mov")){
					fileName = fileName.substring(0, fileName.length()-3)+"mp4";//输出本地路径文件名称
					String virtualFile = file.toString().substring(0, file.toString().length()-3)+"mp4";//输出本地路径文件
					//ChangeVideo.convert(file.toString(), virtualFile);//转码
					file = new File(request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()+"/"+PrjConfig.virtualUploadDir+"/"
							+ typeName +"/"+ brandName+"/"+ fileName);
				}
				ShowVarUtil.debug("compress", compress);
				if(compress && !ext.equals("mov") && !ext.equals("mp4")){//需要压缩
					/*
					 * 压缩图片文件 返回路径
					 */
					String imgsrc = "";
					String imgdist = "";
					
					imgsrc = PrjConfig.realUploadDir + typeName+"/"+brandName+"/"+ fileName;
					imgdist = PrjConfig.realUploadDir+ typeName+"/"+brandName+"/"+"compression/" + fileName;
					Boolean state;
					if(ext.equals("png")){//png压缩
						state = ImageConvert.resizePng(imgsrc, imgdist, true);
					}else{
						state = ImageConvert.reduceImg(imgsrc, imgdist, false);//压缩文件
					}
					if(state){
						//删除压缩前的源文件
						FileUtils.deleteFile(imgsrc);
					}
					String virtualPath = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()+"/"+PrjConfig.virtualUploadDir+"/"
							+ typeName +"/"+ brandName+"/" + "compression/" + fileName;
					file = new File(virtualPath);
				}
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 检测与创建一级、二级文件夹、文件名 这里我通过传入的两个字符串来做一级文件夹和二级文件夹名称
	 * 通过此种办法我们可以做到根据用户的选择保存到相应的文件夹下
	 */
	private File creatFolder(HttpServletRequest request, String typeName,
                             String brandName, String fileName) {
		File file = null;
		typeName = typeName.replaceAll("/", ""); // 去掉"/"
		typeName = typeName.replaceAll(" ", ""); // 替换半角空格
		typeName = typeName.replaceAll(" ", ""); // 替换全角空格

		brandName = brandName.replaceAll("/", ""); // 去掉"/"
		brandName = brandName.replaceAll(" ", ""); // 替换半角空格
		brandName = brandName.replaceAll(" ", ""); // 替换全角空格
		
		// String path = request.getSession().getServletContext().getRealPath("/");		 
		String path = PrjConfig.realUploadDir;//"c:/";
		File firstFolder = new File(path + typeName); // 一级文件夹
		if (firstFolder.exists()) { // 如果一级文件夹存在，则检测二级文件夹
			File secondFolder = new File(firstFolder, brandName);
			if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件
				file = new File(secondFolder, fileName);
			} else { // 如果二级文件夹不存在，则创建二级文件夹
				secondFolder.mkdirs();
				file = new File(secondFolder, fileName); // 创建完二级文件夹后，再合建文件
			}
		} else { // 如果一级不存在，则创建一级文件夹
			firstFolder.mkdirs();
			File secondFolder = new File(firstFolder, brandName);
			if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件
				file = new File(secondFolder, fileName);
			} else { // 如果二级文件夹不存在，则创建二级文件夹
				secondFolder.mkdirs();
				file = new File(secondFolder, fileName);
			}
		}
		return file;
	}

	/**
	 * 上传图片文件,并保存到指定的路径当中
	 */
	@RequestMapping("/uploadPictureFileList")
	public String addImageList(HttpServletRequest request, String path1,
                               String path2, String uploaddiy, Boolean compress) {
		System.out.println("====================文件上传====================");
		// 转型为MultipartHttpRequest(重点的所在)
		MultipartHttpServletRequest multipartRequest = null;
		try {
			multipartRequest = (MultipartHttpServletRequest) request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获得第1张图片（根据前台的name名称得到上传的文件）
		MultipartFile uploaddiy1 = multipartRequest.getFile(uploaddiy);
		System.out.println("uploaddiy1===="+uploaddiy1);
		ShowVarUtil.showJsonObject("uploaddiy1", uploaddiy1);
		if(uploaddiy1 == null){
			return null;
		}
		// 定义一个数组，用于保存可上传的文件类型
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add("jpg");
		fileTypes.add("bmp");
		fileTypes.add("gif");
		fileTypes.add("png");
		fileTypes.add("mp4");
		fileTypes.add("mp3");
		fileTypes.add("ogg");
		fileTypes.add("pdf");
		fileTypes.add("ppt");
		fileTypes.add("mov");
		fileTypes.add("jpeg");
		// 保存第一张图片
		String fileUrl = "";
		System.out.println("uploaddiy1.getOriginalFilename()=="+uploaddiy1.getOriginalFilename());
		if (!(uploaddiy1.getOriginalFilename() == null || "".equals(uploaddiy1.getOriginalFilename()))) {
			/*
			 * 下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名
			 * 自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等这里我就先不做多的介绍。
			 */

			File file1 = this.getFile(request, uploaddiy1, fileTypes, path1, path2, compress);
			System.out.println("file1======================"+file1);
			if (file1 != null)
				System.out.println(file1);
				fileUrl = file1.toString();			
				if(fileUrl.indexOf("http") != -1){
					fileUrl = "http://"+fileUrl.substring(6, fileUrl.length()); 
				}
			else
				System.out.println("file1 is null");
		}
		fileUrl = fileUrl.replaceAll("\\\\", "/");
		return fileUrl;
	}
	
	/*
     * 上传方法 实时上传返回路径
     */
    @RequestMapping("/uploadRealTime")
    @ResponseBody
    public Map<String, Object> uploadRealTime(String uploadFile, String filePath, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model) throws IOException{
    	ShowVarUtil.input_show("uploadFile="+uploadFile,"filePath="+filePath);
    	if(StringUtils.isBlank(filePath)){
    		filePath = "filePath";
    	}
	    String fileUrl = "";//获取上传图片路径
	    Map<String, Object> modelMap = new HashMap<String, Object>();
	    try {
	    	fileUrl = this.addImageList(request, "upload", filePath, uploadFile, true);//上传图片 返回图片路径
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if(fileUrl != null && !fileUrl.equals("")){
	    	modelMap.put("state", "success");
	    	modelMap.put("uploadFile", fileUrl);				    		 
	    }else{
	    	modelMap.put("state", "error");
	    }
		return modelMap;
    }
    
    /*
     * 上传方法 实时上传返回路径
     */
    @RequestMapping("/uploadRealTimeWithCompress")
    @ResponseBody
    public Map<String, Object> uploadRealTimeWithCompress(String uploadFile, String filePath, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model) throws IOException{
    	ShowVarUtil.input_show("uploadFile="+uploadFile,"filePath="+filePath);
    	if(StringUtils.isBlank(filePath)){
    		filePath = "filePath";
    	}
    	String fileUrl = "";//获取上传图片路径
    	Map<String, Object> modelMap = new HashMap<String, Object>();
    	try {
    		fileUrl = this.addImageList(request, "upload", filePath, uploadFile, true);//上传图片 返回图片路径
			//测试环境修改端口号
			if(TestModeUtil.isTestMode()){
				System.out.println("fileUrl="+fileUrl);
				fileUrl = fileUrl.replace("8081", "8080");
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(fileUrl != null && !fileUrl.equals("")){
    		modelMap.put("state", "success");
    		modelMap.put("uploadFile", fileUrl);				    		 
    	}else{
    		modelMap.put("state", "error");
    	}
    	return modelMap;
    }
    
    public static void main(String[] args) {
    	String ext = "png";
    	String imgsrc = "c:/upload/upload/grade/154234710992197228.jpg";
		String imgdist = "c:/upload/upload/grade/compression/154234710992197228.jpg";
		if(ext.equals("png")){//png压缩
			ImageConvert.resizePng(imgsrc, imgdist, true);
		}else{
			ImageConvert.reduceImg(imgsrc, imgdist, false);//压缩文件
		}
		//删除压缩前的源文件
		FileUtils.deleteFile(imgsrc);
	}

}
