package com.zonesion.cloud.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zonesion.cloud.service.util.AvatarSize;
import com.zonesion.cloud.service.util.FileUtil;

/**
 * 文件管理逻辑
 * @author XiaXiong
 *
 */
@Service
public class FileManageMentService {
	
	private static final Logger log = LoggerFactory.getLogger(FileManageMentService.class);
	
	private static final String DOT = ".";
	private static final String USER_AVATAR_FOLDER_PATH = "public";
	private static final String BASE_TEMP_DIR = "com.zonesion.cloud";
	
	@Inject
	private DefaultFileSystemManager fileManager;
	
	/**
	 * 保存头像
	 * @param mpf
	 * @param folder
	 * @param avatarSize
	 * @return
	 */
	public String saveAvatar(MultipartFile mpf, String folder, AvatarSize avatarSize) throws IOException {
		log.debug("save avatar!");
		String originalFileExtension = mpf.getOriginalFilename()
				.substring(mpf.getOriginalFilename().lastIndexOf(DOT) + 1);
		File tempFile = getTempPath(folder, originalFileExtension).toFile();
		Files.deleteIfExists(tempFile.toPath());
		mpf.transferTo(tempFile);
		BufferedImage avatarImgBuffer = Scalr.crop(ImageIO.read(tempFile), avatarSize.getCropX(), avatarSize.getCropY(), avatarSize.getCropWidth(), avatarSize.getCropHeight());
		avatarImgBuffer = Scalr.resize(avatarImgBuffer, avatarSize.getResizeWidth(), avatarSize.getResizeHeight(), Scalr.OP_ANTIALIAS);
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
		return Paths.get(folder, destFileName).toString();
	}
	
	private FileObject initFileObject(String folder, String destFileName) throws FileSystemException {
		return fileManager.resolveFile(Paths.get(USER_AVATAR_FOLDER_PATH, folder, destFileName).toString());
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
			tempPath = Paths.get(FileUtils.getTempDirectoryPath(), BASE_TEMP_DIR, folder);
		} else {
			tempPath = Paths.get(FileUtils.getTempDirectoryPath(), BASE_TEMP_DIR);
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
}
