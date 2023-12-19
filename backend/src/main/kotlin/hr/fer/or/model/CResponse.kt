package hr.fer.or.model

import io.micronaut.http.HttpStatus

data class CResponse (
    var status: HttpStatus? = null,
    var message: String? = null,
    var response: Any? = null
)