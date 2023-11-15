package hr.fer.or.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.litote.kmongo.Id

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class Manager(
    var id: Id<Manager>? = null,
    var name: String? = null
)


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class Player(
    var id: Id<Player>? = null,
    var name: String? = null,
    var matches_played: Int? = null,
    var goal: Int? = null,
    var assist: Int?= null,
    var yellow_card: Int? = null,
    var red_card: Int? = null
)