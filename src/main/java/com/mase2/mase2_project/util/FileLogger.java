package com.mase2.mase2_project.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
	private static Logger logger;


	public void logToFile(final String logMessage) {
		logger.info(logMessage);
	}

	static {
		logger = Logger.getLogger("InvalidEntityLog");
		FileHandler fileHandler;

		try {

			// This block configure the logger with handler and formatter
			final String filePath = initiateLogFile();
			fileHandler = new FileHandler(filePath);
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
			final SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);

			// the following statement is used to log any messages

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String initiateLogFile() {
		String filePath = "";
		String absolutePath = new File(".").getAbsolutePath();
		final int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		final String file = "IncorrectEntities.log";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		return filePath;
	}


}
