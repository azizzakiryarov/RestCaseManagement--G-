package se.groupfish.restcasemanagement.service;

import static se.groupfish.restcasemanagement.data.DTOUser.toEntity;

import org.springframework.stereotype.Component;
import se.groupfish.restcasemanagement.data.DTOUser;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.service.UserService;

@Component
public class RestUserService {

	private final UserService service;

	public RestUserService(UserService service) {
		this.service = service;
	}

	public User saveUser(DTOUser dtoUser) throws ServiceException {

		User savedUser = toEntity(dtoUser);
		return service.createUser(savedUser);
	}

	public void updateUser(Long userId, String userName) throws ServiceException {

		User userForUpdate = service.getUserById(userId);
		service.updateUserUsername(userForUpdate.getId(), userName);
	}

	public void disableUser(Long id, String state) throws ServiceException {

		User userForDisactivate = service.getUserById(id);
		service.updateUserState(userForDisactivate.getId(), state);
	}

}
