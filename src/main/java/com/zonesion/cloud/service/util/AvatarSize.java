package com.zonesion.cloud.service.util;

public class AvatarSize {

	private int cropX;
	
	private int cropY;
	
	private int cropWidth;
	
	private int cropHeight;
	
	private int resizeWidth;
	
	private int resizeHeight;
	
	public AvatarSize(int cropX, int cropY, int cropWidth, int cropHeight, int resizeWidth, int resizeHeight) {
		super();
		this.cropX = cropX;
		this.cropY = cropY;
		this.cropWidth = cropWidth;
		this.cropHeight = cropHeight;
		this.resizeWidth = resizeWidth;
		this.resizeHeight = resizeHeight;
	}

	public int getCropX() {
		return cropX;
	}

	public void setCropX(int cropX) {
		this.cropX = cropX;
	}

	public int getCropY() {
		return cropY;
	}

	public void setCropY(int cropY) {
		this.cropY = cropY;
	}

	public int getCropWidth() {
		return cropWidth;
	}

	public void setCropWidth(int cropWidth) {
		this.cropWidth = cropWidth;
	}

	public int getCropHeight() {
		return cropHeight;
	}

	public void setCropHeight(int cropHeight) {
		this.cropHeight = cropHeight;
	}

	public int getResizeWidth() {
		return resizeWidth;
	}

	public void setResizeWidth(int resizeWidth) {
		this.resizeWidth = resizeWidth;
	}

	public int getResizeHeight() {
		return resizeHeight;
	}

	public void setResizeHeight(int resizeHeight) {
		this.resizeHeight = resizeHeight;
	}
	
}
