package com.baseframework.log;

import java.io.File;

public class LoggerForm {

	private String loggerConfig;

	public String getLoggerConfig() {
		return loggerConfig;
	}

	public void setLoggerConfig(String loggerConfig) {
		this.loggerConfig = loggerConfig;
	}

	private File file;
	private String contentType;
	private String filename;

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}
	
	public File getFile() {
		return file;
	}

}
