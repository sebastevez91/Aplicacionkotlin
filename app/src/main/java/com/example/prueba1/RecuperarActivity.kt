package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecuperarActivity : AppCompatActivity() {
    private lateinit var EmailUser: EditText
    private lateinit var btnRecuperar: Button
    private lateinit var btnVolver: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RecuperarActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializamos vistas
        EmailUser = findViewById(R.id.EmailUser)
        btnRecuperar = findViewById(R.id.btnRecuperar)
        btnVolver = findViewById(R.id.btnVolver)

        btnRecuperar.setOnClickListener{SendEmail()}

        btnVolver.setOnClickListener{
            val intent = Intent(this@RecuperarActivity, MainActivity::class.java)
            // Asegura que la pila de actividades anteriores se limpie
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun SendEmail(){
        val email = EmailUser.text.toString()

        /*if (email.isEmpty()) {
            //TODO
        }else{
            //TODO
        }*/
    }
}