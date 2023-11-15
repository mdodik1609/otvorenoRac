package hr.fer.or.repository

import hr.fer.or.model.Team
import org.litote.kmongo.coroutine.CoroutineCollection
import jakarta.inject.Singleton

@Singleton
class TeamRepository(
    private val teamCollection: CoroutineCollection<Team>
) {

    suspend fun findAll(): List<Team> = teamCollection.find().toList()
}