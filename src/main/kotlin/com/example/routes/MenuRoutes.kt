package com.example.routes

import com.example.models.DeliveredMenu
import com.example.models.Menu
import com.example.models.Recipe
import com.example.storage.MenuStorage
import com.example.storage.RecipeStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.menuRouting() {

    route("/menus") {

        get {
            if (MenuStorage.menus.isNotEmpty()) {
                call.respond(MenuStorage.menus)
            } else {
                call.respondText("No menus found", status = HttpStatusCode.NotFound)
            }
        }

        route("/past") {

            get {
                if (MenuStorage.menus.isNotEmpty()) {
                    val deliveredMenus = MenuStorage.menus.filter { it.date < 10 } // simulates delivered menus

                    val daRealDeliveredMenus = deliveredMenus.map { menu -> mapToDeliveredMenu(menu) }

                    call.respond(daRealDeliveredMenus)
                } else {
                    call.respondText("No menus found", status = HttpStatusCode.NotFound)
                }
            }
        }
    }
}

private fun mapToDeliveredMenu(menu: Menu): DeliveredMenu {
    return DeliveredMenu(
        menu.id,
        menu.date,
        getRecipesById(menu.recipes)
    )
}

private fun getRecipesById(recipeIds: List<String>): List<Recipe> {
    return recipeIds.map { id ->
        RecipeStorage.recipes.find { recipe -> recipe.id == id } ?: error("")
    }
}
