package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InscripcionesActivity : AppCompatActivity() {
    private lateinit var sherePrefe: SherePreference
    private val itemList: MutableList<String> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inscripciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listInscripcciones = findViewById<ListView>(R.id.listIncripciones)
        val btnVolver = findViewById<Button>(R.id.btnVInicio)
        sherePrefe = SherePreference(this)
        // Recuperar la lista guardada
        val listInsc = sherePrefe.getList("Inscripcion")
        // Configura el ArrayAdapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        // Mostrar la lista en el ListView
        if (listInsc != null) {
            val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, listInsc)
            listInscripcciones.adapter = adapter
        }
        btnVolver.setOnClickListener{
            val intent = Intent(this@InscripcionesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}