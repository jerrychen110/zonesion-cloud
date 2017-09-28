package com.zonesion.cloud.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.xml.transform.TransformerException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zonesion.cloud.config.ApplicationProperties;
import com.zonesion.cloud.repository.UserRepository;
import com.zonesion.cloud.service.util.JcropSize;
import com.zonesion.cloud.service.util.FileUtil;
import com.zonesion.cloud.service.util.ServiceConstants;
import com.zonesion.cloud.web.rest.dto.ResumableInfo;
import com.zonesion.cloud.web.rest.util.PPTUtil;
import com.zonesion.cloud.web.rest.util.ResourceUtil;
import com.zonesion.cloud.web.rest.util.ResumableInfoStorage;

/**
 * 文件管理
 * @author XiaXiong
 *
 */
@Service
public class FileManageMentService {
	
	private static final Logger log = LoggerFactory.getLogger(FileManageMentService.class);
	
	private static final String DOT = ".";
	
	@Inject
	private UserRepository userRepository;
	@Inject
	private ApplicationProperties applicationProperties;
	
	@Inject
	private DefaultFileSystemManager fileManager;
	
	/**
	 * 保存jcrop截取的图片
	 * @param mpf
	 * @param folder
	 * @param avatarSize
	 * @throws IOException
	 * @return
	 */
	public String saveJcropPicture(MultipartFile mpf, String folder, JcropSize jcropSize) throws IOException {
		log.debug("save JcropPicture!");
		String originalFileExtension = mpf.getOriginalFilename()
				.substring(mpf.getOriginalFilename().lastIndexOf(DOT) + 1);
		File tempFile = getTempPath(folder, originalFileExtension).toFile();
		Files.deleteIfExists(tempFile.toPath());
		mpf.transferTo(tempFile);
		BufferedImage avatarImgBuffer = Scalr.crop(ImageIO.read(tempFile), jcropSize.getCropX(), jcropSize.getCropY(), jcropSize.getCropWidth(), jcropSize.getCropHeight());
		avatarImgBuffer = Scalr.resize(avatarImgBuffer, jcropSize.getResizeWidth(), jcropSize.getResizeHeight(), Scalr.OP_ANTIALIAS);
		ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream();
		ImageIO.write(avatarImgBuffer, originalFileExtension, tempOutputStream);
		
		InputStream is = new ByteArrayInputStream(tempOutputStream.toByteArray());
		byte[] tempData = tempOutputStream.toByteArray();
		String md5hex = DigestUtils.md5Hex(tempData);
		String destFileName = new StringBuilder(md5hex).append(DOT).append(originalFileExtension).toString();
		FileObject vfsFile = initFileObject(folder, destFileName);
		OutputStream outputs = null;
		try {
			outputs = vfsFile.getContent().getOutputStream();
			IOUtils.copyLarge(is, outputs, new byte[FileUtil.PART_SIZE]);
		} finally {
			if (outputs != null) {
				IOUtils.closeQuietly(outputs);
			}
		}
		return Paths.get(FileUtil.LOCAL_PUBLIC_FOLDER_PATH, folder, destFileName).toString();
	}
	
	/**
	 * 保存图片 并返回path
	 * @param mpf
	 * @param folder
	 * @param request
	 * @throws IOException
	 * @return
	 */
	public String saveImage(MultipartFile mpf, String folder, MultipartHttpServletRequest request) throws IOException {
		String originalFileExtension = mpf.getOriginalFilename()
				.substring(mpf.getOriginalFilename().lastIndexOf(DOT) + 1);
		File tempFile = getTempPath(folder, originalFileExtension).toFile();
		mpf.transferTo(tempFile);
		ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream();
		ImageIO.write(ImageIO.read(tempFile), originalFileExtension, tempOutputStream);
		byte[] tempData = tempOutputStream.toByteArray();
		String md5hex = DigestUtils.md5Hex(tempData);
		String destFileName = new StringBuilder(md5hex).append(DOT).append(originalFileExtension).toString();
		FileObject vfsFile = null;
		vfsFile = initFileObject(folder, destFileName);
		OutputStream outputs = null;
		InputStream inputStream = null;
		try {
			outputs = vfsFile.getContent().getOutputStream();
			inputStream = new FileInputStream(tempFile);
			IOUtils.copyLarge(inputStream, outputs, new byte[FileUtil.PART_SIZE]);
		} finally {
			if (outputs != null) {
				IOUtils.closeQuietly(outputs);
			}
			if(inputStream != null){
				IOUtils.closeQuietly(inputStream);
			}
		}
		String base = ResourceUtil.getBaseUrl(request, applicationProperties.getServerHostName());
		return base+"/"+org.apache.commons.lang3.StringUtils.replace(Paths.get(folder, destFileName).toString(), "\\", "/");
	}
	
	/**
	 * 上传文件
	 * 
	 * @param fileName
	 * @param folder
	 * @param dataFileType
	 * @throws IOException
	 * @return
	 */
	public String saveDataFile(String fileName, String folder, String dataFileType) throws IOException {
		log.debug("upload file!");
		String destFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
		String filename = destFileName.substring(0,destFileName.indexOf("."));
		System.out.println("destFileName-------------------------------: "+destFileName);
		FileObject vfsFile = null;
		String destFilePath = Paths.get(FileUtil.LOCAL_PRIVATE_FOLDER_PATH, folder, destFileName).toString();
		vfsFile = fileManager.resolveFile(destFilePath);
		System.out.println("destFilePath+++++++++++++++++++++++++++++++++: "+destFilePath);
		System.out.println("vfsFile+++++++++++++++++++++++++++++++++: "+vfsFile);
		//String savePath = vfsFile.toString().substring(vfsFile.toString().indexOf("/")+3,vfsFile.toString().lastIndexOf("/")+1);
		String filePath = vfsFile.toString().substring(vfsFile.toString().indexOf("/")+3);
		File tempFile = new File(fileName);
		FileInputStream fi = null;
		OutputStream outputs = null;
		try {
			fi = new FileInputStream(tempFile);
			boolean isUtf8 = FileUtil.isUTF8(FileUtils.readFileToByteArray(tempFile));
			if(ServiceConstants.FILE_EXTENSION_CSV.equals(dataFileType) && !isUtf8){
				return "";
			}
			outputs = vfsFile.getContent().getOutputStream();
			IOUtils.copyLarge(fi, outputs, new byte[FileUtil.PART_SIZE]);
			try {
				PPTUtil.pptToHtml(filePath);
				if(destFileName.endsWith("ppt")){
					destFilePath = destFilePath.substring(0,destFilePath.lastIndexOf("\\"))+"\\cache\\ppt\\"+filename+".html";
				}
				if(destFileName.endsWith("pptx")){
					destFilePath = destFilePath.substring(0,destFilePath.lastIndexOf("\\"))+"\\cache\\pptx\\"+filename+".html";
				}

			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} finally {
			if (outputs != null) {
				IOUtils.closeQuietly(outputs);
			}
			if (fi != null) {
				IOUtils.closeQuietly(fi);
			}
		}
		return destFilePath;
	}
	
	private FileObject initFileObject(String folder, String destFileName) throws FileSystemException {
		return fileManager.resolveFile(Paths.get(FileUtil.LOCAL_PUBLIC_FOLDER_PATH, folder, destFileName).toString());
	}
	
	/**
	 * 获取临时路径
	 * @param folder
	 * @param surfix
	 * @return
	 * @throws IOException
	 */
	public Path getTempPath(String folder, String surfix) throws IOException {
		return getTempPath(folder,UUID.randomUUID().toString(),surfix,true);
	}

	/**
	 * 获取临时路径
	 * @param folder
	 * @param filename
	 * @param surfix
	 * @param replace
	 * @return
	 * @throws IOException
	 */
	public Path getTempPath(String folder,String filename, String surfix,boolean replace) throws IOException {
		Path tempPath;
		if (StringUtils.isNotBlank(folder)) {
			tempPath = Paths.get(FileUtils.getTempDirectoryPath(), FileUtil.BASE_TEMP_DIR, folder);
		} else {
			tempPath = Paths.get(FileUtils.getTempDirectoryPath(), FileUtil.BASE_TEMP_DIR);
		}

		if (Files.notExists(tempPath)) {
			Files.createDirectories(tempPath);
		}

		String fileSurfix = StringUtils.startsWith(surfix, DOT) ? surfix : new StringBuilder(DOT).append(surfix).toString();
		String tempFileName = new StringBuilder(filename).append(fileSurfix).toString();
		Path filePath=tempPath.resolve(tempFileName);
		if(replace){
			Files.deleteIfExists(filePath);
		}else if(Files.exists(filePath)){
			return filePath;
		}
		
		return Files.createFile(filePath);
	}
	
	/**
	 * 组装临时信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResumableInfo getResumableInfo(MultipartHttpServletRequest request) throws Exception {
		int resumableChunkSize = NumberUtils.toInt(request.getParameter("flowChunkSize"), -1);
		long resumableTotalSize = NumberUtils.toLong(request.getParameter("flowTotalSize"), -1);
		String resumableIdentifier = request.getParameter("flowIdentifier");
		String resumableFilename = request.getParameter("flowFilename");
		String resumableRelativePath = request.getParameter("flowRelativePath");
		// Here we add a ".temp" to every upload file to indicate NON-FINISHED
		String originalFileExtension = resumableFilename.substring(resumableFilename.lastIndexOf(DOT) + 1);
		
		ResumableInfoStorage storage = ResumableInfoStorage.getInstance();
		ResumableInfo info = storage.get(resumableChunkSize, resumableTotalSize,
                resumableIdentifier, resumableFilename, resumableRelativePath);
		synchronized (info) {
			String resumableFilePath = getTempPath(FileUtil.LOCAL_UPLOAD_FILE_FOLDER, resumableIdentifier, originalFileExtension,false).toString();
			info.setResumableFilePath(resumableFilePath);
		}
		
        if (!info.vaild())         {
            storage.remove(info.getResumableIdentifier());
            throw new Exception("Invalid request params.");
        }
		return info;
	}
	
	/**
	 * 保存至临时文件
	 * 
	 * @param info
	 * @param is
	 * @param content_length
	 * @throws IOException
	 */
	public void saveResumableFile(ResumableInfo info, InputStream is, long content_length,int resumableChunkNumber) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(info.getResumableFilePath(), "rw");

		// Seek to position
		raf.seek((resumableChunkNumber - 1L) * info.getResumableChunkSize());

		// Save to file
		long readed = 0;
		byte[] bytes = new byte[1024*100];
		while (readed < content_length) {
			int r = is.read(bytes);
			if (r < 0) {
				break;
			}
			raf.write(bytes, 0, r);
			readed += r;
		}
		raf.close();
	}
	
}
