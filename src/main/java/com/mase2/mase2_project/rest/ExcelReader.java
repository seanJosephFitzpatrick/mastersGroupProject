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
	private List<FailureClass> failureClassData = new ArrayList<FailureClass>();
	private List<EventCause> eventCauseData = new ArrayList<EventCause>();
	private List<MccMnc> mccMncData = new ArrayList<MccMnc>();
	private List<Ue> UeData = new ArrayList<Ue>();
	private FailureClass failureClassRow = new FailureClass();
	private EventCause eventCauseRow = new EventCause();
	private MccMnc mccMncRow = new MccMnc();
	private Ue ueRow = new Ue();
	private FileLogger fileLogger = new FileLogger();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importAllData() {

		int[] validAndInvalidRows = this.importExcelData();

		return Response.status(200).entity(validAndInvalidRows).build();
	}

	@GET
	@Path("/basedata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importBaseData() {
		File f = initiateExcelFile();
		int[] validAndInvalidRows = null;
		Workbook wb;
		try {
			wb = Workbook.getWorkbook(f);
			Sheet s = wb.getSheet(0);
			this.retrieveParentTableData();
			validAndInvalidRows = this.importDataBaseData(s);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.noContent().build();
		}
		return Response.status(200).entity(validAndInvalidRows).build();
	}

	private int[] importExcelData() {
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
		String absolutePath = new File(".").getAbsolutePath();// Get path of
																// your Project
																// Folder
		int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);// Remove dot from path
		String file = "test.xls";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		System.out.println(filePath);
		File f = new File(filePath);
		return f;
	}

	private void retrieveParentTableData() {
		if (failureClassData.size() != 0) {
			return;
		}
		failureClassData = failureClassDAO.getAllFailureClasses();
		eventCauseData = eventCauseDAO.getAllEventCauses();
		UeData = ueDAO.getAllUes();
		mccMncData = mcc_mncDao.getAllMcc_Mncs();
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
				baseData.createRow(cells, eventCauseRow, failureClassRow, ueRow, mccMncRow);
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
		if (checkFailureClassForeignKeys(cells)) {
			if (checkEventCauseForeignKeys(cells)) {
				if (checkUeTypeForeignKeys(cells)) {
					if (checkMccMncForeignKeys(cells)) {
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

	private boolean checkMccMncForeignKeys(ArrayList<String> cells) {
		for (MccMnc mccMnc : mccMncData) {
			if (cells.get(4).equalsIgnoreCase(mccMnc.getId().getMcc())) {
				if (cells.get(5).equalsIgnoreCase(mccMnc.getId().getMnc())) {
					mccMncRow = mccMnc;
					return true;
				}

			}

		}
		return false;
	}

	private boolean checkUeTypeForeignKeys(ArrayList<String> cells) {
		for (Ue ue : UeData) {
			if (cells.get(3).equalsIgnoreCase(ue.getTac())) {
				ueRow = ue;
				return true;
			}
		}
		return false;
	}

	private boolean checkFailureClassForeignKeys(ArrayList<String> cells) {
		if (cells.get(2).equalsIgnoreCase("(null)")) {
			failureClassRow = null;
			return true;
		} else {
			for (FailureClass failureClass : failureClassData) {
				if (cells.get(2).equalsIgnoreCase(failureClass.getFailureClass())) {
					failureClassRow = failureClass;
					return true;
				}
			}
		}
		return false;

	}

	private boolean checkEventCauseForeignKeys(ArrayList<String> cells) {
		if (cells.get(8).equalsIgnoreCase("(null)")) {
			for (EventCause eventCause : eventCauseData) {
				if (cells.get(1).equalsIgnoreCase(eventCause.getId().getEventId())) {
					// placeholder for future datasets
					eventCauseRow = eventCause;
					return true;

				}
			}
		} else {
			for (EventCause eventCause : eventCauseData) {
				if (cells.get(1).equalsIgnoreCase(eventCause.getId().getEventId())) {
					if (cells.get(8).equalsIgnoreCase(eventCause.getId().getEventCode())) {
						eventCauseRow = eventCause;
						return true;
					}
				}
			}
		}
		return false;
	}

	private void importDataEventCause(Sheet s) {
		// utilsDAO.deleteTableEventCause();
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
