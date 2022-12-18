package com.zhirnoov

import com.zhirnoov.db.DatabaseFactory
import com.zhirnoov.model.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow
import org.slf4j.event.Level

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    install(CallLogging) {
        level = Level.ERROR
    }

    routing {

        get("/table") {
            call.respond(DatabaseFactory.getAllClubs())
        }

        post("/table") {
            val clubData = call.receive<ClubData>()
            call.respondText("OK")
            DatabaseFactory.insertClub(clubData)
        }

        delete("/table/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            val removed = DatabaseFactory.deleteClub(id)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

        get("/table/club/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            call.respond(DatabaseFactory.getAllPlayersById(id))
        }

        post("/table/club") {
            val player = call.receive<Player>()
            call.respond("OK")
            DatabaseFactory.insertPlayer(player)
        }

        delete("/table/club/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw  IllegalStateException("Must provide id")
            val removed = DatabaseFactory.deletePlayer(id)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }


        static {
            resources("static")
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
}

fun toClub(row: ResultRow): Club {
    return Club(
        id = row[Clubs.id],
        name = row[Clubs.name],
        games = row[Clubs.games],
        score = row[Clubs.score],
        position = row[Clubs.position],
        logo = row[Clubs.logo]
    )
}

fun toPlayer(row: ResultRow): Player {
    return Player(
        id = row[Players.id],
        clubID = row[Players.clubID],
        name = row[Players.name],
        position = row[Players.position],
        year = row[Players.year],
        nationality = row[Players.nationality],
        photo = row[Players.photo]
    )
}
