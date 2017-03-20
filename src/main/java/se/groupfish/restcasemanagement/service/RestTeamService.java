package se.groupfish.restcasemanagement.service;

import org.springframework.stereotype.Component;

import se.groupfish.restcasemanagement.data.DTOTeam;
import se.groupfish.springcasemanagement.service.TeamService;

@Component
public class RestTeamService {
	
	private final TeamService teamService;

	public RestTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	
//	public createTeam(DTOTeam dtoTeam){
//		
//		Team createdTeam = teamService
//		
//	}
	
	
	
	

}
