/**
 * 
 */
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

import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.util.SecurityCheck;

@Path("/ue")
@Stateless
@LocalBean
public class UeWS {

	@EJB
	private UeDAO ueDao;
	@EJB
	SecurityCheck securityCheck;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUes(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<Ue> ues = ueDao.getAllUes();
			return Response.status(200).entity(ues).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}
}
