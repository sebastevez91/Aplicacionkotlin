package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Properties
import java.util.concurrent.ThreadLocalRandom
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecuperarActivity : AppCompatActivity() {
    private lateinit var EmailUser: EditText
    private lateinit var btnRecuperar: Button
    private lateinit var btnVolver: Button

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

        // Creamos una clave aleatoria
        val claveRecu = ThreadLocalRandom.current().nextInt(10000, 999999)

        btnRecuperar.setOnClickListener{
            val email:String = EmailUser.text.toString()
            // Enviamos Correo con la clave de recupero

            if (email.isNotEmpty() && claveRecu > 0) {
                // Llama a la función de envío de correo electrónico
                // Usa Coroutines para la operación de red
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val emailSender = EmailSend()
                        emailSender.sendRecoveryEmail(email, claveRecu.toString())
                        // Si necesitas realizar acciones en el hilo principal después del envío:
                        // withContext(Dispatchers.Main) { /* Actualizar UI */ }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        btnVolver.setOnClickListener{
            val intent = Intent(this@RecuperarActivity, MainActivity::class.java)
            // Asegura que la pila de actividades anteriores se limpie
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}