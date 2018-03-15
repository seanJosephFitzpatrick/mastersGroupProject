package com.mase2.mase2_project.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mase2.mase2_project.data.ExcelDAO;
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
	SecurityCheck securityCheck;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)

	public Response importAllData(@Context HttpHeaders httpHeaders) {

		if (securityCheck.hasRole(httpHeaders, "admin")) {
			tableClearer.deleteAllTables();
			final int[] validAndInvalidRows = excelDAO.importAllExcelData();
			return Response.status(200).entity(validAndInvalidRows).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}
}
