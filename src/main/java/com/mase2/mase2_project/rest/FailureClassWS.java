package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.util.SecurityCheck;

@Path("/failureclass")
@Stateless
@LocalBean
public class FailureClassWS {

	@EJB
	private FailureClassDAO failureClassDao;

	@EJB
	SecurityCheck securityCheck;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllFailureClasses(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<FailureClass> failureClasses = failureClassDao.getAllFailureClasses();
			return Response.status(200).entity(failureClasses).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

}
