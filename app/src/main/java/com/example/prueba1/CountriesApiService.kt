package com.example.prueba1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApiService {
    // Creo una interface con los endpoints que voy a usar
    @GET("v3.1/name/{country}")
    // Realiza la solicitud HTTP GET a la ruta especifica estableciendo parámetro especifico en {country}
    fun getCountryInfo(@Path("country") country: String): Call<List<Country>>
    // @Path("countryName"): Esta anotación indica que el valor del parámetro countryName se insertará en la URL como parte de la ruta.
    // Por ejemplo, si countryName es “Argentina”, la URL será “name/Argentina”.
}
/*Call<List<CountryDTO>>: El tipo de retorno Call<List<CountryDTO>> representa una llamada asíncrona a la API.
 Contiene una lista de objetos CountryDTO, que probablemente representan información sobre países.
 */
/*En resumen, esta interfaz define un método para buscar países por nombre utilizando Retrofit.
 Cuando llames a searchCountries("Argentina"), Retrofit realizará una solicitud GET a la URL “name/Argentina”
 y esperará una lista de objetos CountryDTO como respuesta
 */


