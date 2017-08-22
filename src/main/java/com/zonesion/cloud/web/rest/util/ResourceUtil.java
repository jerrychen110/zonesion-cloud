package com.zonesion.cloud.web.rest.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class ResourceUtil {

	private ResourceUtil() {
		super();
	}
	
	public static String getBaseUrl(HttpServletRequest request, String serverHostName) {
		String baseUrl;
		if (StringUtils.isBlank(serverHostName)) {
			baseUrl = new StringBuilder(request.getScheme()).append("://").append(request.getRequestURL()).append(":")
					.append(request.getServerPort()).append(request.getContextPath()).toString();
		} else {
			baseUrl = new StringBuilder(request.getScheme()).append("://").append(serverHostName)
					.append(request.getContextPath()).toString();
		}
		return baseUrl;
	}

}
