/**
 * 
 */
package com.mase2.mase2_project.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mase2.mase2_project.data.BaseDataDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.util.DateParam;




/**
 * @author A00248115
 *
 */

@Path("/basedatas")
@Stateless
@LocalBean
public class BaseDataWS {
	
	@EJB
	private BaseDataDAO baseDataDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		final List<BaseData> baseData=baseDataDAO.getAllBaseData();
		return Response.status(200).entity(baseData).build();
	}

	@GET
	@Path("/csr/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByImsi(@PathParam("imsi") final String imsi) {
		final List<BaseData> baseData=baseDataDAO.getBaseDataForIMSI(imsi);
		return Response.status(200).entity(baseData).build();

	}
	@GET
	@Path("/se/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByAllImsiWithFailures(@PathParam("date") final DateParam dateParam) {
		final List<BaseData> baseData=baseDataDAO.getAllImsiWithFailures(dateParam);
		return Response.status(200).entity(baseData).build();

	}
	@GET
	@Path("/se/query")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByCellIdAndDateTime(@QueryParam("cellId") final String cellId,@QueryParam("date") final DateParam dateParam) {
		final List<BaseData> baseData=baseDataDAO.getCountForCellIdAndDate(cellId,dateParam);
		return Response.status(200).entity(baseData).build();

	}
	@GET
	@Path("/nme/query")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByDateTime(@QueryParam("date") final DateParam dateParam) {
		final List<BaseData> baseData=baseDataDAO.getSumDurationAndCountForEachIMSI(dateParam);
		return Response.status(200).entity(baseData).build();

	}
	@GET
	@Path("/nme/{cellId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByDateTime(@PathParam("cellId") final String cellId) {
		final List<BaseData> baseData=baseDataDAO.getUniqueEventIdAndCauseCodeForModel(cellId);
		return Response.status(200).entity(baseData).build();

	}


}
