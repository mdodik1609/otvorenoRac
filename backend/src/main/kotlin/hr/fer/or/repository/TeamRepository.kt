package hr.fer.or.repository

import hr.fer.or.model.Team
import org.litote.kmongo.coroutine.CoroutineCollection
import jakarta.inject.Singleton
import org.litote.kmongo.eq
import org.litote.kmongo.id.IdGenerator

@Singleton
class TeamRepository(
    private val teamCollection: CoroutineCollection<Team>
) {

    suspend fun findAll(): List<Team> = teamCollection.find().toList()

    suspend fun findOneById(teamId: String) = teamCollection.findOne(Team::_id eq IdGenerator.defaultGenerator.create(teamId))

    suspend fun findOneByName(teamName: String) = teamCollection.findOne(Team::teamName eq teamName)

    suspend fun findOneByPlace(place: Int) = teamCollection.findOne(Team::place eq place)

    suspend fun findByPoints(points: Int) = teamCollection.findOne(Team::points eq points)

    suspend fun createOrUpdateTeam(team: Team) = teamCollection.save(team)

    suspend fun deleteTeamById(teamId: String) = teamCollection.deleteOne(Team::_id eq IdGenerator.defaultGenerator.create(teamId))

}