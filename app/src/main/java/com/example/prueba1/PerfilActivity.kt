package com.example.prueba1

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nombre = findViewById<EditText>(R.id.Nombre)
        val apellido = findViewById<EditText>(R.id.Apellido)
        val fechaNac = findViewById<DatePicker>(R.id.FechaNacimiento)
        val  tipoDni = findViewById<Spinner>(R.id.TipoDni)
        val numDni = findViewById<EditText>(R.id.numDni)
        val btnGuardar = findViewById<Button>(R.id.BtnGuardar)

        btnGuardar.setOnClickListener {

        }
    }
    /*private fun VerificaDatos(name:String,surname:String,fNac:DatePicker,tipo:String,numDni:Int){
        if(name.isEmpty() || surname.isEmpty() || fNac.isEmpty() || tipo.isEmpty() || numDni == 0 ){
            showToast("Debes completar los campos.")
        }else if ()
    }*/
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}