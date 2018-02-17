package com.mase2.mase2_project.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
import jxl.read.biff.BiffException;

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
	private EventCause eventCauseNull = new EventCause();
	private FailureClass failureClassNull = new FailureClass();


	@GET
	@Path("/all")
	public Response importAllData() {
		this.importExcelData(); 
		return Response.noContent().build();
	}
	@GET
	@Path("/basedata")
	public Response importBaseData() {
		File f = initiateExcelFile();
		Workbook wb;
		try {
			wb = Workbook.getWorkbook(f);
			Sheet s=wb.getSheet(0);
			this.retrieveParentTableData();
			this.importDataBaseData(s);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.noContent().build();
	}

	public void importExcelData(){
		File f = initiateExcelFile();

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
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File initiateExcelFile() {
		String filePath="";
		String absolutePath = new File(".").getAbsolutePath();//Get path of your Project Folder
		int last = absolutePath.length()-1;
		absolutePath = absolutePath.substring(0, last);//Remove dot from path
		String file =  "test.xls";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		System.out.println(filePath);
		File f = new File(filePath);
		return f;
	}

	private void retrieveParentTableData() {
		if(failureClassData.size()!=0){
			return;
		}
		failureClassNull.setFailureClass(7777);
		failureClassNull.setDescription("");
		failureClassDAO.save(failureClassNull);
		EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventId(7777);
		eventCausePK.setEventCode(7777);
		eventCauseNull.setId(eventCausePK);
		eventCauseNull.setDescription("");
		eventCauseDAO.save(eventCauseNull);
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
				baseData.createRow(cells,eventCauseRow,failureClassRow,ueRow,mccMncRow);
				baseDataDAO.save(baseData);
			}
		}

	}

	private boolean checkForeignKeysExist(ArrayList<String> cells) {
		if(checkFailureClassForeignKeys(cells)){
			if(checkEventCauseForeignKeys(cells)){
				if(checkUeTypeForeignKeys(cells)){
					if(checkMccMncForeignKeys(cells)){
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
		if(cells.get(2).equalsIgnoreCase("(null)")){
			failureClassRow=failureClassNull;
			return true;
		}else
		{
			for (FailureClass failureClass : failureClassData) {
				if(cells.get(2).equalsIgnoreCase(Integer.toString(failureClass.getFailureClass()))){
					failureClassRow=failureClass;
					return true;
				}
			}
		}
		return false;

	}

	private boolean checkEventCauseForeignKeys(ArrayList<String> cells) {
		if(cells.get(8).equalsIgnoreCase("(null)")){
			eventCauseRow=eventCauseNull;
			return true;
		}else
		{
			for (EventCause eventCause : eventCauseData) {
				if(cells.get(1).equalsIgnoreCase(Integer.toString(eventCause.getId().getEventId()))){
					if(cells.get(8).equalsIgnoreCase(Integer.toString(eventCause.getId().getEventCode()))||cells.get(8).equalsIgnoreCase("(null)")){
						eventCauseRow=eventCause;
						return true;
					}
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
