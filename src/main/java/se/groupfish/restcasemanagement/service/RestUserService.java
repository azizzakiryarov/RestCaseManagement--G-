package se.groupfish.restcasemanagement.service;

import static se.groupfish.restcasemanagement.data.DTOUser.toEntity;
import static se.groupfish.restcasemanagement.data.DTOUser.usersListToDTOUserList;

import java.util.List;

import static se.groupfish.restcasemanagement.data.DTOUser.toDTO;

import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.data.DTOUser;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.service.TeamService;
import se.groupfish.springcasemanagement.service.UserService;

@Component
public class RestUserService {

	private final UserService userService;
	@SuppressWarnings("unused")
	private final TeamService teamService;

	public RestUserService(UserService userService, TeamService teamService) {
		this.userService = userService;
		this.teamService = teamService;
	}

	public User saveUser(DTOUser dtoUser) throws ServiceException {

		User savedUser = toEntity(dtoUser);
		return userService.createUser(savedUser);
	}

	public void updateUser(Long userId, String userName) throws ServiceException {

		User userForUpdate = userService.getUserById(userId);
		userService.updateUserUsername(userForUpdate.getId(), userName);
	}

	public void disableUser(Long id, String state) throws ServiceException {

		User userForDisactivate = userService.getUserById(id);
		userService.updateUserState(userForDisactivate.getId(), state);
	}

	public DTOUser getUserByNumber(String number) throws ServiceException {

		User getUser = userService.findUserByNumber(number);
		DTOUser user = toDTO(getUser);
		return user;
	}

	public DTOUser getUserByFirstName(String firstName) throws ServiceException {

		User getUser = userService.findUserByFirstName(firstName);
		DTOUser user = toDTO(getUser);
		return user;
	}

	public List<DTOUser> getAllDTOUsers(Long teamId) throws ServiceException {

		List<User> listUsers = userService.findAllUsersFromOneTeam(teamId);
		List<DTOUser> dtoUsersList = usersListToDTOUserList(listUsers);
		return dtoUsersList;
	}

}
