package se.groupfish.restcasemanagement.resourse;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.data.DTOWorkItem;
import se.groupfish.restcasemanagement.service.RestWorkItemService;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.WorkItem;

@Component
@Path("workitems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkItemResource {

	@Autowired
	private RestWorkItemService workItemService;

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

}
