package se.groupfish.restcasemanagement.resourse;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

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
	private RestTeamService teamService;

	@Context
	private UriInfo uriInfo;

	@POST
	public Response addTeam(DTOTeam dtoTeam) throws ServiceException {

		Team savedTeam = teamService.createTeam(dtoTeam);
		URI location = uriInfo.getAbsolutePathBuilder().path(savedTeam.getId().toString()).build();
		return Response.created(location).build();
	}

	@PUT
	@Path("{id}")
	public Response updateAndInactivateTeam(@PathParam("id") Long id, DTOTeam dtoTeam) {

		try {
			if (dtoTeam != null && dtoTeam.getTeamName() != null) {
				teamService.updateTeam(id, dtoTeam.getTeamName());
			}
			if (dtoTeam != null && dtoTeam.getState() != null) {
				teamService.disableTeam(id);
			}
		} catch (ServiceException e) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		return Response.status(Status.OK).build();
	}

	@GET
	public Response getAllTeams(Collection<DTOTeam> dtoTeams) {

		Collection<DTOTeam> getAllTeams = teamService.getAllDTOTeams(dtoTeams);
		return getAllTeams == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(getAllTeams).build();
	}
	
//	@PUT
//	@Path("users/{id}")
//	public Response addUserToTeam(@PathParam("id") Long userId, DTOTeam dtoTeam) {
//
//		try {
//            teamService.addUserToOneTeam(userId, dtoTeam.getId()); 
//			URI location = uriInfo.getAbsolutePathBuilder().path(userId.toString()).build();
//			return Response.created(location).build();
//		} catch (ServiceException e) {
//			throw new WebApplicationException(Status.BAD_REQUEST);
//		}
//	}
}
