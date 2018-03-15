package com.mase2.mase2_project.rest;

import java.util.List;

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

import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.util.SecurityCheck;

@Path("/eventcauses")
@Stateless
@LocalBean
public class EventCauseWS {

	@EJB
	private EventCauseDAO eventCauseDAO;
	@EJB
	SecurityCheck securityCheck;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<EventCause> eventCause = eventCauseDAO.getAllEventCauses();
			return Response.status(200).entity(eventCause).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

}
