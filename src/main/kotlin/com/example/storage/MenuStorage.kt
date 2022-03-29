package com.example.storage

import com.example.models.Menu

object MenuStorage {

    val menus = mutableListOf(
        Menu("10", 5, listOf("1", "2", "3")),
        Menu("20", 7, listOf("4", "5", "6")),
        Menu("30", 14, listOf("7", "8", "9"))
    )
}