package se.groupfish.restcasemanagement.service;

import se.groupfish.restcasemanagement.data.DTOWorkItem;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.WorkItem;
import se.groupfish.springcasemanagement.service.WorkItemService;
import static se.groupfish.restcasemanagement.data.DTOWorkItem.toEntity;

import org.springframework.stereotype.Component;

@Component
public class RestWorkItemService {

	private WorkItemService workItemService;

	public WorkItem saveWorkItem(DTOWorkItem dtoWorkItem) throws ServiceException {

		WorkItem savedWorkItem = toEntity(dtoWorkItem);
		return workItemService.createWorkItem(savedWorkItem);
	}
	
	
}
