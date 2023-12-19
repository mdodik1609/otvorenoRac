package hr.fer.or

import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License

@OpenAPIDefinition(
	info = Info(
		title = "Team backend title.",
		version = "0.1",
		description = "My API",
		license = License(name = "MIT", url = "https://github.com/mdodik1609/otvorenoRac/blob/main/LICENCE.md"),
		contact = Contact( name = "Marko", email = "marko.dodik21@gmail.com")
	)
)
object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		Micronaut.run(Application.javaClass)
	}
}

