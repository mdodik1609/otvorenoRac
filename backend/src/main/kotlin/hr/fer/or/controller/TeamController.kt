package hr.fer.or.controller

import com.mongodb.MongoException
import hr.fer.or.model.Team
import hr.fer.or.service.TeamService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.server.cors.CrossOrigin
import io.micronaut.http.simple.SimpleHttpResponseFactory
import kotlin.NoSuchElementException

@Controller("/v1/team", produces = ["application/json"], consumes = ["application/json"])
class TeamController(
    private val teamService: TeamService
) {

    @CrossOrigin
    @Get
    suspend fun getAllTeam():HttpResponse<Any> {
        return try {
            val teams = teamService.getAllTeams()
            HttpResponse.status<List<Team>>(HttpStatus.OK).body(teams)
        } catch (e: NoSuchElementException) {
            HttpResponse.notFound()
        } catch (e: Exception) {
            HttpResponse.badRequest()
        }
    }

    @Get("/{teamId}")
    suspend fun getTeamById(
        @QueryValue("teamId") teamId: String
    ): HttpResponse<Any> {
        return try {
            val team = teamService.getTeamById(teamId)
            HttpResponse.ok(team)
        } catch (e: NoSuchElementException) {
            HttpResponse.notFound("Team not found.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
        }
    }

    @Delete("/{teamId}")
    suspend fun deleteTeam(
        @QueryValue("teamId") teamId: String
    ): MutableHttpResponse<*> {
        return try {
            val result = teamService.deleteTeamById(teamId)
            SimpleHttpResponseFactory().status<Boolean>(HttpStatus.OK, true)
        } catch (e: NoSuchElementException) {
            HttpResponse.notFound("Team not deleted.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
        }
    }

    @Post
    suspend fun createTeam(
        @Body team: Team
    ): MutableHttpResponse<*> {
        return try {
            val team = teamService.createOrUpdateTeam(team)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: MongoException) {
            HttpResponse.badRequest("Team not created.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
        }
    }


    @Put("/{teamId}")
    suspend fun updateTeam(
        @QueryValue("teamId") teamId: String,
        @Body team: Team
    ): MutableHttpResponse<*> {
        return try {
            val team = teamService.updateTeamName(team, teamId)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: MongoException) {
            HttpResponse.notFound("Team not updated.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
        }
    }



    @Get("/place/{place}")
    suspend fun getTeamsByPlace(
        @QueryValue("place") place: Int
    ): MutableHttpResponse<*> {
        return try {
            val team = teamService.getTeamByPlace(place)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: NoSuchElementException) {
            HttpResponse.notFound("Team not found.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
        }
    }

    @Get("/points/{points}")
    suspend fun getTeamsByPoints(
        @QueryValue("points") points: Int
    ): MutableHttpResponse<*> {
        return try {
            val team = teamService.getTeamByPoints(points)
            SimpleHttpResponseFactory().ok(team)
        } catch (e: NoSuchElementException) {
            HttpResponse.notFound("Team not found.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
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
            HttpResponse.notFound("Team not found.")
        } catch (e: Exception) {
            HttpResponse.badRequest("Bad request.")
        }
    }

}