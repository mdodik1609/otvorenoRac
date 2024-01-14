package hr.fer.or.model

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class User(
    var username: String? = null,
    var password: String? = null
)