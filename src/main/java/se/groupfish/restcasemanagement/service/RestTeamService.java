package se.groupfish.restcasemanagement.service;

import org.springframework.stereotype.Component;
import static se.groupfish.restcasemanagement.data.DTOTeam.toEntity;
import static se.groupfish.restcasemanagement.data.DTOTeam.teamListToDTOTeamList;

import java.util.Collection;

import se.groupfish.restcasemanagement.data.DTOTeam;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.Team;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.service.TeamService;
import se.groupfish.springcasemanagement.service.UserService;

@Component
public final class RestTeamService {

	private final TeamService teamService;
	private final UserService userService;

	public RestTeamService(TeamService teamService, UserService userService) {
		this.teamService = teamService;
		this.userService = userService;
	}

	public Team createTeam(DTOTeam dtoTeam) throws ServiceException {

		Team createdTeam = toEntity(dtoTeam);
		return teamService.createTeam(createdTeam);
	}

	public void updateTeam(Long id, String teamName) throws ServiceException {

		Team updatedTeam = teamService.getTeamById(id);
		teamService.updateTeamName(updatedTeam.getId(), teamName);

	}

	public void disableTeam(Long id) throws ServiceException {
		teamService.disableTeam(id);
	}

	public Collection<DTOTeam> getAllDTOTeams(Collection<DTOTeam> dtoTeams) {

		Collection<Team> list = teamService.getAllTeam();
		Collection<DTOTeam> teams = teamListToDTOTeamList(list);
		return teams;

	}

	public void addUserToOneTeam(Long teamId, Long userId) throws ServiceException {

		User userAddToTeam = userService.getUserById(userId);
		Team teamToUser = teamService.getTeamById(teamId);
		userAddToTeam.setTeam(teamToUser);

		teamService.addUserToTeam(teamId, userAddToTeam.getId());

	}

}
