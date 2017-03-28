package se.groupfish.restcasemanagement.resourse;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.data.DTOIssue;
import se.groupfish.restcasemanagement.data.DTOWorkItem;
import se.groupfish.restcasemanagement.service.RestIssueService;
import se.groupfish.restcasemanagement.service.RestWorkItemService;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.WorkItem;

@Component
@Path("workitems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class WorkItemResource {

	@Autowired
	private RestWorkItemService workItemService;

	@Autowired
	private RestIssueService issueService;

	@Context
	private UriInfo uriInfo;

	@POST
	public Response createWorkItem(DTOWorkItem dtoWorkItem) {

		try {
			WorkItem savedWorkItem = workItemService.saveWorkItem(dtoWorkItem);
			URI location = uriInfo.getAbsolutePathBuilder().path(savedWorkItem.getId().toString()).build();
			return Response.created(location).build();
		} catch (ServiceException e) {
			new WebApplicationException(Status.CONFLICT);
		}
		return Response.status(Status.OK).build();
	}

	@POST
	@Path("{workitemId}")
	public Response createIssueAndAddToWorkItem(DTOIssue dtoIssue, @PathParam("workitemId") Long workItemId) {

		try {
			issueService.saveIssue(dtoIssue, workItemId);
		} catch (ServiceException e) {
			new WebApplicationException(Status.CONFLICT);
		}
		return Response.status(Status.OK).build();
	}

	@PUT
	@Path("{id}")
	public Response updateWorkItemsStateAndAddWorkItemToUser(@PathParam("id") Long id, DTOWorkItem dtoWorkItem) throws ServiceException, IOException {

			if (id != null && dtoWorkItem != null && dtoWorkItem.getState() != null) {
				workItemService.updateWorkItemsState(id, dtoWorkItem.getState());
				return Response.status(Status.OK).build();
			}
			if (id != null && dtoWorkItem != null && dtoWorkItem.getUserId() != null) {
				workItemService.addWorkItemToUser(id, dtoWorkItem.getUserId());
			return Response.status(Status.OK).build();
			}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("{id}")
	public Response removeWorkItem(@PathParam("id") Long id) {

		try {
			workItemService.removeWorkItem(id);
		} catch (ServiceException e) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		return Response.status(Status.OK).build();
	}

	@GET
	public Response getAllWorkItemsByStateByTeamIdByUserIdIssueId(@QueryParam("state") String state,
			@QueryParam("teamId") Long teamId, @QueryParam("userId") Long userId, @QueryParam("issueId") Long issueId) {
		try {
			if (state != null) {

				Collection<DTOWorkItem> getAllWorkItems;
				getAllWorkItems = workItemService.getAllDTOWorkItemsByState(state);
				return getAllWorkItems == null ? Response.status(Status.NOT_FOUND).build()
						: Response.ok(getAllWorkItems).build();
			}

			if (teamId != null) {
				Collection<DTOWorkItem> getAllWorkItems;
				getAllWorkItems = workItemService.getAllDTOWorkItemsByTeam(teamId);
				return getAllWorkItems == null ? Response.status(Status.NOT_FOUND).build()
						: Response.ok(getAllWorkItems).build();
			}

			if (userId != null) {

				Collection<DTOWorkItem> getAllWorkItems;
				getAllWorkItems = workItemService.getAllDTOWorkItemsByUser(userId);
				return getAllWorkItems == null ? Response.status(Status.NOT_FOUND).build()
						: Response.ok(getAllWorkItems).build();

			}

			if (issueId != null) {

				Collection<DTOWorkItem> getAllWorkItems;
				getAllWorkItems = workItemService.getAllDTOWorkItemsByIssue(issueId);
				return getAllWorkItems == null ? Response.status(Status.NOT_FOUND).build()
						: Response.ok(getAllWorkItems).build();
			}
		} catch (ServiceException e) {
			new WebApplicationException(Status.BAD_REQUEST);
		}
		return Response.status(Status.OK).build();
	}

	@GET
	@Path("{descriptionContent}")
	public Response getAllWorkItemByDescriptionContent(@PathParam("descriptionContent") String descriptionContent) {

		if (descriptionContent != null) {
		try {
				Collection<DTOWorkItem> getAllWorkItemsByDescription;
				getAllWorkItemsByDescription = workItemService.getAllWorkItemsByContent(descriptionContent);
				return getAllWorkItemsByDescription == null ? Response.status(Status.NOT_FOUND).build()
						: Response.ok(getAllWorkItemsByDescription).build();
		} catch (ServiceException e) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
