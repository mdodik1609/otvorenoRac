package hr.fer.or

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
		.args(*args)
		.packages("hr.fer.or")
		.start()
}

