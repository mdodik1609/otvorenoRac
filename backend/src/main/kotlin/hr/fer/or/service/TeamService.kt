package hr.fer.or.service

import com.mongodb.MongoException
import hr.fer.or.model.Team
import hr.fer.or.repository.TeamRepository
import jakarta.inject.Singleton
import kotlin.NoSuchElementException

@Singleton
class TeamService(
    private val teamRepository: TeamRepository
) {

    suspend fun createOrUpdateTeam(team: Team): Team {
        val result = teamRepository.createOrUpdateTeam(team)
        if(result == null || !result.wasAcknowledged()) throw MongoException("Exception when inserting into database.")
        return team
    }

    suspend fun updateTeamName(team: Team, teamId: String): Team {
        var currTeam =  teamRepository.findOneById(teamId)
            ?: throw NoSuchElementException("Team with id[$teamId] does not exist.")
        currTeam.teamName = team.teamName
        val result = teamRepository.createOrUpdateTeam(team)
        if(result == null || !result.wasAcknowledged()) throw MongoException("Exception when inserting into database.")
        teamRepository.deleteTeamById(teamId)
        return team
    }

    suspend fun getAllTeams():List<Team> = teamRepository.findAll()


    suspend fun getTeamById(teamId: String): Team {
        return teamRepository.findOneById(teamId)
            ?: throw NoSuchElementException("Team with id[$teamId] does not exist.")
    }

    suspend fun deleteTeamById(teamId: String): Boolean {
        val delResult = teamRepository.deleteTeamById(teamId)
        if(delResult.wasAcknowledged() && delResult.deletedCount == 0L)
            return false
        return true
    }

    suspend fun getTeamByPlace(place: Int): Team {
        return teamRepository.findOneByPlace(place) ?: throw NoSuchElementException("Team for place: $place not found.")
    }

    suspend fun getTeamByPoints(points: Int): Team {
        return teamRepository.findByPoints(points) ?: throw NoSuchElementException("Team for points: $points not found.")
    }

    suspend fun getTeamByName(name: String): Team {
        return teamRepository.findOneByName(name) ?: throw NoSuchElementException("Team for name: $name not found.")
    }

}