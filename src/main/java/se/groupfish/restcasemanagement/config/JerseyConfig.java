package se.groupfish.restcasemanagement.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.resourse.TeamResource;
import se.groupfish.restcasemanagement.resourse.UserResource;
import se.groupfish.restcasemanagement.resourse.WorkItemResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(UserResource.class);
		register(TeamResource.class);
		register(WorkItemResource.class);
//		register(IssueResource.class);
//		register(ConflictExceptionMapper.class);
//		register(NotFoundExceptionMapper.class);
//		register(BadRequestExceptionMapper.class);
//		register(UnathorizedExceptionMapper.class);
//		register(RequestFilter.class);
	}

}
