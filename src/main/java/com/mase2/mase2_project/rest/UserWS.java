package com.mase2.mase2_project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mase2.mase2_project.data.UserDAO;
import com.mase2.mase2_project.model.User;
import com.mase2.mase2_project.util.SecurityCheck;

@Stateless
@LocalBean

@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserWS {

	@EJB
	UserDAO userDAO;
	@EJB
	SecurityCheck securityCheck;

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpHeaders httpHeaders) {
		User logedUser = securityCheck.login(httpHeaders);
		if (logedUser.getRole() != null && !logedUser.getRole().equalsIgnoreCase("")) {
			// if (logedUser.getRole() .equals("admin")) {
			System.out.println("UserWS.login()");
			System.out.println("UserWS.login()" + logedUser.getRole() + "{\"role\": \"" + logedUser.getRole() + "\"}");
			return Response.status(200).entity("{\"role\": \"" + logedUser.getRole() + "\"}").build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	public Response listAll(@Context HttpHeaders httpHeaders) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<User> users = userDAO.getAllUsers();
			return Response.ok(users).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@POST
	public Response create(@Context HttpHeaders httpHeaders, final User user) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			userDAO.save(user);
			return Response.ok().build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/{email}")
	public Response findByEmail(@Context HttpHeaders httpHeaders, @PathParam("email") final String email) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			List<User> users = userDAO.findByEmail(email);
			if (users == null || users.isEmpty()) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(users).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@Context HttpHeaders httpHeaders, @PathParam("id") final int id) {
		if (securityCheck.hasRole(httpHeaders, "admin")) {
			User user = userDAO.findById(id);
			if (user == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(user).build();
		} else {
			return SecurityCheck.ACCESS_DENY;
		}
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@Context HttpHeaders httpHeaders, @PathParam("id") Long id, final User user) {
		userDAO.update(user);
		return Response.ok().build();
	}

	// @DELETE
	// @Path("/{id:[0-9][0-9]*}")
	// public Response deleteById(@PathParam("id") final Long id) {
	// // TODO: process the user matching by the given id
	// return Response.noContent().build();
	// }

}
