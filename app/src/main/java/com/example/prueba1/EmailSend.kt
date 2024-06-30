package com.example.prueba1

import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailSend {
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