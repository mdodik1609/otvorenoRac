package hr.fer.or.model

import org.litote.kmongo.Id

data class Manager(
    var name: String? = null
)

data class Player(
    var name: String? = null,
    var matches_played: Int? = null,
    var goal: Int? = null,
    var assist: Int?= null,
    var yellow_card: Int? = null,
    var red_card: Int? = null
)