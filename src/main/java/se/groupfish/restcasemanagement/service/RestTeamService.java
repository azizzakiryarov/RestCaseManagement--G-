package se.groupfish.restcasemanagement.service;

import org.springframework.stereotype.Component;
import static se.groupfish.restcasemanagement.data.DTOTeam.toEntity;
import static se.groupfish.restcasemanagement.data.DTOTeam.toDTO;

import se.groupfish.restcasemanagement.data.DTOTeam;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.Team;
import se.groupfish.springcasemanagement.service.TeamService;

@Component
public class RestTeamService {

	private final TeamService teamService;

	public RestTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public Team createTeam(DTOTeam dtoTeam) throws ServiceException {

		Team createdTeam = toEntity(dtoTeam);
		return teamService.createTeam(createdTeam);
	}

	public void updateTeam(Long id, String teamName) throws ServiceException {

		Team updatedTeam = teamService.getTeamById(id);
		teamService.updateTeamName(updatedTeam.getId(), teamName);

	}

	public void disableTeam(Long id, String state) throws ServiceException {

		Team disabledTeam = teamService.getTeamById(id);
		teamService.updateTeamName(disabledTeam.getId(), state);

	}
}
