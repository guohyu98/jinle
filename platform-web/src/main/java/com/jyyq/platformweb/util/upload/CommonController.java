package com.jyyq.platformweb.util.upload;

import com.jyyq.platformcommon.common.*;
import com.jyyq.platformcommon.util.config.PrjConfig;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 文件上传，wangEditor文件上传
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	@RequestMapping("/FileUploadvideo.action")
	public void FileUploadvideo(
            @RequestParam("uploadvideo") CommonsMultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {// 对应的input file的id和name必须为uploaddiy1
		System.out.println("FileUpload1.start");

		fileUpload(file, request, response, model);
	}

	@RequestMapping("/FileUploadaudio.action")
	public void FileUploadaudio(
            @RequestParam("uploadaudio") CommonsMultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {// 对应的input file的id和name必须为uploaddiy1
		System.out.println("FileUpload1.start");

		fileUpload(file, request, response, model);
	}

	private void fileUpload(MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response,
                            Model model) {
		String type = request.getParameter("type");
		String prename = request.getParameter("prename");

		ShowVarUtil.input_show(type, prename);
		boolean flag = false;
		String message = "";
		String path = "";
		String url = "";
		JSONObject ret = new JSONObject();
		try {
			String baseUrl = "";
			if (TestModeUtil.isTestMode()) {
				baseUrl = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + "/"+PrjConfig.virtualUploadDir+"/upload/";//
			} else {
				
			}
			String realRootPath = PrjConfig.realUploadDir+"/upload";

			System.out.println("realRootPath:" + realRootPath);

			JSONObject obj = MyUploadUtil.upload(file, type,
					BaseTypeUtils.formatNullStr(prename), baseUrl, realRootPath);
			if (obj != null)
				System.out.println("obj:" + obj.toString());

			flag = JsonUtils.safeGetBooleanValue(obj, "flag");
			System.out.println("flag:" + flag);
			message = JsonUtils.safeGetStrValue(obj, "message");
			path = JsonUtils.safeGetStrValue(obj, "path");
			url = JsonUtils.safeGetStrValue(obj, "url");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ret.put("flag", flag);
			ret.put("message", message);
			ret.put("path", path);
			ret.put("url", url);
			response.setContentType("application/json;charset=UTF-8");
			System.out.println("ret.toString():" + ret.toString());
			PrintWriter out = response.getWriter();
			out.write(ret.toString());// 给页面上传输json对象
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/FileUploadCompress1.action")
	public void FileUploadCompress1(
            @RequestParam("upload1") CommonsMultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {// 对应的input file的id和name必须为uploaddiy1
		System.out.println("FileUploadCompress1.start");

		fileUpload_compress(file, request, response, model);
	}

	@RequestMapping("/FileUploadCompress2.action")
	public void FileUploadCompress2(
            @RequestParam("upload2") CommonsMultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {// 对应的input file的id和name必须为uploaddiy1
		System.out.println("FileUploadCompress2.start");

		fileUpload_compress(file, request, response, model);
	}

	private void fileUpload_compress(CommonsMultipartFile file,
                                     HttpServletRequest request, HttpServletResponse response,
                                     Model model) {
		String type = request.getParameter("type");
		String prename = request.getParameter("prename");

		ShowVarUtil.input_show(type, prename);
		boolean flag = false;
		String message = "";
		String path = "";
		String url = "";
		String thumb = "";
		JSONObject ret = new JSONObject();
		try {
			String baseUrl = "";
			if (TestModeUtil.isTestMode()) {
				baseUrl = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + "/"+PrjConfig.virtualUploadDir+"/";
			} else {
				
			}
			// String realRootPath = VdRootUtil.getVdRootYshdRealPath(request);
			String realRootPath = PrjConfig.realUploadDir+"/upload";

			System.out.println("realRootPath:" + realRootPath);

			JSONObject obj = MyUploadUtil.upload_compress(file, type,
					BaseTypeUtils.formatNullStr(prename), baseUrl, realRootPath);
			if (obj != null)
				System.out.println("obj:" + obj.toString());

			flag = JsonUtils.safeGetBooleanValue(obj, "flag");
			System.out.println("flag:" + flag);
			message = JsonUtils.safeGetStrValue(obj, "message");
			path = JsonUtils.safeGetStrValue(obj, "path");
			url = JsonUtils.safeGetStrValue(obj, "url");
			thumb = JsonUtils.safeGetStrValue(obj, "thumb");

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ret.put("flag", flag);
			ret.put("message", message);
			ret.put("path", path);
			ret.put("url", url);
			ret.put("thumb", thumb);
			response.setContentType("application/json;charset=UTF-8");
			System.out.println("ret.toString():" + ret.toString());
			PrintWriter out = response.getWriter();
			out.write(ret.toString());// 给页面上传输json对象
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//切勿修改 wangeditor的 配置项 editor.config.uploadImgFileName
	@RequestMapping("/uploadWangeditor.action")
	public void uploadWangeditor(@RequestParam("wangEditorH5File") MultipartFile uploaddiy1, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("uploadWangeditor.start");

		/*
		 * MultipartHttpServletRequest multipartRequest = null; try {
		 * multipartRequest = (MultipartHttpServletRequest) request; } catch
		 * (Exception e) { e.printStackTrace(); }
		 * 
		 * Iterator<String> it = multipartRequest.getFileNames(); while
		 * (it.hasNext()) { String i = (String) it.next();
		 * System.out.println(i); }
		 * 
		 * MultipartFile uploaddiy1 =
		 * multipartRequest.getFile("wangEditorH5File");
		 */

		System.out.println("file:" + uploaddiy1.getOriginalFilename());

		String extName = FileUtils.getFileExt(uploaddiy1.getOriginalFilename())
				.toUpperCase();
		// if(extName.equals("JPG")||)

		// 文件类型限制
		String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png" };
		boolean allowed = Arrays.asList(allowedType).contains(uploaddiy1.getContentType());
		if (!allowed) {
			try {
				response.setContentType("text/text;charset=utf-8");
				response.getWriter().write("error|不支持的类型");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return;
		}
		// 图片大小限制
		if (uploaddiy1.getSize() > 5 * 1024 * 1024) {
			try {
				System.out.println("图片太大");
				response.setContentType("text/text;charset=utf-8");
				response.getWriter().write("error|图片大小不能超过5M");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return;
		}

		String baseUrl = request.getScheme() 
				+ "://" + request.getServerName()
				+ ":" + request.getServerPort() 
				+ "/"+PrjConfig.virtualUploadDir+"/upload/wangeditor";

		String path = PrjConfig.realUploadDir + "/upload/wangeditor";// request.getRealPath("/image");
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();

		String fileName = System.currentTimeMillis() + "." + extName;
		try {
			File file1 = new File(path + "/" + fileName);
			uploaddiy1.transferTo(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String imgUrl = baseUrl + "/" + fileName;

			//测试环境修改端口号
			if(TestModeUtil.isTestMode()){
				imgUrl = imgUrl.replace("8081", "8080");
			}
			System.out.println("imgUrl:" + imgUrl);

			response.setContentType("text/text;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(imgUrl); // 返回url地址
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
