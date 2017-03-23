package se.groupfish.restcasemanagement.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import se.groupfish.springcasemanagement.model.WorkItem;

public class DTOWorkItem {

	private Long id;
	private String title;
	private String description;
	private String state;
	private Long userId;

	public DTOWorkItem(Long id, String title, String description, String state, Long userId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.state = state;
		this.userId = userId;
	}

	public DTOWorkItem() {
		this.id = null;
		this.title = null;
		this.description = null;
		this.state = null;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getState() {
		return state;
	}

	public Long getUserId() {
		return userId;
	}

	public static DTOWorkItem toDTO(WorkItem entity) {

		DTOWorkItemBuilder builder = new DTOWorkItemBuilder();
		builder.setId(entity.getId()).setTitle(entity.getTitle()).setDescription(entity.getDescription())
				.setState(entity.getState()).setUserId(entity.getUser().getId());

		return builder.build(entity.toString());
	}

	public static WorkItem toEntity(DTOWorkItem dataTransferObject) {

		WorkItem workItem = new WorkItem(dataTransferObject.getTitle(), dataTransferObject.getDescription(),
				dataTransferObject.getState());

		return workItem;
	}

	public static List<DTOWorkItem> workItemsListToDTOWorkItemList(Collection<WorkItem> list) {
		List<DTOWorkItem> listDto = new ArrayList<>();
		for (WorkItem workItems : list) {
			listDto.add(DTOWorkItem.toDTO(workItems));
		}
		return listDto;
	}

	public static DTOWorkItemBuilder builder() {
		return new DTOWorkItemBuilder();
	}

	public static final class DTOWorkItemBuilder {

		private Long id = null;
		private String title = "";
		private String description = "";
		private String state = "";
		private Long userId = null;

		public DTOWorkItemBuilder() {
			;
		}

		public DTOWorkItemBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public DTOWorkItemBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		public DTOWorkItemBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public DTOWorkItemBuilder setState(String state) {
			this.state = state;
			return this;
		}

		public DTOWorkItemBuilder setUserId(Long userId) {
			this.userId = userId;
			return this;
		}

		public DTOWorkItem build(String dtoWorkItem) {
			return new DTOWorkItem(id, title, description, state, userId);
		}
	}
}