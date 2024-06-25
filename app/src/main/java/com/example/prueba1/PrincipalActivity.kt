package com.example.prueba1

import android.annotation.SuppressLint
import com.example.prueba1.R

import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.view.ScrollCaptureSession
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.ViewCompat
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView


class PrincipalActivity : AppCompatActivity() {
    private lateinit var menuMain: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.PrincipaActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userSession = findViewById<TextView>(R.id.idInfoStudent)
        //userSession.text = "Tu nombre de usuario: ${}"

        menuMain = findViewById(R.id.idMenuMain)
        menuMain.setNavigationItemSelectedListener { Item ->
            when (Item.itemId) {
                R.id.menu_inscripciones -> {
                    startActivity(Intent(this, InscriptionActivity::class.java))
                    true
                }
                R.id.menu_mis_inscripciones -> {
                    startActivity(Intent(this, InscripcionesActivity::class.java))
                    true
                }
                R.id.menu_perfil_alumno -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                R.id.menu_apis -> {
                    startActivity(Intent(this, PaisesActivity::class.java))
                    true
                }
                // Add more cases as needed
                else -> false
            }
        }

    }
}