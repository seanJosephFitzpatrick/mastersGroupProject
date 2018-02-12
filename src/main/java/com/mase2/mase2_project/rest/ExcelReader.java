package com.mase2.mase2_project.rest;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.test.utils.UeUtilsDAO;
import com.mase2.mase2_project.test.utils.UtilsDAO;

import jxl.Cell;
import jxl.CellType;
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
	
	
	@POST
	public Response create() {
		this.importData();
		 
		return Response.noContent().build();
	}
	
	public void importData(){
        File f = new File("C:\\Users\\a00199480\\Downloads\\test.xls");
        
       
        
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
            
            //s=wb.getSheet(0);
            
            
            

           
                
               
            
        
        }catch(Exception e2){
        	System.out.println("Exception");
        	
        }
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
