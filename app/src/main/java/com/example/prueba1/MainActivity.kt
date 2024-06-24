package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var linkRegistrar: TextView
    private lateinit var linkRecuperar: TextView
    private lateinit var checkboxSession: CheckBox



    // Datos de ejemplo para validar
    private val correctUsername = "andres"
    private val correctPassword = "456"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializar vistas
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        resultTextView = findViewById(R.id.resultTextView)
        linkRegistrar = findViewById(R.id.linkRegistrar)
        linkRecuperar = findViewById(R.id.linkRecuperar)

        val checkBoxSession : CheckBox = findViewById(R.id.checkboxSession)

        loginButton.setOnClickListener { login() }

        linkRecuperar.setOnClickListener{
            val intent = Intent(this@MainActivity, RecuperarActivity::class.java)
            startActivity(intent)
            finish()
        }

        linkRegistrar.setOnClickListener {
            val intent = Intent(this@MainActivity, Registro::class.java)
            startActivity(intent)
            finish()
        }

        checkBoxSession.setOnCheckedChangeListener { _, isChecked ->
            // Guardar el estado del CheckBox en SharedPreferences
            val sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("KeepSessionOpen", isChecked)
                apply()
            }
        }

// Al iniciar la Activity, establecer el estado del CheckBox según SharedPreferences
        val sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val keepSessionOpen = sharedPref.getBoolean("KeepSessionOpen", false)
        checkBoxSession.isChecked = keepSessionOpen
    }


    private fun login() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            showToast("Debe completar todos los campos")
            return
        }

        if (username == correctUsername && password == correctPassword) {
           resultTextView.text = "Access validado"
            showToast("Acceso validado")
            val intent = Intent(this@MainActivity, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            showToast("Verifique el nombre de usuario y contraseña")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
