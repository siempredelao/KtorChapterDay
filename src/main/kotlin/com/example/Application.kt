package com.example

import com.example.routes.menuRouting
import com.example.routes.recipeRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        registerRoutes()
        configureSerialization()
    }.start(wait = true)
}

fun Application.registerRoutes() {
    routing {
        recipeRouting()
        menuRouting()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
