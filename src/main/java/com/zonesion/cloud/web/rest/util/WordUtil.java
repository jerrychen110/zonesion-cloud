package com.zonesion.cloud.web.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

/**
 * @Title: WordUtil.java
 * @Package com.zonesion.cloud.web.rest.util
 * @Description: TODO
 * @author: cc
 * @date: 2017年9月26日 下午12:37:38
 */
public class WordUtil {
	
	private final static Log logger = LogFactory.getLog(WordUtil.class);  
    
    private static final String ENCODING="UTF-8";
    private static final String INDENT="yes";
    private static final String METHOD="html";
    
	public static void wordToHtml(String filePath)
			throws TransformerException, ParserConfigurationException {
		  
		
		File file = new File(filePath);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.indexOf("."));
		String basePath = filePath.substring(0,filePath.lastIndexOf("/") + 1);
		String fileDocPath = basePath+"cache/doc/";
		String fileDocxPath = basePath+"cache/docx/";
		//String fileImagePath = fileCachePath;
		if(checkDirectory(fileDocPath) == true && checkDirectory(fileDocxPath) == true){
		String fileExt = filePath.substring(filePath.indexOf(".") + 1);
		
		if (fileExt.equals("doc")) {
			logger.info("*****doc转html 正在转换...*****");  
			try {
				HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(file));
				WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
						DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
				wordToHtmlConverter.setPicturesManager(new PicturesManager() {
					@Override
					public String savePicture(byte[] content, PictureType pictureType, String suggestedName,
							float widthInches, float heightInches) {
						return suggestedName;
					}
				});
				wordToHtmlConverter.processDocument(wordDocument);
				List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
				if (pics != null) {
					for (int i = 0; i < pics.size(); i++) {
						Picture pic = (Picture) pics.get(i);
						try {
							pic.writeImageContent(new FileOutputStream(fileDocPath + pic.suggestFullFileName()));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
				Document htmlDocument = wordToHtmlConverter.getDocument();
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				DOMSource domSource = new DOMSource(htmlDocument);
				StreamResult streamResult = new StreamResult(outStream);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer serializer = tf.newTransformer();
				serializer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
				serializer.setOutputProperty(OutputKeys.INDENT, INDENT);
				serializer.setOutputProperty(OutputKeys.METHOD, METHOD);
				serializer.transform(domSource, streamResult);
				outStream.close();
				String content = new String(outStream.toByteArray());
				FileUtils.writeStringToFile(new File(fileDocPath+fileName + ".html"), content, "utf-8");
				logger.info("*****doc转html 转换结束...*****");   
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fileExt.equals("docx")) {
			logger.info("*****docx转html 正在转换...*****");   
			try {
				XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(file));
				File imageFolderFile = new File(fileDocxPath);
				// 加载html页面时图片路径
				XHTMLOptions options = XHTMLOptions.create().URIResolver(new BasicURIResolver("./"));
				// 图片保存文件夹路径
				options.setExtractor(new FileImageExtractor(imageFolderFile));
				OutputStream out = new FileOutputStream(new File(fileDocxPath+fileName + ".html"));
				XHTMLConverter.getInstance().convert(xwpfDocument, out, options);
				out.close();
				logger.info("*****docx转html 转换结束...*****"); 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  }

	}
	public static boolean checkDirectory(String filePath){
		File file = new File(filePath);
		if  (!file.exists() && !file.isDirectory())      
		{       
		    file.mkdir();   
		    return true;
		}
		return true; 
	}
	/*public static void main(String[] args) throws TransformerException, ParserConfigurationException{
		String vfsFile = "file:///E:/workspace/zonesion-cloud/target/local-storage/private/upload-file/1.doc";
		String filePath = vfsFile.toString().substring(vfsFile.toString().indexOf("/")+3);
		System.out.println(filePath);
		String fileCachePath = filePath.substring(0,filePath.lastIndexOf("/") + 1)+"cache/";
		System.out.println(fileCachePath);
		WordUtil.wordToHtml(filePath);
		String destFileName = "c61ed252-472a-49e4-b413-2d1409160f78.ppt";
		String filename = destFileName.substring(0,destFileName.indexOf("."));
		String s ="private\\upload-file\\c61ed252-472a-49e4-b413-2d1409160f78.ppt";
		System.out.println(s);
		String cachePath = s.substring(0,s.lastIndexOf("\\"))+"\\cache\\pptx";
		System.out.println(filename);
	}*/
}
