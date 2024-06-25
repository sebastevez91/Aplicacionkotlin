package com.example.prueba1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaisesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paises)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnVolver = findViewById<Button>(R.id.btnVolverInicio)
        val spinnerPais = findViewById<Spinner>(R.id.listPaises)
        val spinnerCapi = findViewById<Spinner>(R.id.listCapitales)
        val informacion = findViewById<TextView>(R.id.infoPais)

        btnVolver.setOnClickListener{
            val intent = Intent(this@PaisesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}