package hr.fer.or.controller

import com.mongodb.MongoException
import hr.fer.or.model.Team
import hr.fer.or.service.TeamService
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.simple.SimpleHttpResponseFactory
import kotlin.NoSuchElementException

@Controller("/v1/team", produces = ["application/json"], consumes = ["application/json"])
class TeamController(
    private val teamService: TeamService
) {

    @Get("/all")
    suspend fun getAllTeams(
    ): MutableHttpResponse<List<Team>> {
        return try {
            val teams = teamService.getAllTeams()
            SimpleHttpResponseFactory().ok(teams)
        } catch (e: NoSuchElementException) {
            SimpleHttpResponseFactory().status<List<Team>>(HttpStatus.BAD_REQUEST, "Team not found.")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<List<Team>>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }

    @Get("/{teamId}")
    suspend fun getTeamById(
        @QueryValue("teamId") teamId: String
    ): MutableHttpResponse<*> {
        return try {
            val team = teamService.getTeamById(teamId)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: NoSuchElementException) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Team not found.")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }

    @Delete("/{teamId}")
    suspend fun deleteTeam(
        @QueryValue("teamId") teamId: String
    ): MutableHttpResponse<Boolean> {
        return try {
            val result = teamService.deleteTeamById(teamId)
            SimpleHttpResponseFactory().status<Boolean>(HttpStatus.OK, true)
        } catch (e: NoSuchElementException) {
            SimpleHttpResponseFactory().status<Boolean>(HttpStatus.BAD_REQUEST, "Team not inserted.")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<Boolean>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }

    @Post
    suspend fun createTeam(
        @Body team: Team
    ): MutableHttpResponse<Team> {
        return try {
            val team = teamService.createOrUpdateTeam(team)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: MongoException) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Team not inserted.")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }


    @Put("/{teamId}")
    suspend fun updateTeam(
        @QueryValue("teamId") teamId: String,
        @Body team: Team
    ): MutableHttpResponse<Team> {
        return try {
            val team = teamService.updateTeamName(team, teamId)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: MongoException) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Team not updated.")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }



    @Get("/place/{place}")
    suspend fun getTeamsByPlace(
        @QueryValue("place") place: Int
    ): MutableHttpResponse<Team> {
        return try {
            val team = teamService.getTeamByPlace(place)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: NoSuchElementException) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.NOT_FOUND, "Team not found")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }

    @Get("/points/{points}")
    suspend fun getTeamsByPoints(
        @QueryValue("points") points: Int
    ): MutableHttpResponse<Team> {
        return try {
            val team = teamService.getTeamByPoints(points)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: NoSuchElementException) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.NOT_FOUND, "Team not found")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<Team>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }


    @Get("/name/{name}")
    suspend fun getTeamsByPlace(
        @QueryValue("name") name: String
    ): MutableHttpResponse<*> {
        return try {
            val team = teamService.getTeamByName(name)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: NoSuchElementException) {
            SimpleHttpResponseFactory().status<String>(HttpStatus.NOT_FOUND, "Team not found")
        } catch (e: Exception) {
            SimpleHttpResponseFactory().status<String>(HttpStatus.BAD_REQUEST, "Bad request")
        }
    }

}