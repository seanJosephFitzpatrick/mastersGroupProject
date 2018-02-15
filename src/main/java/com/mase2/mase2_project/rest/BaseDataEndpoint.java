/**
 * 
 */
package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.mase2.mase2_project.data.BaseDataDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.MccMnc;



/**
 * @author A00248115
 *
 */

@Path("/basedatas")
@Stateless
@LocalBean
public class BaseDataEndpoint {
	
	@EJB
	private BaseDataDAO baseDataDAO;
	
	/**
	* @param startPosition
	* @param maxResult
	* @return
	*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<BaseData> baseData=baseDataDAO.getAllBaseData();
		return Response.status(200).entity(baseData).build();
	}

//	/**
//	* @param basedata
//	* @return
//	*/
//	@POST
//	public Response create(final BaseData basedata) {
//		//TODO: process the given basedata 
//		//here we use BaseData#getId(), assuming that it provides the identifier to retrieve the created BaseData resource. 
//		return Response
//				.created(UriBuilder.fromResource(BaseDataEndpoint.class).path(String.valueOf(basedata.getId())).build())
//				.build();
//	}
//
//	/**
//	* @param id
//	* @return
//	*/
//	@GET
//	@Path("/{id:[0-9][0-9]*}")
//	public Response findById(@PathParam("id") final Long id) {
//		//TODO: retrieve the basedata 
//		BaseData basedata = null;
//		if (basedata == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		return Response.ok(basedata).build();
//	}
//
//	/**
//	* @param id
//	* @param basedata
//	* @return
//	*/
//	@PUT
//	@Path("/{id:[0-9][0-9]*}")
//	public Response update(@PathParam("id") Long id, final BaseData basedata) {
//		//TODO: process the given basedata 
//		return Response.noContent().build();
//	}
//
//	/**
//	* @param id
//	* @return
//	*/
//	@DELETE
//	@Path("/{id:[0-9][0-9]*}")
//	public Response deleteById(@PathParam("id") final Long id) {
//		//TODO: process the basedata matching by the given id 
//		return Response.noContent().build();
//	}

}
