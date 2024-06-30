package com.example.prueba1


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba1.R.id.buttonSend

class InscriptionActivity : AppCompatActivity() {
    private lateinit var buttonSend: Button
    private lateinit var buttonVolver: Button
    private lateinit var sherePrefe: SherePreference
    private val itemList: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inscription)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonSend = findViewById(R.id.buttonSend)
        buttonVolver = findViewById(R.id.btnVolInicio)
        var spinnerMaterias = findViewById<Spinner>(R.id.listMaterias)
        var spinnerLlamados = findViewById<Spinner>(R.id.listLlamados)
        val descripcion = findViewById<TextView>(R.id.Descripcion)
        sherePrefe = SherePreference(this)
        val sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        buttonVolver.setOnClickListener{
            val intent = Intent(this@InscriptionActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonSend.setOnClickListener {
            // Guardo la informacón de la inscripción en una variable.
            val materiaInscription =
                "Nombre de materia: ${spinnerMaterias.selectedItem}, inscripto para el ${spinnerLlamados.selectedItem} ."
            // Le mostramos al usuario la información
            descripcion.text = materiaInscription
            // Esta línea de código guarda una cadena con la clave "Nueva inscripcion. " y el valor de materiaInscription en las SharedPreferences
            sharedPref.edit().putString("Nueva inscripcion. ", materiaInscription).apply()
            // Le permitimos enviar la inscripción por correo
            MailSend(materiaInscription)
            // Almacenamos la incripción
            val newItem = materiaInscription
            if (newItem.isNotEmpty()) {
                // Agregar el nuevo elemento a la lista
                itemList.add(newItem)
                // Guardar la lista actualizada en SharedPreferences
                sherePrefe.saveList("Inscripción", itemList)
            }
            //Mostramos un mensaje
            showToast("Inscripción enviada al correo de la Institución.")
        }

    }
    // Método que usamos para preguntarle al usuario que aplicación quiere usar para enviar correo
    private fun MailSend(messageInscription : String){
        val to = "schoolembensema@gmail.com"
        val subject = "Inscripción"
        val message = "Inscription: $messageInscription"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$to")
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject: $subject")
        intent.putExtra(Intent.EXTRA_TEXT, "message: $message")

        startActivity(Intent.createChooser(intent, "Enviar correo usando:"))

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}