package com.terraplanistas.rolltogo.data.remote.services

data class MonsterResponse(
    // Datos de la API si hubiera allí
    val index: String,
    val name: String, //mutumbo
    val hit_points: Int,
    val alignment: String,

    // Monstruos locales
    val isCustom: Boolean = false,  // Para diferenciar monstruos de API vs. creados
    val createdBy: String? = null   // ID del usuario (si es personalizado)
)