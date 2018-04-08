package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mase2.mase2_project.data.BaseDataDAO;
import com.mase2.mase2_project.graph_model.ImsiNode;
import com.mase2.mase2_project.graph_model.NodeEventIdCouseCode;
import com.mase2.mase2_project.model.BaseData;

import com.mase2.mase2_project.util.AutoComObject;

import com.mase2.mase2_project.model.DateAndDurationForIMSI;

import com.mase2.mase2_project.util.DateParam;
import com.mase2.mase2_project.util.DurationAndCountObject;
import com.mase2.mase2_project.util.FailureCountObject;
import com.mase2.mase2_project.util.IMSIObject;
import com.mase2.mase2_project.util.SecurityCheck;
import com.mase2.mase2_project.util.TopTenFailuresObject;
import com.mase2.mase2_project.util.TopTenIMSIsObject;
import com.mase2.mase2_project.util.UniqueEventAndCauseObject;

@Path("/basedatas")
@Stateless
@LocalBean
public class BaseDataWS {

	@EJB
	SecurityCheck securityCheck;
	@EJB
	private BaseDataDAO baseDataDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {

			final List<BaseData> baseData = baseDataDAO.getAllBaseData();
			return Response.status(200).entity(baseData).build();

		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/aci/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response autoCopleteImsi(@Context HttpHeaders httpHeaders, @QueryParam("term") final String imsi) {
		final List<String> baseData = baseDataDAO.getAllImsi(imsi);
		return Response.status(200).entity(baseData).build();
	}

	@GET
	@Path("/acm/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response autoCopleteModel(@Context HttpHeaders httpHeaders, @QueryParam("term") final String model) {
		final List<String> baseData = baseDataDAO.getAllModels(model);
		return Response.status(200).entity(baseData).build();
	}
	@GET
	@Path("/afc/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response autoCopleteFailureClass(@Context HttpHeaders httpHeaders, @QueryParam("term") final String failureClass) {		
		final List<AutoComObject> baseData = baseDataDAO.getAllFailureClasses(failureClass);
		return Response.status(200).entity(baseData).build();
	}

	@GET
	@Path("/csr/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByImsi(@Context HttpHeaders httpHeaders, @PathParam("imsi") final String imsi) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<BaseData> baseData = baseDataDAO.getBaseDataForIMSI(imsi);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}

	}

	@GET
	@Path("/csr/unique/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUniqueCodesByImsi(@Context HttpHeaders httpHeaders, @PathParam("imsi") final String imsi) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<IMSIObject> baseData = baseDataDAO.getUniqueCauseCodesForIMSI(imsi);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}

	}

	@GET
	@Path("/se/QueryDates")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByAllImsiWithFailures(@Context HttpHeaders httpHeaders, @QueryParam("StartDate") final DateParam startDateParam, @QueryParam("EndDate") final DateParam endDateParam) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<IMSIObject> baseData = baseDataDAO.getAllImsiWithFailures(startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/se/{model}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByCellIdAndDateTime(@Context HttpHeaders httpHeaders, @PathParam("model") final String model, @QueryParam("StartDate") final DateParam startDateParam,
			@QueryParam("EndDate") final DateParam endDateParam) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<FailureCountObject> baseData = baseDataDAO.getCountForCellIdAndDate(model, startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/fc/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByIMSIAndDateTime(@Context HttpHeaders httpHeaders, @PathParam("imsi") final String imsi, @QueryParam("StartDate") final DateParam startDateParam,
			@QueryParam("EndDate") final DateParam endDateParam) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<FailureCountObject> baseData = baseDataDAO.getCountForIMSIAndDate(imsi, startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/nme/query")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByDateTime(@Context HttpHeaders httpHeaders, @QueryParam("StartDate") final DateParam startDateParam, @QueryParam("EndDate") final DateParam endDateParam) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<DurationAndCountObject> baseData = baseDataDAO.getSumDurationAndCountForEachIMSI(startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/nme/{model}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByUniqueModelCombinations(@Context HttpHeaders httpHeaders, @PathParam("model") final String model) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<UniqueEventAndCauseObject> baseData = baseDataDAO.getUniqueEventIdAndCauseCodeForModel(model);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/nme/querytopten")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTopTenFailuresByDateTime(@Context HttpHeaders httpHeaders, @QueryParam("StartDate") final DateParam startDateParam, @QueryParam("EndDate") final DateParam endDateParam) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<TopTenFailuresObject> baseData = baseDataDAO.getTopTenFailures(startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/nme/countfailures")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCountFailures(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<Integer> baseData = baseDataDAO.getTotalNumberOfFailures();
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}


	



	@GET
	@Path("/graph/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByImsiGraph(@Context HttpHeaders httpHeaders, @PathParam("imsi") final String imsi) {
		// if (securityCheck.hasRole(httpHeaders, "admin")) {
		final List<NodeEventIdCouseCode> baseData = baseDataDAO.getBaseDataForIMSIGraph(imsi);
		ImsiNode imsiNode = new ImsiNode();
		imsiNode.setName(imsi);
		imsiNode.setChildren(baseData);

		return Response.status(200).entity(imsiNode).build();
		// } else {
		// return SecurityCheck.ACCESS_DENY;
		// }

	}

	@GET
	@Path("/graph/{imsi}/{StartDate}/{EndDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByIMSIStartDateAndEndDate(@Context HttpHeaders httpHeaders, @PathParam("imsi") final String imsi, @PathParam("StartDate") final DateParam startDateParam,
			@PathParam("EndDate") final DateParam endDateParam) {

		System.out.println("BaseDataWS.findByIMSIStartDateAndEndDate() **********");
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<DateAndDurationForIMSI> baseData = baseDataDAO.getDateAndDurationOfFailuresForIMSI(imsi, startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/nme/querytoptenimsi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTopTenIMSIsThatHadCallFailuresByDateTime(@Context HttpHeaders httpHeaders, @QueryParam("StartDate") final DateParam startDateParam,
			@QueryParam("EndDate") final DateParam endDateParam) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<TopTenIMSIsObject> baseData = baseDataDAO.getTopTenIMSIs(startDateParam, endDateParam);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/nme/querygivenfailurecauseclass/{failure}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findIMSIForGivenFailureCauseClass(@Context HttpHeaders httpHeaders, @PathParam("failure") final String failure) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<IMSIObject> baseData = baseDataDAO.getIMSIsForGivenFaiureCauseClass(failure);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}

	}
	
	@GET
	@Path("/mg/{model}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByUniqueModelGraphCombinations(@Context HttpHeaders httpHeaders,
			@PathParam("model") final String model) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<UniqueEventAndCauseObject> baseData = baseDataDAO.getUniqueEventIdAndCauseCodeForModelGraph(model);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}
	
	@GET
	@Path("/mgd/{model}/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByUniqueModelGraphDrilldownCombinations(@Context HttpHeaders httpHeaders,
			@PathParam("model") final String model, @PathParam("eventId") final String eventId) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<UniqueEventAndCauseObject> baseData = baseDataDAO.getUniqueEventIdAndCauseCodeForModelGraphDrilldown(model, eventId);
			return Response.status(200).entity(baseData).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}
	
}