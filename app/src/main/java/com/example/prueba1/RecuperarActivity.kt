package com.example.prueba1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

        val claveRecu = ThreadLocalRandom.current().nextInt(10000, 999999)

        btnRecuperar.setOnClickListener{
            val email:String = EmailUser.text.toString()
            sendRecoveryEmail(email, claveRecu.toString())}

        btnVolver.setOnClickListener{
            val intent = Intent(this@RecuperarActivity, MainActivity::class.java)
            // Asegura que la pila de actividades anteriores se limpie
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
    fun sendRecoveryEmail(emailTo: String, newPassword: String) {
        val email = "schoolembensema@gmail.com"
        val password = "rbkp auzh skju ucqp"
        val props = Properties().apply {
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
        }

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(email, password)
            }
        })

        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(email))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo))
                subject = "Recuperación de Contraseña"
                setText("Tu nueva contraseña es: $newPassword")
            }

            Transport.send(message)
            println("Correo enviado correctamente.")
        } catch (e: MessagingException) {
            e.printStackTrace()
            println("Error al enviar el correo: ${e.message}")
        }
    }


}