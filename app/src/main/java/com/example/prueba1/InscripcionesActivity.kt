package com.example.prueba1

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InscripcionesActivity : AppCompatActivity() {
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
    }
    private fun obtenerInscripcionesGuardadas(): List<String> {
        // Aquí debes implementar la lógica para obtener las inscripciones desde SharedPreferences
        // Por ejemplo, si las inscripciones están guardadas con claves "inscripcion1", "inscripcion2", etc.
        val inscripcion1 = getSharedPreferences("mi_archivo_preferencias", MODE_PRIVATE).getString("inscripcion1", "")
        val inscripcion2 = getSharedPreferences("mi_archivo_preferencias", MODE_PRIVATE).getString("inscripcion2", "")
        // ...

        // Agregar las inscripciones a una lista
        val inscripciones = mutableListOf<String>()
        if (inscripcion1 != null) inscripciones.add(inscripcion1)
        if (inscripcion2 != null) inscripciones.add(inscripcion2)
        // ...

        return inscripciones
    }
}