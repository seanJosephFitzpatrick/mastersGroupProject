package com.mase2.mase2_project.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.mase2.mase2_project.data.ExcelDAO;
import com.mase2.mase2_project.rest.ImportWS;


@Stateless
@LocalBean
public class FileSystemMonitor {

    public static Logger log = Logger.getLogger(FileSystemMonitor.class.getName());
    @EJB
    private ExcelDAO excelDAO;
    @EJB
    private ImportWS importWS;
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
            final File excelFile = new File(".\\DataFiles\\imports\\"+pathCreated);
            
            BufferedReader bufferedReader = null;
            final File file = new File(".\\DataFiles\\dataImportNames.txt");
            if(!file.exists()) {
	        	  try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
            
			try {
				bufferedReader = new BufferedReader(new FileReader(".\\DataFiles\\dataImportNames.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
            String line;
		    

		
		          FileWriter fileWriter;
				try {
					fileWriter = new FileWriter(file,true);
				
		          final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		          boolean found=false;
				  while ((line = bufferedReader.readLine()) != null)
				  {
				      if((line.equals(pathCreated.toString()))) {
				    	  log.info("File already exists");
				    	  found=true;
				    	  break;
				      }
				  }
				  if(!found) {
					  excelDAO.autoImportBaseDataExcelData(excelFile);
			    	  bufferedWriter.write(pathCreated.toString()+"\r\n");
			          bufferedWriter.close();
			          log.info("Dataset Imported");
				  }
				  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
    }
        
    @Asynchronous  
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  
                for (final WatchEvent<?> event : key.pollEvents()) {  
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