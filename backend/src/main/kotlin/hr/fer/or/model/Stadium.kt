package hr.fer.or.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.litote.kmongo.Id

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class Stadium(
    var name: String? = null,
    var capacity: Int? = null
)