/**
 * 
 */
package com.mase2.mase2_project.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.mase2.mase2_project.model.MccMnc;

/**
 * @author A00248213
 *
 */
@RequestScoped
@Path("/mcc_mnc")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MccMncWS {

	/**
	* @param mccmnc
	* @return
	*/
	@POST
	public Response create(final MccMnc mccmnc) {
		//TODO: process the given mccmnc 
		//here we use MccMnc#getId(), assuming that it provides the identifier to retrieve the created MccMnc resource. 
		return Response.created(UriBuilder.fromResource(MccMncWS.class).path(String.valueOf(mccmnc.getId())).build())
				.build();
	}

	/**
	* @param id
	* @return
	*/
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the mccmnc 
		MccMnc mccmnc = null;
		if (mccmnc == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(mccmnc).build();
	}

	/**
	* @param startPosition
	* @param maxResult
	* @return
	*/
	@GET
	public List<MccMnc> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the mccmncs 
		final List<MccMnc> mccmncs = null;
		return mccmncs;
	}

	/**
	* @param id
	* @param mccmnc
	* @return
	*/
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final MccMnc mccmnc) {
		//TODO: process the given mccmnc 
		return Response.noContent().build();
	}

	/**
	* @param id
	* @return
	*/
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the mccmnc matching by the given id 
		return Response.noContent().build();
	}

}
