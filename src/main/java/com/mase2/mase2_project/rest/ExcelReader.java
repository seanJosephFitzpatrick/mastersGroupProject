package com.mase2.mase2_project.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.mase2.mase2_project.data.BaseDataDAO;
import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.Ue;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Path("/importdata")
@Stateless
@LocalBean
public class ExcelReader {
	@EJB
	private MccMncDAO mcc_mncDao;
	@EJB
	private UeDAO ueDAO;
	@EJB
	private FailureClassDAO failureClassDAO;
	@EJB
	private EventCauseDAO eventCauseDAO;
	@EJB
	private BaseDataDAO baseDataDAO;
	private List<FailureClass> failureClassData = new ArrayList<FailureClass>();//temporary data holder to compare foreign check for foreign keys
	private List<EventCause> eventCauseData = new ArrayList<EventCause>();
	private List<MccMnc> mccMncData = new ArrayList<MccMnc>();
	private List<Ue> UeData = new ArrayList<Ue>();

	
	
	@POST
	public Response create() {
		this.importData();
		 
		return Response.noContent().build();
	}
	
	public void importData(){
	    String filePath="";
        String absolutePath = new File(".").getAbsolutePath();//Get path of your Project Folder
		int last = absolutePath.length()-1;
		absolutePath = absolutePath.substring(0, last);//Remove dot from path
		String file =  "test.xls";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		System.out.println(filePath);
		File f = new File(filePath);
        
       
        
        try {
            Workbook wb=Workbook.getWorkbook(f);
            Sheet s = wb.getSheet(4);
            this.importDataMccMnc(s);
           
            s=wb.getSheet(3);
            this.importDataUE(s);
            s=wb.getSheet(2);
            this.importDataFailureClass(s);
            s=wb.getSheet(1);
            this.importDataEventCause(s);
            s=wb.getSheet(0);
            this.retrieveParentTableData();
            this.importDataBaseData(s);
            
            
            

           
                
               
            
        
        }catch(Exception e2){
        	System.out.println("Exception");
        	
        }
    }

	private void retrieveParentTableData() {
		if(failureClassData.size()!=0){
			return;
		}
		failureClassData=failureClassDAO.getAllFailureClasses();
		eventCauseData=eventCauseDAO.getAllEventCauses();
		UeData=ueDAO.getAllUes();
		mccMncData=mcc_mncDao.getAllMcc_Mncs();	
	}

	public void importDataBaseData(Sheet s) {
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for(int i1=1; i1<row;i1++) {
			cells.clear();
			BaseData baseData = new BaseData();
		    for(int j=0;j<col;j++) {
		        Cell c =s.getCell(j, i1);
		        cells.add(c.getContents());
		        
		    }
		    if(checkForeignKeysExist(cells)){
		    baseData.createRow(cells);
		    baseDataDAO.save(baseData);
		    }
		}
		
	}

	private boolean checkForeignKeysExist(ArrayList<String> cells) {
		int flag=0;
		String eventIDForeignKey=null;
		String eventCodeForeignKey=null;
		for (EventCause eventCause : eventCauseData) {
			
			if(cells.get(2).equalsIgnoreCase(Integer.toString(eventCause.getId().getEventId()))){
				eventIDForeignKey = Integer.toString(eventCause.getId().getEventId());
				flag++;
			}
			if(cells.get(9).equalsIgnoreCase(Integer.toString(eventCause.getId().getEventCode()))){
				eventCodeForeignKey = Integer.toString(eventCause.getId().getEventCode());
				flag++;
			}
			if(flag==2){
				break;
			}
			
		}
		cells.set(2, eventIDForeignKey);
		cells.set(9, eventCodeForeignKey);
		String faliureClassForeignKey=null;
		for (FailureClass faliureClass : failureClassData) {
			if(cells.get(3).equalsIgnoreCase(Integer.toString(faliureClass.getFailureClass()))){
				faliureClassForeignKey=Integer.toString(faliureClass.getFailureClass());
				break;
			}
			
		}
		cells.set(3, faliureClassForeignKey);
		String ueTypeForeignKey=null;
		for (Ue ue : UeData) {
			if(cells.get(4).equalsIgnoreCase(Integer.toString(ue.getTac()))){
				ueTypeForeignKey=Integer.toString(ue.getTac());
				break;
			}
			
		}
		cells.set(4, ueTypeForeignKey);
		flag=0;
		String marketForeignKey=null;
		String operatorForeignKey=null;
		for (MccMnc mccMnc : mccMncData) {
			
			if(cells.get(5).equalsIgnoreCase(Integer.toString(mccMnc.getId().getMcc()))){
				marketForeignKey = Integer.toString(mccMnc.getId().getMcc());
				flag++;
			}
			if(cells.get(6).equalsIgnoreCase(Integer.toString(mccMnc.getId().getMnc()))){
				operatorForeignKey = Integer.toString(mccMnc.getId().getMnc());
				flag++;
			}
			if(flag==2){
				break;
			}
			
		}
		cells.set(5, marketForeignKey);
		cells.set(6, operatorForeignKey);
		return true;
	}

	private void importDataEventCause(Sheet s) {
		//utilsDAO.deleteTableEventCause();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for(int i1=1; i1<row;i1++) {
			cells.clear();
			EventCause eventCause = new EventCause();
		    for(int j=0;j<col;j++) {
		        Cell c =s.getCell(j, i1);
		        cells.add(c.getContents());
		        
		    }
		    eventCause.createRow(cells);
		    eventCauseDAO.save(eventCause);
		}
		
	}

	private void importDataFailureClass(Sheet s) {
		//utilsDAO.deleteTableFailureClass();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for(int i1=1; i1<row;i1++) {
			cells.clear();
			FailureClass failureClass = new FailureClass();
		    for(int j=0;j<col;j++) {
		        Cell c =s.getCell(j, i1);
		        cells.add(c.getContents());
		        
		    }
		    failureClass.createRow(cells);
		    failureClassDAO.save(failureClass);
		}
		
	}

	private void importDataUE(Sheet s) {
		//ueUtilsDAO.deleteTable();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for(int i1=1; i1<row;i1++) {
			cells.clear();
			Ue ue = new Ue();
		    for(int j=0;j<col;j++) {
		        Cell c =s.getCell(j, i1);
		        cells.add(c.getContents());
		        
		    }
		    ue.createRow(cells);
		    ueDAO.save(ue);
		}
		
	}

	private void importDataMccMnc(Sheet s) {
		//utilsDAO.deleteTable();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for(int i1=1; i1<row;i1++) {
			cells.clear();
			MccMnc mccMnc = new MccMnc();
		    for(int j=0;j<col;j++) {
		        Cell c =s.getCell(j, i1);
		        cells.add(c.getContents());
		        
		    }
		    mccMnc.createRow(cells);
		    mcc_mncDao.save(mccMnc);
		}
	}
	
}
