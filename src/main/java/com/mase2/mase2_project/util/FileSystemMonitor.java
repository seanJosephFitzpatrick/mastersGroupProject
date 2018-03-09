package com.mase2.mase2_project.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mase2.mase2_project.data.ExcelDAO;
import com.sun.xml.stream.xerces.util.SynchronizedSymbolTable;

@Stateless
@LocalBean
public class FileSystemMonitor {

    public static Logger log = Logger.getLogger(FileSystemMonitor.class.getName());
    @EJB
    private ExcelDAO excelDAO;
    private WatchService watcher;  
    private WatchKey key;  
    private Path dir;  
  
    public void newFolderWatcher() {  
        try {  
            watcher = FileSystems.getDefault().newWatchService();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void setMonitoredDir(String filePath) {  
  
        dir = Paths.get(filePath); 
  
        WatchEvent.Kind<?>[] events = { StandardWatchEventKinds.ENTRY_CREATE };  
        try {  
            key = dir.register(watcher, events);  
        }catch (IOException e) {  
            e.printStackTrace();  
        } 
    } 
    
    private void printEvent(WatchEvent<?> event) {
        Kind<?> kind = event.kind();
        if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
            Path pathCreated = (Path) event.context();
            log.info("Entry created: " + pathCreated + " Directory path: " + dir.toString());
            String filePath = dir.toString() + "\\" + pathCreated;
            final File excelFile = new File(filePath);
            System.out.println(filePath);
            excelDAO.autoImportAllExcelData(excelFile);
            //excelDAO.autoImportBaseDataExcelData(excelFile);
            log.info("After auto import");
    		
        }
    }
    
    @Asynchronous  
    public void startWatching() {  
  
        while (true) { 
  
            try {  
                key = watcher.take();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } catch (ClosedWatchServiceException e) {  
                e.printStackTrace();
                break;  
            }  
  
            if (key != null) {  
  
                for (WatchEvent<?> event : key.pollEvents()) {  
                	printEvent(event);
                } 
                
                if (!key.reset()) {  
                    key.cancel();
                    try {
						watcher.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
                    break;  
                }  
            }  
        }  
    }  
}
