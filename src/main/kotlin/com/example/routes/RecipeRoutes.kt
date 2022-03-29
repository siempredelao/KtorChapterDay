package com.example.routes

import com.example.models.Recipe
import com.example.storage.RecipeStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.recipeRouting() {

    route("/recipes") {

        get {
            if (RecipeStorage.recipes.isNotEmpty()) {
                call.respond(RecipeStorage.recipes)
            } else {
                call.respondText("No recipes found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"]
                ?: return@get call.respondText("Missing or malformed id", status = HttpStatusCode.BadRequest)

            val recipe =
                RecipeStorage.recipes.find { it.id == id }
                    ?: return@get call.respondText("No customer with id $id", status = HttpStatusCode.NotFound)
            call.respond(recipe)
        }

        post {
            val recipe = call.receive<Recipe>()
            RecipeStorage.recipes.add(recipe)
            call.respondText("Recipe stored correctly", status = HttpStatusCode.Created)
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (RecipeStorage.recipes.removeIf { it.id == id }) {
                call.respondText("Recipe removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}