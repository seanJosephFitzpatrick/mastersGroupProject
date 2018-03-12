package com.mase2.mase2_project.rest;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.HttpRequest;

import com.mase2.mase2_project.data.UserDAO;
import com.mase2.mase2_project.model.User;
import com.mase2.mase2_project.util.SecurityCheck;

@Stateless
@LocalBean

@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserWS {

	private static final ServerResponse ACCESS_DENY = new ServerResponse("Access deny for this resource", 401,
			new Headers<>());

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
			return ACCESS_DENY;
		}
	}

	@GET
	public Response listAll(@Context HttpHeaders httpHeaders) {

		if (securityCheck.hasRole(httpHeaders, "admin")) {
			final List<User> users = userDAO.getAllUsers();
			return Response.ok(users).build();
		} else {
			return ACCESS_DENY;
		}
	}

	@POST
	public Response create(@Context HttpHeaders httpHeaders, final User user) {
		// TODO: process the given user
		// here we use User#getId(), assuming that it provides the identifier to
		// retrieve the created User resource.

		if (securityCheck.hasRole(httpHeaders, "admin")) {
			userDAO.save(user);

			return Response.created(UriBuilder.fromResource(UserWS.class).path(String.valueOf(user.getId())).build())
					.build();
		} else {
			return ACCESS_DENY;
		}
	}

	@GET
	@Path("/{email}")
	public Response findByEmail(@PathParam("email") final String email) {
		// TODO: retrieve the user
		List<User> users = userDAO.findByEmail(email);
		if (users == null || users.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(users).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		// TODO: retrieve the user
		User user = userDAO.findById(id);
		if (user == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final User user) {
		// TODO: process the given user
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		// TODO: process the user matching by the given id
		return Response.noContent().build();
	}

}
