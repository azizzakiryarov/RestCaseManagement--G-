package se.groupfish.restcasemanagement.resourse;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.data.DTOUser;
import se.groupfish.restcasemanagement.exception.ConflictException;
import se.groupfish.restcasemanagement.service.RestUserService;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.service.WorkItemService;


@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

	@Autowired
	private RestUserService service;

	@SuppressWarnings("unused")
	@Autowired
	private WorkItemService workItemService;

	@Context
	private UriInfo uriInfo;

	@POST
	public Response addUser(DTOUser dtoUser) throws ConflictException, ServiceException {

		User savedUser = service.saveUser(dtoUser);
		
		URI location = uriInfo.getAbsolutePathBuilder().path(savedUser.getId().toString()).build();

		return Response.created(location).build();
	}

	@PUT
	@Path("{id}")
	public Response updateUser(@PathParam("id") Long id, DTOUser dtoUser) {

			try {
				service.updateUser(id, dtoUser.getUserName());
			} catch (ServiceException e) {
				throw new WebApplicationException(Status.BAD_REQUEST);
			}

		return Response.status(Status.OK).build();
	}
	@PUT
	@Path("{id}")
	public Response disableUser(@PathParam("id") Long id, DTOUser dtoUser) {
		try {
			service.disableUser(id, dtoUser.getState());
		} catch (Exception e) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		return Response.status(Status.OK).build();
	}
	
	



}
