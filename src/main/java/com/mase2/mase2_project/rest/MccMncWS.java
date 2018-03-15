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

import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.util.SecurityCheck;

@Path("/mcc_mnc")
@Stateless
@LocalBean
public class MccMncWS {

	@EJB
	private MccMncDAO mcc_mncDao;
	@EJB
	SecurityCheck securityCheck;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllMccMncs(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			System.out.println("Get all mcc_mnc");
			final List<MccMnc> mccMncs = mcc_mncDao.getAllMcc_Mncs();
			return Response.status(200).entity(mccMncs).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}
}
