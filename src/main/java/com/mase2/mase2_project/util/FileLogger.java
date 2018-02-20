package com.mase2.mase2_project.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
	private Logger logger;

	public FileLogger() {
		InitiateLogger();
	}

	public void logToFile(String logMessage) {
		logger.info(logMessage);
	}

	private void InitiateLogger() {
		logger = Logger.getLogger("InvalidEntityLog");
		FileHandler fh;

		try {

			// This block configure the logger with handler and formatter
			String filePath = initiateLogFile();
			fh = new FileHandler(filePath);
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// the following statement is used to log any messages

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String initiateLogFile() {
		String filePath = "";
		String absolutePath = new File(".").getAbsolutePath();// Get path of
																// your Project
																// Folder
		int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);// Remove dot from path
		String file = "IncorrectEntities.log";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		return filePath;
	}


}
