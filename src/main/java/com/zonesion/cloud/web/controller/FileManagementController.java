package com.zonesion.cloud.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zonesion.cloud.service.FileManageMentService;
import com.zonesion.cloud.service.util.FileUtil;

/**
 * 文件上传controller
 * @author XiaXiong
 *
 */
@Controller
@RequestMapping("/api/file-management")
public class FileManagementController {
	
	private final Logger log = LoggerFactory.getLogger(FileManagementController.class);
	
	@Inject
	private FileManageMentService fileManageMentService;
	
	/**
	 * 富文本控件中插入图片的处理
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/image-upload", method = RequestMethod.POST,
            headers = {"content-type=multipart/form-data"})
	public ResponseEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException{
		log.debug("Saving team logo...");
		String imageUrl = null;
		Iterator<String> itr = request.getFileNames();
		String fileName="";
		if (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			imageUrl = fileManageMentService.saveImage(mpf, FileUtil.LOCAL_UPLOAD_IMAGE_FOLDER, request);
			fileName = mpf.getOriginalFilename();
		}
		if("json".equals(request.getParameter("responseType"))){
			Map<String, String> ret = new HashMap<>();
			ret.put("fileName", fileName);
			ret.put("uploaded", "1");
			ret.put("url", imageUrl);
			return new ResponseEntity<>(ret, HttpStatus.OK);
        }else{
        	String ret = "<script type='text/javascript'>"+
            		"window.parent.CKEDITOR.tools.callFunction('"+
            		request.getParameter("CKEditorFuncNum")+
            		"','"+imageUrl+"','');"+
            		"</script>";
        	return new ResponseEntity<>(ret, HttpStatus.OK);
        }
	}
	
}
