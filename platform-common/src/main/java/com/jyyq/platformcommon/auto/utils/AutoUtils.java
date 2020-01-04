package com.jyyq.platformcommon.auto.utils;

import java.io.*;
import java.util.ArrayList;

public class AutoUtils {
	/**
	 * 首字母大写
	 * @param name
	 * @return
	 */
	public static String upperFirst(String name) {
		if (name == null || name.trim().length() < 1)
			return name;

		char[] cs = name.toCharArray();
		if (cs[0] >= 'a' && cs[0] <= 'z')
			cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 首字母小写
	 * @param name
	 * @return
	 */
	public static String lowerFirst(String name) {
		if (name == null || name.trim().length() < 1)
			return name;

		char[] cs = name.toCharArray();
		if (cs[0] >= 'A' && cs[0] <= 'Z')
			cs[0] += 32;
		return String.valueOf(cs);
	}
	
	/*
	 * 生成文件
	 */
	public static void createFile(String filePath, String content){
		String directory = filePath.substring(0, filePath.lastIndexOf("/"));
		System.out.println(directory);
		File direFile = new File(directory);
		if(!direFile.exists()){
			direFile.mkdirs();
		}
		File serviceFile = new File(filePath);
		try {
			if(!serviceFile.exists()){
				serviceFile.createNewFile();
			}
			FileWriter fw = new FileWriter(serviceFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			bw.close();
			String fileName = filePath.substring(filePath.lastIndexOf('/')+1);
			System.out.println("生成"+fileName+"成功!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 删除文件
	 */
	public static void deleteFile(String filePath){
		File file = new File(filePath);
		String fileName = filePath.substring(filePath.lastIndexOf('/')+1);
		if(file.exists()){
			file.delete();
			System.out.println("删除"+fileName+"成功!");
		}else{
			System.out.println(fileName+"不存在!");
		}
	}
	
	public static String readFile(String filePath){
		StringBuffer sb = new StringBuffer();
		File file = new File(filePath);
		if (!file.exists()) {
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
			System.out.println(fileName+"不存在");
			return null;
		}

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line = null;
			while ((line = br.readLine()) != null) {
//				if (line.length() < 1) {
//					continue;
//				}
				sb.append(line+"\n");
			}
			br.close();
			fr.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 从文件获取行文本，只删除空行，不做其他处理
	 * 
	 * @param pathName
	 * @return
	 */
	public static ArrayList<String> getTextLinesFromFile(String pathName) {
		File file = new File(pathName);
		if (file.exists() == false) {
			return null;
		}

		FileReader fr = null;
		BufferedReader br = null;

		ArrayList<String> alRet = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.length() < 1) {
					continue;
				}

				alRet.add(line);
			}
			br.close();
			fr.close();
			return alRet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
