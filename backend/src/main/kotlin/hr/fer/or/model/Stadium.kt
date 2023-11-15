package hr.fer.or.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.litote.kmongo.Id

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class Stadium(
    var id: Id<Stadium>? = null,
    var name: String? = null,
    var capacity: Int? = null
)