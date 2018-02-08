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

import com.mase2.mase2_project.model.MccMncPK;

/**
 * @author A00248213
 *
 */
@RequestScoped
@Path("/mcc_mncpks")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MccMncPKWS {

	/**
	* @param mccmncpk
	* @return
	*/
	@POST
	public Response create(final MccMncPK mccmncpk) {
		//TODO: process the given mccmncpk 
		//you may want to use the following return statement, assuming that MccMncPK#getId() or a similar method 
		//would provide the identifier to retrieve the created MccMncPK resource:
		//return Response.created(UriBuilder.fromResource(MccMncPKWS.class).path(String.valueOf(mccmncpk.getId())).build()).build();
		return Response.created(null).build();
	}

	/**
	* @param id
	* @return
	*/
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the mccmncpk 
		MccMncPK mccmncpk = null;
		if (mccmncpk == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(mccmncpk).build();
	}

	/**
	* @param startPosition
	* @param maxResult
	* @return
	*/
	@GET
	public List<MccMncPK> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the mccmncpks 
		final List<MccMncPK> mccmncpks = null;
		return mccmncpks;
	}

	/**
	* @param id
	* @param mccmncpk
	* @return
	*/
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final MccMncPK mccmncpk) {
		//TODO: process the given mccmncpk 
		return Response.noContent().build();
	}

	/**
	* @param id
	* @return
	*/
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the mccmncpk matching by the given id 
		return Response.noContent().build();
	}

}
