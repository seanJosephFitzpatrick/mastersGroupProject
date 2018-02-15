package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.model.EventCause;

/**
 * @author A00248114
 *
 */

//@RequestScoped
@Path("/eventcauses")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EventCauseEndpoint {
	
	@EJB
	private EventCauseDAO eventCauseDAO;
	
	@GET
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<EventCause> eventCause=eventCauseDAO.getAllEventCauses();
		return Response.status(200).entity(eventCause).build();
	}
	/*
	@POST
	public Response create(final EventCause eventcause) {
		//TODO: process the given eventcause 
		//here we use EventCause#getId(), assuming that it provides the identifier to retrieve the created EventCause resource. 
		return Response.created(
				UriBuilder.fromResource(EventCauseEndpoint.class).path(String.valueOf(eventcause.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the eventcause 
		EventCause eventcause = null;
		if (eventcause == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(eventcause).build();
	}



	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final EventCause eventcause) {
		//TODO: process the given eventcause 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the eventcause matching by the given id 
		return Response.noContent().build();
	}
	*/

}
