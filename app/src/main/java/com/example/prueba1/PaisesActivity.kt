package com.example.prueba1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaisesActivity : AppCompatActivity() {
    private lateinit var capitalTextView: TextView
    private lateinit var populationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paises)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnInformacion = findViewById<Button>(R.id.btnVerInfo)
        val btnVolver = findViewById<Button>(R.id.btnVuelve)
        val countryEditText: EditText = findViewById(R.id.countryEditText)
        capitalTextView = findViewById(R.id.capitalTextView)
        populationTextView = findViewById(R.id.populationTextView)


        btnInformacion.setOnClickListener {
            val country = countryEditText.text.toString()
            fetchCountriesData(country)
        }
        btnVolver.setOnClickListener {
            val intent = Intent(this@PaisesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun fetchCountriesData(country: String) {
        // Intanciamos la API, llamando a la clase interface CountriesApiService
        val api = RetrofitClient.retrofit.create(CountriesApiService::class.java)
        // Llamamos al método getCountryinfo() que esta dentro de la interface
        val call = api.getCountryInfo(country)

        //Estamos configurando una llamada asíncrona a la API utilizando Retrofit
        call.enqueue(object : Callback<List<Country>>{
            // Esto se ejecutará cuando la respuesta de la API sea exitosa
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>){
                if(response.isSuccessful && response.body()?.isNotEmpty() == true){
                    // Si la respuesta fue exitosa y no está vacía
                    val countryInfo = response.body()!![0]
                    val capital = countryInfo.capital?.firstOrNull() ?: "Capital no encontrada"
                    // Actualizando la vista de texto
                    capitalTextView.text = "Capital: $capital"
                    populationTextView.text = "Población: ${countryInfo.population}"
                }else{
                    // Actualizando la vista de texto
                    capitalTextView.text = "No data available"
                    populationTextView.text = ""
                }
            }
            // Esto se ejecutará cuando la respuesta de la API no sea exitosa
            override fun onFailure(call: Call<List<Country>>, t: Throwable){
                capitalTextView.text = "Error: ${t.message}"
                populationTextView.text = ""
            }
        })
    }

}




