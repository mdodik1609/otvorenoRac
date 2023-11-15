package hr.fer.or.controller

import hr.fer.or.model.Team
import hr.fer.or.service.TeamService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/team")
class TeamController(
    private val teamService: TeamService
) {

    @Get("/all")
    suspend fun getAllTeams(): List<Team> {
        return teamService.getAllTeams();
    }

}