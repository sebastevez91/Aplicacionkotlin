package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaisesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountriesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinnerPais: Spinner
    private lateinit var spinnerCapi: Spinner
    private lateinit var informacion: TextView
    private lateinit var countries: List<Country>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paises)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnInformacion = findViewById<Button>(R.id.btnInformacion)
        val btnVolver = findViewById<Button>(R.id.btnVolverInicio)
        spinnerPais = findViewById(R.id.listPaises)
        spinnerCapi = findViewById(R.id.listCapitales)
        informacion = findViewById(R.id.infoPais)
        progressBar = findViewById(R.id.progressBar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(CountriesApiService::class.java)

        btnInformacion.setOnClickListener {
            showCountryInfo()
        }
        btnVolver.setOnClickListener {
            val intent = Intent(this@PaisesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        fetchCountries(apiService)
    }

    private fun fetchCountries(apiService: CountriesApiService) {
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                countries = apiService.getCountries()
                setupSpinners(countries)
                progressBar.visibility = View.GONE
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@PaisesActivity, "Error al obtener datos", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupSpinners(countries: List<Country>) {
        val countryNames = countries.map { it.name }
        val capitalNames = countries.map { it.capital }

        val countryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countryNames)
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPais.adapter = countryAdapter

        val capitalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, capitalNames)
        capitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCapi.adapter = capitalAdapter

        spinnerPais.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                spinnerCapi.setSelection(position)
                informacion.text = getString(
                    R.string.country_info,
                    selectedCountry.name,
                    selectedCountry.capital,
                    selectedCountry.population
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerCapi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                spinnerPais.setSelection(position)
                informacion.text = getString(
                    R.string.country_info,
                    selectedCountry.name,
                    selectedCountry.capital,
                    selectedCountry.population
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun showCountryInfo() {
        val selectedPosition = spinnerPais.selectedItemPosition
        val selectedCountry = countries[selectedPosition]
        informacion.text = getString(
            R.string.country_info,
            selectedCountry.name,
            selectedCountry.capital,
            selectedCountry.population
        )
    }
}
