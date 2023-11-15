package hr.fer.or.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.litote.kmongo.Id

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class Team(
    var id: Id<Team>? = null,
    var stadium: Stadium? = null,
    var wins: Int? = null,
    var draws: Int? = null,
    var defeats: Int? = null,
    var goals_scored: Int? = null,
    var goals_conceded: Int? = null,
    var point: Int? = null,
    var place: Int? = null,
    var captain: Player? = null,
    var manager: List<Manager>? = null
)