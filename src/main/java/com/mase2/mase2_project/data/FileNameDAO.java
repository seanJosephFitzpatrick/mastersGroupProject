package com.mase2.mase2_project.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class FileNameDAO {

	public List<String> getFileNames() {
		File folder = new File(".\\DataFiles");
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()&&listOfFiles[i].getName().endsWith(".xls")) {
		    	  fileNames.add(listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		    	  
		      }
		    }
		    return fileNames;
	}

}
