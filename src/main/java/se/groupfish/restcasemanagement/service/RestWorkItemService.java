package se.groupfish.restcasemanagement.service;

import se.groupfish.restcasemanagement.data.DTOWorkItem;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.WorkItem;
import se.groupfish.springcasemanagement.service.WorkItemService;
import static se.groupfish.restcasemanagement.data.DTOWorkItem.toEntity;
import static se.groupfish.restcasemanagement.data.DTOWorkItem.workItemsListToDTOWorkItemList;

import java.io.IOException;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class RestWorkItemService {

	private WorkItemService workItemService;

	public RestWorkItemService(WorkItemService workItemService) {
		this.workItemService = workItemService;
	}

	public WorkItem saveWorkItem(DTOWorkItem dtoWorkItem) throws ServiceException {

		WorkItem savedWorkItem = toEntity(dtoWorkItem);
		return workItemService.createWorkItem(savedWorkItem);
	}

	public void updateWorkItemsState(Long id, String state) throws ServiceException, IOException {

		WorkItem updatedWorkItem = workItemService.getWorkItemById(id);
		workItemService.updateWorkItemState(updatedWorkItem.getId(), state);
	}

	public void removeWorkItem(Long id) throws ServiceException {

		workItemService.removeWorkItem(id);

	}

	public void addWorkItemToUser(Long workItemId, Long userId) throws ServiceException {

		workItemService.addWorkItemToUser(workItemId, userId);
	}

	public Collection<DTOWorkItem> getAllDTOWorkItemsByTeam(Long teamId) throws ServiceException {

		Collection<WorkItem> workItems = workItemService.getAllWorkItemforTeam(teamId);
		Collection<DTOWorkItem> dtoWorkItems = workItemsListToDTOWorkItemList(workItems);

		return dtoWorkItems;
	}

	public Collection<DTOWorkItem> getAllDTOWorkItemsByUser(Long userId) throws ServiceException {

		Collection<WorkItem> workItems = workItemService.getAllWorkItemforUser(userId);
		Collection<DTOWorkItem> dtoWorkItems = workItemsListToDTOWorkItemList(workItems);

		return dtoWorkItems;
	}

	public Collection<DTOWorkItem> getAllDTOWorkItemsByState(String state) throws ServiceException {
		
		Collection<WorkItem> workItems = workItemService.getWorkItemByState(state);
		Collection<DTOWorkItem> dtoWorkItems = workItemsListToDTOWorkItemList(workItems);
		
		return dtoWorkItems;
	}

}
