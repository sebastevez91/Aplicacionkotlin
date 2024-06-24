package com.example.prueba1

import retrofit2.http.GET

interface CountriesApiService {
    @GET("all")
    suspend fun getCountries(): List<Country>
}
