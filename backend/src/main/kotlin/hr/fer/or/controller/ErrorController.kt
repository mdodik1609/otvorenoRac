package hr.fer.or.controller

import hr.fer.or.model.CResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import java.lang.reflect.InvocationTargetException
import io.micronaut.http.annotation.Error

@Controller
class ErrorController(
) {
    @Error(exception = InvocationTargetException::class, global = true)
    fun notImplementedException(
        httpRequest: HttpRequest<Any>
    ): CResponse =
        CResponse(
            status = HttpStatus.NOT_IMPLEMENTED,
            message = "Endpoint not implemented",
            response = null
        )

    @Error(exception = ClassCastException::class, global = true)
    fun classCastException(
        httpRequest: HttpRequest<Any>
    ): CResponse =
        CResponse(
            status = HttpStatus.NOT_FOUND,
            message = "Team not found",
            response = null
        )
}