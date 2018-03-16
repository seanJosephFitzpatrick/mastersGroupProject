package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mase2.mase2_project.data.ExcelDAO;
import com.mase2.mase2_project.data.FileNameDAO;
import com.mase2.mase2_project.util.SecurityCheck;
import com.mase2.mase2_project.util.TableClearer;

@Path("/importdata")
@Stateless
@LocalBean
public class ImportWS {
	@EJB
	private TableClearer tableClearer;
	@EJB
	private ExcelDAO excelDAO;
	@EJB
	private FileNameDAO fileNameDAO;
	@EJB
	SecurityCheck securityCheck;


	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importAllData(@Context HttpHeaders httpHeaders) {
		int[] validAndInvalidRows;
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			tableClearer.deleteAllTables();
			validAndInvalidRows = excelDAO.importAllExcelData();
			return Response.status(200).entity(validAndInvalidRows).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}

	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response importAllData(String fileName) {
	
		final int[] validAndInvalidRows = excelDAO.manualImport(fileName);

		return Response.status(200).entity(validAndInvalidRows).build();
	}
	@GET
	@Path("/filenames")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFileNames() {
		
		final List<String> fileNames = fileNameDAO.getFileNames();
		return Response.status(200).entity(fileNames).build();
	}

	
}
