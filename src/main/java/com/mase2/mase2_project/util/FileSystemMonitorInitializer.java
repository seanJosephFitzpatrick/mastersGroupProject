package com.mase2.mase2_project.util;

import java.io.File;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup  
@Singleton  
public class FileSystemMonitorInitializer { 
    
   @EJB  
   private FileSystemMonitor fileSystemMonitor;   
    
   @PostConstruct  
   public void init(){  
	   createDirectory(".\\DataFiles\\imports");
       fileSystemMonitor.newFolderWatcher();  
       fileSystemMonitor.setMonitoredDir(initiateDirectoryPath());  
       fileSystemMonitor.startWatching();       
   } 
   
	private String initiateDirectoryPath() {
		String filePath = "";
		String absolutePath = new File(".").getAbsolutePath();
		final int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		filePath = (absolutePath + "/DataFiles/imports");
		filePath = filePath.replace("\\", "/");
		return filePath;
	}
	
    private static File createDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
		return dir;     
    }

}
