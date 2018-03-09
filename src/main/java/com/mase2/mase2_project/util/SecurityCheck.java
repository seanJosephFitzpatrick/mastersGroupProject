package com.mase2.mase2_project.util;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.HttpHeaders;

import org.jboss.resteasy.util.Base64;

import com.mase2.mase2_project.data.UserDAO;
import com.mase2.mase2_project.model.User;

@Stateless
@LocalBean
public class SecurityCheck {
	@EJB
	UserDAO userDAO;

	/**
	 * 
	 * @param httpHeaders
	 * @return user <link>User<link>
	 */
	public User login(HttpHeaders httpHeaders) {
		String credentials = "";
		User userToCheck = new User();

		try {
			credentials = getAuthorizationHeader(httpHeaders);
		} catch (Exception e) {
			System.out.println("Credential not found");
			return userToCheck;
		}

		tkenizeCredentials(credentials, userToCheck);
		isValidUser(userToCheck);
		return userToCheck;
	}

	private void tkenizeCredentials(String credentials, User userToCheck) {
		final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");

		if (tokenizer.hasMoreTokens()) {
			userToCheck.setEmail(tokenizer.nextToken());
		}
		if (tokenizer.hasMoreTokens()) {
			userToCheck.setPassword(tokenizer.nextToken());
		}
	}

	private String getAuthorizationHeader(HttpHeaders httpHeaders) throws Exception {
		final List<String> authorizationHeaders = httpHeaders.getRequestHeader("Authorization");
		if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
			throw new Exception("No Credentials in request");
		}
		return authorizationHeaders.get(0).replaceFirst("Basic ", "");
		// System.out.println("authorizationHeaders.get(0)" +
		// authorizationHeaders.get(0));
		// String encodedCredentials = authorizationHeaders.get(0).replaceFirst("Basic
		// ", "");

		// try {
		// credentials = new String(Base64.decode(encodedCredentials));
		// } catch (IOException e) {
		// return false;
		// }
	}

	public boolean hasRole(HttpHeaders httpHeaders, String role) {

		User userToCheck = new User();
		userToCheck.setRole(role);

		String credentials = "";
		try {
			credentials = getAuthorizationHeader(httpHeaders);
		} catch (Exception e) {
			System.out.println("Credential not found");
			return false;
		}
		tkenizeCredentials(credentials, userToCheck);
		if (isValidUser(userToCheck)) {
			return userToCheck.getRole().equals(role);
		}
		return false;
	}

	private boolean isValidUser(User userToCheck) {
		List<User> userList = userDAO.findByEmail(userToCheck.getEmail());
		if (!userList.isEmpty()) {
			User userInDB = userList.get(0);
			if (userInDB == null) {
				return false;
			}

			if (!userInDB.getPassword().equals(userToCheck.getPassword())) {
				return false;
			}
			userToCheck.setRole(userInDB.getRole());
			return true;
		} else {
			return false;
		}
	}
}
