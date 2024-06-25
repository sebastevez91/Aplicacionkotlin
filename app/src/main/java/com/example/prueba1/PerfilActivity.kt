package com.example.prueba1

import android.app.DatePickerDialog
import android.content.Intent
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
import java.util.Calendar

class PerfilActivity : AppCompatActivity() {
    private lateinit var etDate:EditText
    private lateinit var buttonVolver: Button
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
        val tipoDni = findViewById<Spinner>(R.id.TipoDni)
        val numDni = findViewById<EditText>(R.id.numDni)
        val btnGuardar = findViewById<Button>(R.id.BtnGuardar)
        etDate = findViewById(R.id.etDate)
        buttonVolver = findViewById(R.id.btnVolverInicio)

        buttonVolver.setOnClickListener{
            val intent = Intent(this@PerfilActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        etDate.setOnClickListener {
            showDateNacimiento()
        }
        btnGuardar.setOnClickListener {
            val name:String = nombre.text.toString()
            val surname:String = apellido.text.toString()
            val fechaNac:String = etDate.text.toString()
            val tpDni:String = tipoDni.selectedItem.toString()
            val numeroDni = numDni.text.toString()

            VerificaDatos(name,surname,fechaNac,tpDni,numeroDni)
        }
    }
    private fun VerificaDatos(name:String,surname:String,fNac:String,tpDni:String,numDni:String){
        if(name.isEmpty() || surname.isEmpty() || fNac.isEmpty() || tpDni.isEmpty() || numDni.isEmpty() ){
            showToast("Debes completar los campos.")
        }else if (numDni.length < 8){
            showToast("Debes poner un número de DNI")
        }else{
            showToast("Actualización exitosa!!!")
        }
    }
    private fun showDateNacimiento(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,{ _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                etDate.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePicker.show()
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}