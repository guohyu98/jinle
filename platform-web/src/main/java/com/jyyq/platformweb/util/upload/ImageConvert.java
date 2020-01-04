package com.jyyq.platformweb.util.upload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageConvert {
	//private final static long max_size = 100 * 1024;
	private final static long max_width = 750;
	private final static long max_thumb_width = 150;

	/**
	 * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
	 * 
	 * @param imgsrc
	 *            源图片地址
	 * @param imgdist
	 *            目标图片地址
	 */

	public static boolean reduceImg(String imgsrc, String imgdist,
			boolean thumbMode) {
		try {
			File srcfile = new File(imgsrc);
			File toFile = new File(imgdist);
			// 检查文件是否存在
			if (!srcfile.exists()) {
				System.out.println("文件不存在");
				return false;
			}
			if(!toFile.exists()){  
				toFile.mkdirs();  
			} 
			//long orgSize = srcfile.length();

			int widthdist = 0;
			int heightdist = 0;
			int[] results = getImgWidth(srcfile);
			if (results == null || results[0] == 0 || results[1] == 0) {
				System.out.println("读取原图宽高失败");
				return false;
			}

			/*if (thumbMode) {
				if (results[0] < max_thumb_width) {
					System.out.println("没达到图片压缩条件1");
					return false;
				}
			} else {
				if (orgSize < max_size && results[0] < max_width) {
					System.out.println("没达到图片压缩条件2");
					return false;
				}
			}*/
			
			double rate = 1;
			
			if(thumbMode){
				rate = (max_thumb_width * 1.0) / results[0];
			}else{
				rate = (max_width * 1.0) / results[0];
			}

			if (rate > 1.0) {
				rate = 1.0;
			}
			System.out.println("rate:" + rate);

			widthdist = (int) Math.round(results[0] * rate);
			heightdist = (int) Math.round(results[1] * rate);
			System.out.println("" + widthdist + "*" + heightdist);

			// 开始读取文件并进行压缩
			Image src = ImageIO.read(srcfile);
			BufferedImage tag = new BufferedImage((int) widthdist,
					(int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(
					src.getScaledInstance(widthdist, heightdist,
							Image.SCALE_SMOOTH), 0, 0, null);

			File imgFile = new File(imgdist);
			ImageIO.write(tag, "jpg", imgFile);

			return true;

		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private static int[] getImgWidth(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int result[] = { 0, 0 };
		try {
			is = new FileInputStream(file);
			src = ImageIO.read(is);
			result[0] = src.getWidth(null); // 得到源图宽
			result[1] = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 裁剪PNG图片工具类
	 *
	 * @param proportion
	 *            是否是等比缩放
	 */
	public static Boolean resizePng(String imgsrc, String imgdist, boolean proportion) {
		try {
			File fromFile = new File(imgsrc);
			File toFile = new File(imgdist);
			// 检查文件是否存在
			if (!fromFile.exists()) {
				System.out.println("文件不存在");
				return false;
			}
			if(!toFile.exists()){  
				toFile.mkdirs();  
			} 
			int outputWidth = 750;
			int outputHeight = 100;
			BufferedImage bi2 = ImageIO.read(fromFile);
			int newWidth;
			int newHeight;
			// 判断是否是等比缩放
			if (proportion) {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
				double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
				// 根据缩放比率大的进行缩放控制
				double rate = rate1 < rate2 ? rate1 : rate2;
				System.out.println("rate1"+rate1);
				System.out.println("rate2"+rate2);
				newWidth = (int) (((double) bi2.getWidth(null)) / rate);
				newHeight = (int) (((double) bi2.getHeight(null)) / rate);
			} else {
				newWidth = outputWidth; // 输出的图片宽度
				newHeight = outputHeight; // 输出的图片高度
			}
			BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = to.createGraphics();
			to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight,
					Transparency.TRANSLUCENT);
			g2d.dispose();
			g2d = to.createGraphics();
			@SuppressWarnings("static-access")
			Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);
			g2d.drawImage(from, 0, 0, null);
			g2d.dispose();
			ImageIO.write(to, "png", toFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
