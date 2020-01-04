package com.jyyq.platformweb.util.upload;

import com.jyyq.platformcommon.common.BaseTypeUtils;
import com.jyyq.platformcommon.common.FileUtils;
import com.jyyq.platformcommon.common.RandomDigtalUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MyUploadUtil {

	public static JSONObject upload(MultipartFile srcFile, String type, String prename, String baseUrl, String realRootPath) throws IOException {

		final String base = realRootPath;
		String relSavePath = "";
		switch (type) {
		case "sysPic":
			relSavePath += "syspic";
			break;
		case "pic":
			relSavePath += "pic";
			break;
		case "video":
			relSavePath += "video";
			break;
		case "audio":
			relSavePath += "audio";
			break;
		}
		relSavePath = relSavePath.toLowerCase();
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = base + File.separatorChar + relSavePath;
		savePath = savePath.toLowerCase();
		// System.out.println("savePath:" + savePath);
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			file.mkdirs();
		}

		boolean flag = false;
		// 消息提示
		String message = "";
		JSONObject main = new JSONObject();
		try {
			String filename = srcFile.getOriginalFilename();
			filename = filename.substring(filename
					.lastIndexOf(File.separatorChar) + 1);

			// System.out.println("filename:" + filename);
			if (filename == null || filename.trim().equals("")) {
				return null;
			}

			String extName = FileUtils.getFileExt(filename);
			String mainName = prename + "_"
					+ BaseTypeUtils.longToStr(System.currentTimeMillis())
					+ RandomDigtalUtil.getRandomStringByLength(5);
			String newFileName = mainName + "." + extName;

			newFileName = newFileName.toLowerCase();

			String pathName = savePath + File.separatorChar + newFileName;
			// System.out.println("pathName:"+pathName);
			File newFile = new File(pathName);

			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			srcFile.transferTo(newFile);

			String url = baseUrl + relSavePath + "/" + newFileName;
			// System.out.println(path);

			try {
				main.put("flag", true);
				main.put("path", pathName);
				main.put("url", url);
				main.put("message", message);
			} catch (JSONException jSONException) {
			}

			return main;

		} catch (Exception e) {
			flag = false;
			message = "文件上传失败！";
			e.printStackTrace();

			try {
				main.put("flag", flag);
				main.put("message", message);
			} catch (JSONException jSONException) {
			}

			return main;

		}
	}
	
	
	public static JSONObject upload_compress(MultipartFile srcFile, String type, String prename, String baseUrl, String realRootPath)throws IOException {

		final String base = realRootPath;
		String relSavePath = "";
		switch (type) {
		case "video":
			relSavePath += "video";
			break;
		case "pic":
			relSavePath += "pic";
			break;
		}
		relSavePath = relSavePath.toLowerCase();
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = base + File.separatorChar + relSavePath;
		savePath = savePath.toLowerCase();
		// System.out.println("savePath:" + savePath);
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			file.mkdirs();
		}

		File tempDirFile = new File(base + File.separatorChar + "temp");
		if (!tempDirFile.exists() && !tempDirFile.isDirectory()) {
			System.out.println("临时目录不存在，需要创建");
			tempDirFile.mkdirs();
		}

		
		boolean flag = false;
		// 消息提示
		String message = "";
		JSONObject main = new JSONObject();
		try {
			String filename = srcFile.getOriginalFilename();
			filename = filename.substring(filename
					.lastIndexOf(File.separatorChar) + 1);

			// System.out.println("filename:" + filename);
			if (filename == null || filename.trim().equals("")) {
				return null;
			}

			String extName = getFileExt(filename);
			String mainName = prename + "_" /*+ getFileMain(filename) + "_"*/
					+ BaseTypeUtils.longToStr(System.currentTimeMillis());
			String newFileName = mainName + "." + extName;
			String thumbFileName = "thumb_"+newFileName;
			String tempPathName = base + File.separatorChar + "temp"
					+ File.separatorChar
					+ BaseTypeUtils.longToStr(System.currentTimeMillis())
					+ RandomDigtalUtil.getRandomStringByLength(5) + "."
					+ extName;

			newFileName = newFileName.toLowerCase();

			String pathName = savePath + File.separatorChar + newFileName;
			String thumbPathName = savePath + File.separatorChar + thumbFileName;
			// System.out.println("pathName:"+pathName);
			File tempFile = new File(tempPathName);
			// File newFile = new File(pathName);

			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			srcFile.transferTo(tempFile);
			boolean flag1 = ImageConvert.reduceImg(tempPathName, pathName,
					false);
			System.out.println("flag1:" + flag1);
			Thread.sleep(100);
			if (flag1 == false) {
				System.out.println("转换失败");
				FileUtils.copyFile(tempPathName, pathName);
				
				ImageConvert.reduceImg(pathName, thumbPathName, true);
			} else {
				ImageConvert.reduceImg(pathName, thumbPathName, true);
			}

			String url = baseUrl + relSavePath + "/" + newFileName;
			String thumb = baseUrl + relSavePath +"/" + thumbFileName;
			// System.out.println(path);

			try {
				main.put("flag", true);
				main.put("path", pathName);
				main.put("url", url);
				main.put("thumb", thumb);
				main.put("message", message);
			} catch (JSONException jSONException) {
			}

			return main;

		} catch (Exception e) {
			flag = false;
			message = "文件上传失败！";
			e.printStackTrace();

			try {
				main.put("flag", flag);
				main.put("message", message);
			} catch (JSONException jSONException) {
			}

			return main;

		}
	}
	
	private static String getFileExt(String fname) {
		int index = fname.lastIndexOf(".");
		return fname.substring(index + 1);
	}

}
