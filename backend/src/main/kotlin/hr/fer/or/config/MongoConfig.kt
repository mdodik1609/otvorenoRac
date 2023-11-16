package hr.fer.or.config

import hr.fer.or.model.Team
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import jakarta.inject.Named
import jakarta.inject.Singleton
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

@Factory
class MongoConfig {

    @Bean
    @Singleton
    fun mongoClient(
        //@Value("\${mongo.uri}") uri: String
    ): CoroutineClient = KMongo.createClient("mongodb://localhost:27017/?minPoolSize=12&maxPoolSize=12&authSource=admin").coroutine

    @Bean
    @Singleton
    fun mongoDatabase(
        //@Value("\${mongo.database}") database: String,
        mongoClient: CoroutineClient
    ) = mongoClient.getDatabase("teams")

    @Bean
    @Named("teamCollection")
    @Singleton
    fun teamCollection(
        mongoDatabase: CoroutineDatabase
    ) = mongoDatabase.getCollection<Team>("teamCollection")

}