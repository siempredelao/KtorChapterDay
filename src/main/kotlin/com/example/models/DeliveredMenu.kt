package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class DeliveredMenu(
    val id: String,
    val date: Long,
    val recipes: List<Recipe>
)
