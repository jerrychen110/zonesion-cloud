package com.zonesion.cloud.web.rest.dto;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class ResumableInfo {

	private int resumableChunkSize;
	private long resumableTotalSize;
	private String resumableIdentifier;
	private String resumableFilename;
	private String resumableRelativePath;
	private String resumableFilePath;
	// Chunks uploaded
	private Set<ResumableChunkNumber> uploadedChunks = new HashSet<>();

	public static class ResumableChunkNumber {
		public ResumableChunkNumber(int number) {
			this.number = number;
		}

		private int number;

		@Override
		public boolean equals(Object obj) {
			return obj instanceof ResumableChunkNumber ? ((ResumableChunkNumber) obj).number == this.number : false;
		}
		@Override
		public int hashCode() {
			return number;
		}
	}

	public boolean vaild() {
		if (resumableChunkSize < 0 || resumableTotalSize < 0) {
			return false;
		} else if (StringUtils.isEmpty(resumableIdentifier) || StringUtils.isEmpty(resumableFilename)
				|| StringUtils.isEmpty(resumableRelativePath)) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean checkIfUploadFinished() {
		// check if upload finished
		int count = (int) Math.ceil(((double) resumableTotalSize) / ((double) resumableChunkSize));
		for (int i = 1; i < count + 1; i++) {
			if (!uploadedChunks.contains(new ResumableChunkNumber(i))) {
				return false;
			}
		}
		return true;
	}

	public int getResumableChunkSize() {
		return resumableChunkSize;
	}

	public void setResumableChunkSize(int resumableChunkSize) {
		this.resumableChunkSize = resumableChunkSize;
	}

	public long getResumableTotalSize() {
		return resumableTotalSize;
	}
	
	public void setResumableTotalSize(long resumableTotalSize) {
		this.resumableTotalSize = resumableTotalSize;
	}

	public String getResumableIdentifier() {
		return resumableIdentifier;
	}

	public void setResumableIdentifier(String resumableIdentifier) {
		this.resumableIdentifier = resumableIdentifier;
	}

	public String getResumableFilename() {
		return resumableFilename;
	}

	public void setResumableFilename(String resumableFilename) {
		this.resumableFilename = resumableFilename;
	}

	public String getResumableRelativePath() {
		return resumableRelativePath;
	}

	public void setResumableRelativePath(String resumableRelativePath) {
		this.resumableRelativePath = resumableRelativePath;
	}

	public String getResumableFilePath() {
		return resumableFilePath;
	}

	public void setResumableFilePath(String resumableFilePath) {
		this.resumableFilePath = resumableFilePath;
	}
	
	public Set<ResumableChunkNumber> getUploadedChunks() {
		return uploadedChunks;
	}

	public void setUploadedChunks(Set<ResumableChunkNumber> uploadedChunks) {
		this.uploadedChunks = uploadedChunks;
	}
}
