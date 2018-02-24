package com.mase2.mase2_project.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
import com.mase2.mase2_project.util.FileLogger;
import com.mase2.mase2_project.util.InvalidEntity;
import com.mase2.mase2_project.util.TableClearer;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Path("/importdata")
@Stateless
@DeclareRoles({"admin"})
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

	private final FileLogger fileLogger = new FileLogger();
	private final Validator validator = new Validator();

	@GET
	@Path("/all")
	@RolesAllowed({"admin"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response importAllData() {
		tableClearer.deleteAllTables();
		final int[] validAndInvalidRows = this.importAllExcelData();

		return Response.status(200).entity(validAndInvalidRows).build();
	}

	@GET
	@Path("/basedata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importBaseData() {
		tableClearer.deleteBaseDataTable();
		final int[] validAndInvalidRows = importBaseDataExcelData();
		return Response.status(200).entity(validAndInvalidRows).build();
	}

	private int[] importBaseDataExcelData() {
		final File baseDataFile = initiateExcelFile();
		final Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(baseDataFile);
			final Sheet sheet = workbook.getSheet(0);
			this.retrieveParentTableData();
			return this.importDataBaseData(sheet);
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
		final File allDataFile = initiateExcelFile();

		try {
			final Workbook workbook = Workbook.getWorkbook(allDataFile);
			Sheet sheet = workbook.getSheet(4);
			this.importDataMccMnc(sheet);
			sheet = workbook.getSheet(3);
			this.importDataUE(sheet);
			sheet = workbook.getSheet(2);
			this.importDataFailureClass(sheet);
			sheet = workbook.getSheet(1);
			this.importDataEventCause(sheet);
			sheet = workbook.getSheet(0);
			this.retrieveParentTableData();
			return this.importDataBaseData(sheet);
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
		final int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		final String file = "test.xls";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		System.out.println(filePath);
		final File excelFile = new File(filePath);
		return excelFile;
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

	private int[] importDataBaseData(final Sheet sheet) {
		final int row = sheet.getRows();
		final int col = sheet.getColumns();
		int[] validAndInvalidRows = new int[2];

		final ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			final BaseData baseData = new BaseData();
			for (int j = 0; j < col; j++) {
				final Cell cell = sheet.getCell(j, i1);
				cells.add(cell.getContents());
			}
			if (checkForeignKeysExist(cells)) {
				baseData.createRow(cells, validator.getEventCauseRow(), validator.getFailureClassRow(), validator.getUeRow(), validator.getMccMncRow());
				if(validator.validateBase_data(baseData)){
					baseDataDAO.save(baseData);
					validAndInvalidRows[0]++;
				} else {
					validAndInvalidRows[1]++;
				}
			} else {
				validAndInvalidRows[1]++;
			}
		}
		return validAndInvalidRows;

	}

	private boolean checkForeignKeysExist(final List<String> cells) {
		final InvalidEntity invalidEntity = new InvalidEntity();
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

	private void importDataEventCause(final Sheet sheet) {
		final int row = sheet.getRows();
		final int col = sheet.getColumns();
		final List<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			final EventCause eventCause = new EventCause();
			for (int j = 0; j < col; j++) {
				final Cell cell = sheet.getCell(j, i1);
				cells.add(cell.getContents());

			}
			eventCause.createRow(cells);
			if(validator.validateEventCause(eventCause)){
				eventCauseDAO.save(eventCause);
			}
		}

	}

	private void importDataFailureClass(final Sheet sheet) {
		final int row = sheet.getRows();
		final int col = sheet.getColumns();
		final List<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			final FailureClass failureClass = new FailureClass();
			for (int j = 0; j < col; j++) {
				final Cell cell = sheet.getCell(j, i1);
				cells.add(cell.getContents());

			}
			failureClass.createRow(cells);
			if(validator.validateFailureClass(failureClass)){
				failureClassDAO.save(failureClass);
			}
		}

	}

	private void importDataUE(final Sheet sheet) {
		// ueUtilsDAO.deleteTable();
		final int row = sheet.getRows();
		final int col = sheet.getColumns();
		final List<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			final Ue ue = new Ue();
			for (int j = 0; j < col; j++) {
				final Cell cell = sheet.getCell(j, i1);
				cells.add(cell.getContents());

			}
			ue.createRow(cells);
			if(validator.validateUe(ue)){
				ueDAO.save(ue);
			}
		}

	}

	private void importDataMccMnc(final Sheet sheet) {
		// utilsDAO.deleteTable();
		final int row = sheet.getRows();
		final int col = sheet.getColumns();
		final List<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			final MccMnc mccMnc = new MccMnc();
			for (int j = 0; j < col; j++) {
				final Cell cell = sheet.getCell(j, i1);
				cells.add(cell.getContents());

			}
			mccMnc.createRow(cells);
			if(validator.validateMcc_Mnc(mccMnc)){
				mcc_mncDao.save(mccMnc);
			}
		}
	}

}
