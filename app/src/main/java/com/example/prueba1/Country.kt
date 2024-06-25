package com.example.prueba1

import com.google.gson.annotations.SerializedName
import retrofit2.http.Path

data class Country(
    @Path("name")
    val name: String,
    @Path("capital")
    val capital: String,
    @Path("population")
    val population: Int
)

