package com.mase2.mase2_project.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.util.FileSystemMonitor;
import com.mase2.mase2_project.util.TableClearer;
import com.mase2.mase2_project.util.Validator;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ExcelDAO {
	public static Logger log = Logger.getLogger(FileSystemMonitor.class.getName());
	@EJB
	TableClearer tableClearer;
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

	private final Validator validator = new Validator();

	public int[] importBaseDataExcelData() {
		final File baseDataFile = initiateExcelFile();
		final Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(baseDataFile);
			final Sheet sheet = workbook.getSheet(0);
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

	public int[] autoImportBaseDataExcelData(final File baseDataFile) {
		final List<FailureClass> failureClasses = failureClassDAO.getAllFailureClasses();
		if (failureClasses.isEmpty()) {
			tableClearer.deleteParentTables();
			autoImportAllExcelData(baseDataFile);
		}
		else{
		try {
			final Workbook workbook = Workbook.getWorkbook(baseDataFile);
			final Sheet sheet = workbook.getSheet(0);
			return this.importDataBaseData(sheet);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return new int[2];
	}

	public int[] autoImportAllExcelData(final File allDataFile) {
		
		// final File allDataFile = initiateFile("test.xls");
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
			return this.importDataBaseData(sheet);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new int[2];

	}

	public int[] importAllExcelData() {
		final File allDataFile = initiateExcelFile();
		final List<FailureClass> failureClasses = failureClassDAO.getAllFailureClasses();
		if (failureClasses.isEmpty()) {
			tableClearer.deleteParentTables();
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
				return this.importDataBaseData(sheet);
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new int[2];
		} else {
			return importBaseDataExcelData();
		}

	}

	public int[] manualImport(String fileName) {
		int[] validAndInvalidRows = new int[2];
		final File excelFile = initiateFile(fileName);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(".\\DataFiles\\dataImportNames.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		final File file = new File(".\\DataFiles\\dataImportNames.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file, true);
			final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String line = "";
			boolean found = false;
			while ((line = bufferedReader.readLine()) != null) {
				if ((line.equals(fileName))) {
					log.info("File already exists");
					found = true;
					break;
				}
			}
			if (!found) {
				validAndInvalidRows=autoImportBaseDataExcelData(excelFile);
				
			} else {
				tableClearer.deleteAllTables();
				validAndInvalidRows=autoImportAllExcelData(excelFile);
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
			}
			bufferedWriter.write(fileName + "\r\n");
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return validAndInvalidRows;
	}

	//////////////////////////////////////////////
	private File initiateFile(String file) {
		String filePath = "";
		String absolutePath = new File(".").getAbsolutePath();
		final int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		// final String file = "test.xls";
		filePath = (absolutePath + file);
		filePath = filePath.replace("\\", "/");
		System.out.println(filePath);
		final File excelFile = new File(filePath);
		return excelFile;
	}
	//////////////////////////////////////////////

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
		retrieveParentTableData();

		final ArrayList<String> cells = new ArrayList<String>();

		for (int i1 = 1; i1 < row; i1++) {
			cells.clear();
			final BaseData baseData = new BaseData();
			for (int j = 0; j < col; j++) {
				final Cell cell = sheet.getCell(j, i1);
				cells.add(cell.getContents());
			}
			if (validator.checkForeignKeysExist(cells)) {
				baseData.createRow(cells, validator.getEventCauseRow(), validator.getFailureClassRow(),
						validator.getUeRow(), validator.getMccMncRow());
				if (Validator.validateBase_data(baseData)) {
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
			// if(Validator.validateEventCause(eventCause)){
			eventCauseDAO.save(eventCause);
			// }
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
			// if(Validator.validateFailureClass(failureClass)){
			failureClassDAO.save(failureClass);
			// }
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
			// if(Validator.validateUe(ue)){
			ueDAO.save(ue);
			// }
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
			// if(Validator.validateMcc_Mnc(mccMnc)){
			mcc_mncDao.save(mccMnc);
			// }
		}
	}

}
