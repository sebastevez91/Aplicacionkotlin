package com.example.prueba1

import com.google.gson.annotations.SerializedName
import retrofit2.http.Path
// Clase modelo
data class Country(
    val capital: List<String>?,
    val population: Long
)


