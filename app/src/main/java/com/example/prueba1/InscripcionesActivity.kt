package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InscripcionesActivity : AppCompatActivity() {

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

        btnVolver.setOnClickListener{
            val intent = Intent(this@InscripcionesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    /*private fun obtenerInscripcionesGuardadas(): List<String> {
        val inscripcion1 = getSharedPreferences(R.string.preference_file_key, MODE_PRIVATE).getString("inscripcion1", "")
        val inscripcion2 = getSharedPreferences("mi_archivo_preferencias", MODE_PRIVATE).getString("inscripcion2", "")
        // ...

        // Agregar las inscripciones a una lista
        val inscripciones = mutableListOf<String>()
        if (inscripcion1 != null) inscripciones.add(inscripcion1)
        if (inscripcion2 != null) inscripciones.add(inscripcion2)
        // ...

        return inscripciones
    }*/
}