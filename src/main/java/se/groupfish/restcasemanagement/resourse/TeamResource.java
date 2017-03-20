package se.groupfish.restcasemanagement.resourse;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.data.DTOTeam;
import se.groupfish.restcasemanagement.service.RestTeamService;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.Team;

@Component
@Path("teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

	@Autowired
	private RestTeamService service;

	@Context
	private UriInfo uriInfo;

	@POST
	public Response addTeam(DTOTeam dtoTeam) throws ServiceException {

		Team savedTeam = service.createTeam(dtoTeam);
		URI location = uriInfo.getAbsolutePathBuilder().path(savedTeam.getId().toString()).build();
		return Response.created(location).build();
	}
	
	
}
