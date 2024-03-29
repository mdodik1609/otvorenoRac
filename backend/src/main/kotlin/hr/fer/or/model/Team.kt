package hr.fer.or.model

import org.litote.kmongo.Id

data class Team(
    var _id: Id<Team>? = null,
    var teamName: String? = null,
    var stadium: Stadium? = null,
    var wins: Int? = null,
    var draws: Int? = null,
    var defeats: Int? = null,
    var goal_scored: Int? = null,
    var goal_conceded: Int? = null,
    var points: Int? = null,
    var place: Int? = null,
    var captain: Player? = null,
    var manager: List<Manager>? = null
)