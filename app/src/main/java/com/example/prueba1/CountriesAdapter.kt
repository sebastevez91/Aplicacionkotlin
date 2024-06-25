package com.example.prueba1

// CountriesAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountriesAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvCountryName)
        val capital: TextView = view.findViewById(R.id.tvCapital)
        val population: TextView = view.findViewById(R.id.tvPopulation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.name.text = country.name
        holder.capital.text = "Capital: ${country.capital}"
        holder.population.text = "Population: ${country.population}"
    }

    override fun getItemCount() = countries.size
}
