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
       fileSystemMonitor.newFolderWatcher();  
       fileSystemMonitor.setMonitoredDir(initiateDirectoryPath());  
       fileSystemMonitor.startWatching();  
        
   } 
   
	private String initiateDirectoryPath() {
		String filePath = "";
		String absolutePath = new File(".").getAbsolutePath();
		final int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		filePath = (absolutePath + "/DataFiles");
		filePath = filePath.replace("\\", "/");
		return filePath;
	}

}
