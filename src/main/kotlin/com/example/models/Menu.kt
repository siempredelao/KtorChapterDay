package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val id: String,
    val date: Long,
    val recipes: List<String> // List of recipe IDs
)
