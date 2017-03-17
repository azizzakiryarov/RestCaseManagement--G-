package se.groupfish.restcasemanagement.data;
import se.groupfish.springcasemanagement.model.User;

public class DTOUser {

	private Long id;
	private final String firstName;
	private final String lastName;
	private final String userName;
	private String userNumber;
	private final String state;

	private DTOUser(Long id, String firstName, String lastName, String userName, String userNumber, String state) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.userNumber = userNumber;
		this.state = state;
	}

	private DTOUser() {
		this.userName = null;
		this.firstName = null;
		this.lastName = null;
		this.state = null;
	}

	public static DTOUserBuilder builder() {
		return new DTOUserBuilder();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public String getState() {
		return state;
	}

	public static DTOUser toDTO(User entity) {

		DTOUserBuilder builder = new DTOUserBuilder();
		builder.setFirstName(entity.getFirstName()).setLastName(entity.getLastName()).setState(entity.getState())
				.setId(entity.getId());

		return builder.build(entity.getUserName());
	}

	public static User toEntity(DTOUser dataTransferObject) {

		User user = new User(dataTransferObject.getFirstName(), dataTransferObject.getLastName(),
				dataTransferObject.getUserName(), dataTransferObject.getUserNumber());

		return user;
	}

	public static final class DTOUserBuilder {

		private Long id = null;
		private String firstName = "";
		private String lastName = "";
		@SuppressWarnings("unused")
		private String userName = "";
		private String userNumber = "";
		private String state = "";

		private DTOUserBuilder() {
			;
		}

		public DTOUserBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public DTOUserBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public DTOUserBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		public DTOUserBuilder setUserName(String userName) {
			this.userName = userName;
			return this;
		}
		public DTOUserBuilder setuserNumber(String userNumber) {
			this.userNumber = userNumber;
			return this;
		}

		public DTOUserBuilder setState(String state) {
			this.state = state;
			return this;
		}

		public DTOUser build(String userName) {
			return new DTOUser(id, firstName, lastName, userName, userNumber, state);
		}
	}
}
