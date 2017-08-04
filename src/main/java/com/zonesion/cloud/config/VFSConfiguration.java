package com.zonesion.cloud.config;

import java.io.File;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.LocalFileProvider;
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zonesion.cloud.service.util.FileUtil;

@Configuration
public class VFSConfiguration {
	
	private final Logger log = LoggerFactory.getLogger(VFSConfiguration.class);
	
	@Bean
	public DefaultFileSystemManager initFileManager() throws FileSystemException{
		log.debug("Starting Vfs file manager configuration...");
		DefaultFileSystemManager fsManager = (DefaultFileSystemManager) VFS.getManager();
		LocalFileProvider localFileProvider=new DefaultLocalFileProvider();
		fsManager.setDefaultProvider(localFileProvider);
		File basePathFile=new File(FileUtil.KEY_FILE_BASE_PATH);
		if(!basePathFile.exists()){
			basePathFile.mkdirs();
		}
		fsManager.setBaseFile(basePathFile);
		return fsManager;
	}

}
