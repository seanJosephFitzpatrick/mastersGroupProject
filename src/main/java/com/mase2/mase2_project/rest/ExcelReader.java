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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

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
import com.mase2.mase2_project.util.FileLogger;
import com.mase2.mase2_project.util.InvalidEntity;
import com.mase2.mase2_project.util.TableClearer;

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
	@EJB 
	private TableClearer tableClearer;
	
	private FileLogger fileLogger = new FileLogger();
	private Validator validator = new Validator();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importAllData() {
		tableClearer.deleteAllTables();
		int[] validAndInvalidRows = this.importAllExcelData();

		return Response.status(200).entity(validAndInvalidRows).build();
	}

	@GET
	@Path("/basedata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importBaseData() {
		tableClearer.deleteBaseDataTable();
		int[] validAndInvalidRows = importBaseDataExcelData();
		return Response.status(200).entity(validAndInvalidRows).build();
	}

	private int[] importBaseDataExcelData() {
		File f = initiateExcelFile();
		Workbook wb;
		try {
			wb = Workbook.getWorkbook(f);
			Sheet s = wb.getSheet(0);
			this.retrieveParentTableData();
			return this.importDataBaseData(s);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new int[2];
	}

	private int[] importAllExcelData() {
		File f = initiateExcelFile();
		
		try {
			Workbook wb = Workbook.getWorkbook(f);
			Sheet s = wb.getSheet(4);
			this.importDataMccMnc(s);
			s = wb.getSheet(3);
			this.importDataUE(s);
			s = wb.getSheet(2);
			this.importDataFailureClass(s);
			s = wb.getSheet(1);
			this.importDataEventCause(s);
			s = wb.getSheet(0);
			this.retrieveParentTableData();
			return this.importDataBaseData(s);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new int[2];

	}

	private File initiateExcelFile() {
		String filePath = "";
		String absolutePath = new File(".").getAbsolutePath();
		int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		String file = "test.xls";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		System.out.println(filePath);
		File f = new File(filePath);
		return f;
	}

	private void retrieveParentTableData() {
		if (validator.getFailureClassData() != null) {
			return;
		}
		validator.setFailureClassData(failureClassDAO.getAllFailureClasses());
		validator.setEventCauseData(eventCauseDAO.getAllEventCauses());
		validator.setUeData(ueDAO.getAllUes());
		validator.setMccMncData(mcc_mncDao.getAllMcc_Mncs());
	}

	private int[] importDataBaseData(Sheet s) {
		int row = s.getRows();
		int col = s.getColumns();
		int[] validAndInvalidRows = new int[2];

		ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			BaseData baseData = new BaseData();
			for (int j = 0; j < col; j++) {
				Cell c = s.getCell(j, i1);
				cells.add(c.getContents());
			}
			if (checkForeignKeysExist(cells)) {
				baseData.createRow(cells, validator.getEventCauseRow(), validator.getFailureClassRow(), validator.getUeRow(), validator.getMccMncRow());
				baseDataDAO.save(baseData);
				validAndInvalidRows[0]++;
			} else {
				validAndInvalidRows[1]++;
			}
		}
		return validAndInvalidRows;

	}

	private boolean checkForeignKeysExist(ArrayList<String> cells) {
		InvalidEntity invalidEntity = new InvalidEntity();
		if (validator.checkFailureClassForeignKeys(cells)) {
			if (validator.checkEventCauseForeignKeys(cells)) {
				if (validator.checkUeTypeForeignKeys(cells)) {
					if (validator.checkMccMncForeignKeys(cells)) {
						return true;
					} else {
						invalidEntity.setErrorDescription("Error - Foreign key doesn't exist in MccMnc Table (market or operator)");
					}
				} else {
					invalidEntity.setErrorDescription("Error - Foreign key doesn't exist in Ue Table (ue_type)");
				}
			} else {
				invalidEntity.setErrorDescription("Error - Foreign key doesn't exist in EventCause Table (event_id or cause_code)");
			}
		} else {
			invalidEntity.setErrorDescription("Error - Foreign key doesn't exist in FailureClass Table (failure_class)");
		}
		invalidEntity.setCells(cells);
		fileLogger.logToFile(invalidEntity.toString());
		return false;

	}

	private void importDataEventCause(Sheet s) {
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			EventCause eventCause = new EventCause();
			for (int j = 0; j < col; j++) {
				Cell c = s.getCell(j, i1);
				cells.add(c.getContents());

			}
			eventCause.createRow(cells);
			eventCauseDAO.save(eventCause);
		}

	}

	private void importDataFailureClass(Sheet s) {
		// utilsDAO.deleteTableFailureClass();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			FailureClass failureClass = new FailureClass();
			for (int j = 0; j < col; j++) {
				Cell c = s.getCell(j, i1);
				cells.add(c.getContents());

			}
			failureClass.createRow(cells);
			failureClassDAO.save(failureClass);
		}

	}

	private void importDataUE(Sheet s) {
		// ueUtilsDAO.deleteTable();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			Ue ue = new Ue();
			for (int j = 0; j < col; j++) {
				Cell c = s.getCell(j, i1);
				cells.add(c.getContents());

			}
			ue.createRow(cells);
			ueDAO.save(ue);
		}

	}

	private void importDataMccMnc(Sheet s) {
		// utilsDAO.deleteTable();
		int row = s.getRows();
		int col = s.getColumns();
		ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			MccMnc mccMnc = new MccMnc();
			for (int j = 0; j < col; j++) {
				Cell c = s.getCell(j, i1);
				cells.add(c.getContents());

			}
			mccMnc.createRow(cells);
			mcc_mncDao.save(mccMnc);
		}
	}

}
