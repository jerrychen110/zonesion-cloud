package com.zonesion.cloud.web.rest.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

/**
 * @Title: PptUtil.java
 * @Package com.zonesion.cloud.web.rest.util
 * @Description: TODO
 * @author: cc
 * @date: 2017年9月26日 下午12:32:50
 */
public class PPTUtil {
	private final static Log logger = LogFactory.getLog(PPTUtil.class);

	private static final String ENCODING = "UTF-8";
	private static final String INDENT = "yes";
	private static final String METHOD = "html";

	public static void pptToHtml(String filePath) throws IOException, TransformerException {
		File file = new File(filePath);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.indexOf("."));
		System.out.println(fileName);
		String basePath = filePath.substring(0,filePath.lastIndexOf("/") + 1);
		String filePPTPath = basePath+"cache/ppt/";
		String filePPTXPath = basePath+"cache/pptx/";
		//String fileCachePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + "cache/";
		String pptImagePath = filePPTPath + "images/";
		String pptxImagePath = filePPTXPath + "images/";
		if (checkDirectory(pptImagePath) == true && checkDirectory(pptxImagePath) == true) {

			String fileExt = filePath.substring(filePath.indexOf(".") + 1);
			FileOutputStream out = null;
			if (fileExt.equals("pptx")) {
				logger.info("*****pptx转html 正在转换...*****");
				try {
					XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
					Dimension pgsize = ppt.getPageSize();
					System.out.println(pgsize.width + "--" + pgsize.height);
					XSLFSlide[] pptPageXSLFSLiseList = ppt.getSlides();
					String imghtml = "";
					for (int i = 0; i < pptPageXSLFSLiseList.length; i++) {
						System.out.print("第" + i + "页。\n");
						try {
							for (XSLFShape shape : pptPageXSLFSLiseList[i].getShapes()) {
								if (shape instanceof XSLFTextShape) {
									XSLFTextShape tsh = (XSLFTextShape) shape;
									for (XSLFTextParagraph p : tsh) {
										for (XSLFTextRun r : p) {
											r.setFontFamily("宋体");
										}
									}
								}
							}
							BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
									BufferedImage.TYPE_INT_RGB);
							Graphics2D graphics = img.createGraphics();
							// clear the drawing area
							graphics.setPaint(Color.white);
							graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
							pptPageXSLFSLiseList[i].draw(graphics);
							String Imgname = pptxImagePath + fileName + "_" + (i + 1) + ".jpg";
							out = new FileOutputStream(Imgname);
							javax.imageio.ImageIO.write(img, "jpg", out);
							// 图片在html加载路径
							String imgs = "images/" + fileName + "_" + (i + 1) + ".jpg";
							imghtml += "<img src=\'./" + imgs
									+ "\' style=\'width:720px;height:540px;vertical-align:text-bottom;\'><br><br><br><br>";
						} catch (Exception e) {
							System.out.println(e);
							System.out.println("第" + i + "张ppt转换出错");
						}
					}
					System.out.println("success");
					DOMSource domSource = new DOMSource();
					StreamResult streamResult = new StreamResult(out);
					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer serializer = tf.newTransformer();
					serializer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
					serializer.setOutputProperty(OutputKeys.INDENT, INDENT);
					serializer.setOutputProperty(OutputKeys.METHOD, METHOD);
					serializer.transform(domSource, streamResult);
					out.close();
					String ppthtml = "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>"
							+ imghtml + "</body></html>";
					FileUtils.writeStringToFile(new File(filePPTXPath + fileName + ".html"), ppthtml, "utf-8");
					logger.info("*****pptx转html 转换结束...*****");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (fileExt.equals("ppt")) {
				logger.info("*****ppt转html 正在转换...*****");
				try {
					SlideShow ppt = new SlideShow(new FileInputStream(file));
					Dimension pgsize = ppt.getPageSize();
					org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
					String imghtml = "";
					for (int i = 0; i < slide.length; i++) {
						System.out.print("第" + i + "页。\n");

						TextRun[] truns = slide[i].getTextRuns();
						for (int k = 0; k < truns.length; k++) {
							RichTextRun[] rtruns = truns[k].getRichTextRuns();
							for (int l = 0; l < rtruns.length; l++) {

								rtruns[l].setFontIndex(1);
								rtruns[l].setFontName("宋体");
							}
						}
						BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);

						Graphics2D graphics = img.createGraphics();
						graphics.setPaint(Color.BLUE);
						graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
						slide[i].draw(graphics);

						// 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
						String Imgname = pptImagePath + fileName + "_" + (i + 1) + ".jpg";
						out = new FileOutputStream(Imgname);
						javax.imageio.ImageIO.write(img, "jpg", out);
						// 图片在html加载路径
						String imgs = "images/" + fileName + "_" + (i + 1) + ".jpg";
						imghtml += "<img src=\'./" + imgs
								+ "\' style=\'width:720px;height:540px;vertical-align:text-bottom;\'><br><br><br><br>";

					}
					System.out.println("success");
					DOMSource domSource = new DOMSource();
					StreamResult streamResult = new StreamResult(out);
					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer serializer = tf.newTransformer();
					serializer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
					serializer.setOutputProperty(OutputKeys.INDENT, INDENT);
					serializer.setOutputProperty(OutputKeys.METHOD, METHOD);
					serializer.transform(domSource, streamResult);
					out.close();
					String ppthtml = "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>"
							+ imghtml + "</body></html>";
					FileUtils.writeStringToFile(new File(filePPTPath + "/" + fileName + ".html"), ppthtml, "utf-8");
					logger.info("*****ppt转html 转换结束...*****");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static boolean checkDirectory(String filePath) {
		File file = new File(filePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
			return true;
		}
		return true;
	}

	/*public static void main(String[] args) throws IOException, TransformerException {

		String vfsFile = "file:///E:/workspace/zonesion-cloud/target/local-storage/private/upload-file/04aa3a12-7a70-48a8-9c53-5a8e7c6df1eb.ppt";
		String filePath = vfsFile.toString().substring(vfsFile.toString().indexOf("/") + 3);
		System.out.println(filePath);
		String fileCachePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + "cache/";
		System.out.println(fileCachePath);
		PPTUtil.pptToHtml(filePath);
	}*/
}
