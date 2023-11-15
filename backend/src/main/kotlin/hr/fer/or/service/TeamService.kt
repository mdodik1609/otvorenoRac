package hr.fer.or.service

import hr.fer.or.repository.TeamRepository
import jakarta.inject.Singleton

@Singleton
class TeamService(
    private val teamRepository: TeamRepository
) {

    suspend fun getAllTeams() = teamRepository.findAll()
}