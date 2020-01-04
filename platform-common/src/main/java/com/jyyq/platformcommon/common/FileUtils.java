package com.jyyq.platformcommon.common;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {
	/**
	 * 拷贝文件
	 * @param srcFile
	 * @param destFile
	 */
	public static void copyFile(String srcFile, String destFile) {
		try {

			File file_src = new File(srcFile);
			int fileLen = (int) file_src.length();

			FileInputStream in = new FileInputStream(file_src);

			File file_dest = new File(destFile);
			file_dest.createNewFile();
			FileOutputStream out = new FileOutputStream(file_dest);

			byte[] buff = new byte[4096];

			int haveread = 0;
			int readlen = 0;
			while (haveread < fileLen) {
				readlen = in.read(buff);
				if (readlen < 0) {
					break;
				} else {
					haveread += readlen;
				}
				out.write(buff, 0, readlen);
				out.flush();
			}

			in.close();
			in = null;
			out.close();
			out = null;
			file_src = null;
			file_dest = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除文件
	 * @param pathname
	 */
	public static void deleteFile(String pathname) {
		if (pathname == null || pathname.length() < 1) {
			return;
		}
		File file = new File(pathname);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}


	public static byte[] getFileBytes(String pathName) {
		try {
			File file = new File(pathName);
			if (!file.exists()) {
				return null;
			}

			if (!file.isFile()) {
				return null;
			}
			if (file.length() <= 0) {
				return null;
			}

			int fileLen = (int) file.length();
			FileInputStream in = new FileInputStream(file);
			byte[] content = new byte[fileLen];
			in.read(content); // 待修改为循环读取
			in.close();
			return content;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static byte[] getFileBytes(String pathName, int offset) {
		try {
			File file = new File(pathName);
			if (!file.exists()) {
				return null;
			}

			if (!file.isFile()) {
				return null;
			}
			if (file.length() <= 0) {
				return null;
			}

			int toReadLen = (int) file.length();
			if (offset > 0) {
				toReadLen = toReadLen - offset;
			}
			if (toReadLen < 1) {
				return null;
			}

			FileInputStream in = new FileInputStream(file);
			if (offset > 0) {
				in.skip(offset);
			}

			byte[] content = new byte[toReadLen];
			in.read(content); // 待修改为循环读取
			in.close();
			return content;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	/**
	 * @功能: 删除某一目录及其子目录
	 * @参数:
	 * @返回值:
	 * @作者: suitang at 2011-11-7
	 * @修改记录:
	 */
	public static synchronized Boolean pDeleteDirectory(String filePath) {

		Boolean bTotalRlt = true;
		File file = new File(filePath);

		File[] fileList = file.listFiles();
		String dirPath = null;

		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					bTotalRlt = bTotalRlt && fileList[i].delete();
				}

				if (fileList[i].isDirectory()) {
					dirPath = fileList[i].getPath();
					bTotalRlt = bTotalRlt && pDeleteDirectory(dirPath);
				}
			}
			bTotalRlt = bTotalRlt && file.delete();
		}

		return bTotalRlt;
	}

	/**
	 * @功能: 删除某一目录
	 * @参数:
	 * @返回值:
	 * @作者: suitang at 2011-11-7
	 * @修改记录:
	 */
	public static Boolean deleteDirectory(String filePath) {

		Boolean ret = true;
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		} else {
			ret = false;
		}

		return ret;
	}

	public static boolean writeBytesToFile(String pathName, byte[] block) {
		try {
			File file = new File(pathName);
			// File parent = file.getParentFile();
			// if (parent.exists() == false) {
			// parent.mkdirs();// 先建父目录
			// }
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			out.write(block);
			out.flush();
			out.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public static boolean ifFileExist(String pathName) {
		File file = new File(pathName);
		return file.exists();
	}

	public static boolean writeTextLinesToFile(String pathName,
			ArrayList<String> alLines, boolean append) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(pathName);
			File parent = file.getParentFile();
			if (parent.exists() == false) {
				parent.mkdirs();// 先建父目录
			}
			if (file.exists() == false) {
				file.createNewFile();
			}

			if (alLines == null || alLines.size() < 1) {
				return false;
			}

			fw = new FileWriter(file, append);
			bw = new BufferedWriter(fw);

			for (int i = 0, ilen = alLines.size(); i < ilen; i++) {
				bw.write(alLines.get(i));
				bw.write("\n");
				bw.flush();
			}
			bw.close();
			fw.close();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e) {
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e) {
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

	/**
	 * 获取行文本的第一个有效行
	 * 
	 * @param pathName
	 * @return
	 */
	public static String getValidFirstTextLine(String pathName) {
		File file = new File(pathName);
		if (file.exists() == false) {
			return null;
		}

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String firstLine = null;

			while ((firstLine = br.readLine()) != null) {
				if (firstLine.length() < 1) {
					continue;
				}

				break;
			}
			br.close();
			fr.close();
			return firstLine;
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
	 * 从数据库输入流中获取字节数组
	 * 
	 * @param is
	 * @return
	 */
	public static byte[] getInputStreamBytes(InputStream is) {
		byte[] defRetBytes = new byte[0];
		try {
			if (is == null) {
				return defRetBytes;
			}

			int count = is.available();
			// System.out.println("count=" + count);
			byte[] listBytes = new byte[count];
			is.read(listBytes);

			return listBytes;
		} catch (Exception e) {
			e.printStackTrace();
			return defRetBytes;
		}
	}/**
	 * 获取 单字节长度 + String
	 * 
	 * @param bais
	 * @return
	 */
	public static byte[] getByteStrBytes(ByteArrayInputStream bais) {
		final int defMaxLen = 127;
		try {
			int tempLen = bais.read();
			if (tempLen >= 0 && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取 单字节长度 + String
	 * 
	 * @param bais
	 * @param maxLen
	 * @return
	 */
	public static byte[] getByteStrBytes(ByteArrayInputStream bais, int maxLen) {
		final int defMaxLen = 127;
		try {
			int tempLen = bais.read();
			if (tempLen >= 0 && tempLen < maxLen && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取 int长度 + String
	 * 
	 * @param bais
	 * @return
	 */
	public static byte[] getIntStrBytes(ByteArrayInputStream bais) {
		final int defMaxLen = 1024 * 1024;
		try {
			byte[] tempIntArr = new byte[4];
			bais.read(tempIntArr);
			int tempLen = BaseTypeUtils.byteArrayToInt(tempIntArr);

			if (tempLen >= 0 && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取 int长度 + String
	 * 
	 * @param bais
	 * @param maxLen
	 * @return
	 */
	public static byte[] getIntStrBytes(ByteArrayInputStream bais, int maxLen) {
		final int defMaxLen = 1024 * 1024;
		try {
			byte[] tempIntArr = new byte[4];
			bais.read(tempIntArr);
			int tempLen = BaseTypeUtils.byteArrayToInt(tempIntArr);

			if (tempLen >= 0 && tempLen < maxLen && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getFileExt(String fname) {
		int index = fname.lastIndexOf(".");
		return fname.substring(index + 1);
	}

	public static String getFileMain(String fname) {
		int index = fname.lastIndexOf(".");
		System.out.println("index:"+index);
		return fname.substring(0, index);
	}
}
