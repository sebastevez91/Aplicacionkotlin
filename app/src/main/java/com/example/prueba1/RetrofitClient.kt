package com.example.prueba1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://restcountries.com/"

    val  retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
/*
1. **`object RetrofitClient`**: Esto define un objeto llamado `RetrofitClient`.
 Los objetos en Kotlin son similares a las clases, pero solo pueden tener una instancia única.
En este caso, `RetrofitClient` se utiliza para configurar y crear instancias de la biblioteca Retrofit.
2. **`private const val BASE_URL = "https://restcountries.com/"`**: Aquí se declara una constante llamada `BASE_URL` con el valor "https://restcountries.com/".
 Esta URL es la base para todas las solicitudes a la API.
3. **`val retrofit: Retrofit = Retrofit.Builder() ... .build()`**: Esta línea crea una instancia de `Retrofit`.
 Veamos los pasos:
    - **`Retrofit.Builder()`**: Aquí se crea un constructor de `Retrofit`.
    El `Builder` se utiliza para configurar opciones antes de construir la instancia final.
    - **`.baseUrl(BASE_URL)`**: Se establece la URL base para todas las solicitudes.
    En este caso, es la constante `BASE_URL` que definimos anteriormente.
    - **`.addConverterFactory(GsonConverterFactory.create())`**: Se agrega un convertidor de fábrica para convertir las respuestas de la API en objetos Kotlin.
     En este caso, se utiliza `GsonConverterFactory` para convertir los datos JSON en objetos Kotlin.
    - **`.build()`**: Finalmente, se construye la instancia de `Retrofit` con todas las configuraciones anteriores.
En resumen, este código crea una instancia de `Retrofit` con la URL base y un convertidor para manejar las respuestas JSON.
 Luego, puedes usar esta instancia para realizar llamadas a la API.*/