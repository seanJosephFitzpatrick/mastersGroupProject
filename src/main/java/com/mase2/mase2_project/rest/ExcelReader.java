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
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
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
	private FailureClass failureClassRow= new FailureClass();
	private EventCause eventCauseRow= new EventCause();
	private MccMnc mccMncRow= new MccMnc();
	private Ue ueRow=new Ue();
	private MccMncPK mccMncPK = new MccMncPK();
	private EventCausePK eventCausePK = new EventCausePK();

	
	
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
        	e2.printStackTrace();
        	
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
		    System.out.println("boooooooooom");
		    baseData.createRow(cells,eventCauseRow,failureClassRow,ueRow,mccMncRow);
		    baseDataDAO.save(baseData);
		    }
		}
		
	}

	private boolean checkForeignKeysExist(ArrayList<String> cells) {
		if(checkFailureClassForeignKeys(cells)){
			System.out.println("First check");
			if(checkEventCauseForeignKeys(cells)){
				System.out.println("Second check");
				if(checkUeTypeForeignKeys(cells)){
					System.out.println("Third check");
					if(checkMccMncForeignKeys(cells)){
						System.out.println("Fourth check");
						return true;
					}
				}
			}
		}
		return false;
		
		
		
		
	}

	private boolean checkMccMncForeignKeys(ArrayList<String> cells) {
		for (MccMnc mccMnc : mccMncData) {
			if(cells.get(4).equalsIgnoreCase(Integer.toString(mccMnc.getId().getMcc()))){
				if(cells.get(5).equalsIgnoreCase(Integer.toString(mccMnc.getId().getMnc()))){
					mccMncRow=mccMnc;
					return true;	
				}
				
			}
				
		}
		return false;
	}

	private boolean checkUeTypeForeignKeys(ArrayList<String> cells) {
		for (Ue ue : UeData) {
			if(cells.get(3).equalsIgnoreCase(Integer.toString(ue.getTac()))){
				ueRow=ue;
				return true;
			}
		}
		return false;
	}

	private boolean checkFailureClassForeignKeys(ArrayList<String> cells) {
		for (FailureClass failureClass : failureClassData) {
			if(cells.get(2).equalsIgnoreCase(Integer.toString(failureClass.getFailureClass()))){
				System.out.println("Inside failure class check");
				failureClassRow=failureClass;
				return true;
			}
		}
		return false;

	}

	private boolean checkEventCauseForeignKeys(ArrayList<String> cells) {
		for (EventCause eventCause : eventCauseData) {
			if(cells.get(1).equalsIgnoreCase(Integer.toString(eventCause.getId().getEventId()))){
				if(cells.get(8).equalsIgnoreCase(Integer.toString(eventCause.getId().getEventCode()))){
					eventCauseRow=eventCause;
					return true;
				}
			}	
		}
		return false;
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
