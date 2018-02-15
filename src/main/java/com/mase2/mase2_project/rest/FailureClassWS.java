package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.model.FailureClass;

@Path("/failureclass")
@Stateless
@LocalBean
public class FailureClassWS {
	
	@EJB
	private FailureClassDAO failureClassDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllFailureClasses() {
		List<FailureClass> failureClasses=failureClassDao.getAllFailureClasses();
		return Response.status(200).entity(failureClasses).build();
	}
	
	/*
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findByFailureClass(@PathParam("id") int failureClassParam) {
		FailureClass failureClass = failureClassDao.getFailureClass(failureClassParam);
		System.out.println("emfind test"+failureClassParam);
		return Response.status(200).entity(failureClass).build();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveFailureClass(FailureClass failureClass) {
		failureClassDao.save(failureClass);
		return Response.status(201).entity(failureClass).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateFailureClass(FailureClass failureClass) {
		failureClassDao.update(failureClass);
		return Response.status(200).entity(failureClass).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteFailureClass(@PathParam("id") int failureClass) {
		failureClassDao.delete(failureClass);
		return Response.status(204).build();
	}
	
	@GET
	@Path("/search/{query}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByDescription(@PathParam("query") String query) {
		System.out.println("findByName: " + query);
		List<FailureClass> failureclasses=failureClassDao.getByDescription(query);
		return Response.status(200).entity(failureclasses).build();
	}
	*/

}
