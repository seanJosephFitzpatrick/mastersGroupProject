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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.model.Ue;

/**
 * @author A00199480
 *
 */
@Path("/ue")
@Stateless
@LocalBean
public class UeWS {

	@EJB
	private UeDAO ueDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUes() {
		final List<Ue> ues=ueDao.getAllUes();
		return Response.status(200).entity(ues).build();
	}
	
//
//	@GET
//	@Produces({ MediaType.APPLICATION_JSON })
//	@Path("/{id}")
//	public Response findBytac(@PathParam("id") int ueParam) {
//		Ue ue = ueDao.getUe(ueParam);
//		System.out.println("emfind test"+ueParam);
//		return Response.status(200).entity(ue).build();
//	}
//	
//	@POST
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response saveUe(Ue ue) {
//		ueDao.save(ue);
//		return Response.status(201).entity(ue).build();
//	}
//	
//	@PUT
//	@Path("/{id}")
//	@Consumes("application/json")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response updateUe(Ue ue) {
//		ueDao.update(ue);
//		return Response.status(200).entity(ue).build();
//	}
//	
//	@GET
//	@Path("/search/{query}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response findByMarketingName(@PathParam("query") String query) {
//		System.out.println("findByMarketingName: " + query);
//		List<Ue> ue=ueDao.getByMarketingName(query);
//		return Response.status(200).entity(ue).build();
//	}
//	
//	@GET
//	@Path("/search/{query}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response findByManufacturer(@PathParam("query") String query) {
//		System.out.println("findByManufacturer: " + query);
//		List<Ue> ue=ueDao.getByManufacturer(query);
//		return Response.status(200).entity(ue).build();
//	}
//	
//	@GET
//	@Path("/search/{query}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response findByAccessCapability(@PathParam("query") String query) {
//		System.out.println("findByAccessCapability: " + query);
//		List<Ue> ue=ueDao.getByAccessCapability(query);
//		return Response.status(200).entity(ue).build();
//	}
//	

}
