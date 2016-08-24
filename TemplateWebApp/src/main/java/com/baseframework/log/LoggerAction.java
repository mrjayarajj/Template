package com.baseframework.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.access.annotation.Secured;

public class LoggerAction implements ServletContextAware {

	private ServletContext servletContext;

	private LoggerForm loggerForm;

	@Secured({ "BF_CONTROL_LOGGER" })
	public String onLoad() {
		String webAppPath = getServletContext().getRealPath("/");
		String log4jLocation = getServletContext().getInitParameter("log4jConfigLocation");
		String log4jProp = webAppPath + log4jLocation;
		File log4jFile = new File(log4jProp);
		String currentLog4jConfig = (String) getServletContext().getAttribute("CURRENT_LOG4J_CONFIG");
		if (currentLog4jConfig != null) {
			getLoggerForm().setLoggerConfig(currentLog4jConfig);
		} else {
			setLog4jConfig(log4jFile);
		}
		return "success";
	}

	private void setLog4jConfig(File log4jFile) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(log4jFile)));
			try {
				String thisLine = null;
				StringBuffer loggerConfig = new StringBuffer();
				while ((thisLine = br.readLine()) != null) {
					loggerConfig.append(thisLine);
					loggerConfig.append("\n");
				}
				getLoggerForm().setLoggerConfig(loggerConfig.toString());
			} catch (IOException e) {
				getLoggerForm().setLoggerConfig("IO Exception");
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						getLoggerForm().setLoggerConfig("IO Exception during close");
					}
				}
			}
		} catch (FileNotFoundException e) {
			getLoggerForm().setLoggerConfig("File not Found");
		}
	}

	@Secured({ "BF_CONTROL_LOGGER" })
	public String uploadLogger() {
		setLog4jConfig(getLoggerForm().getFile());
		
		Configurator.setLevel("com.baseframework.web.filters.JSPIncludeFilter", Level.DEBUG);
		
		getServletContext().setAttribute("CURRENT_LOG4J_CONFIG", getLoggerForm().getLoggerConfig());
		return "redirect_onLoad";
	}

	public LoggerForm getLoggerForm() {
		if (this.loggerForm == null) {
			this.loggerForm = new LoggerForm();
		}
		return this.loggerForm;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
}
