package se.groupfish.restcasemanagement.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.resourse.UserResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(UserResource.class);
//		register(WorkItemResource.class);
//		register(TeamResource.class);
//		register(IssueResource.class);
//		register(ConflictExceptionMapper.class);
//		register(NotFoundExceptionMapper.class);
//		register(BadRequestExceptionMapper.class);
//		register(UnathorizedExceptionMapper.class);
//		register(RequestFilter.class);
		//HEJ HEJ
	}

}
