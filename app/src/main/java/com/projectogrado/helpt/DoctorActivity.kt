package com.projectogrado.helpt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doctor)
        val btnViewUsers = findViewById<Button>(R.id.btnViewUsers)
        val btnCreateUser = findViewById<Button>(R.id.btnCreateUser)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Manejar clic del botón Ver Usuario
        btnViewUsers.setOnClickListener {
            // Redirigir a la actividad de lista de usuarios (crea esta actividad después)
            //val intent = Intent(this, ModificarUsuarioActivity::class.java)
            startActivity(intent)
        }

        // Manejar clic del botón Crear Usuario
        btnCreateUser.setOnClickListener {
            // Redirigir a la actividad de creación de usuarios (crea esta actividad después)
            val intent = Intent(this, CrearPacienteActivity::class.java)
            startActivity(intent)
        }

        // Manejar clic del botón Cerrar Sesión
        btnLogout.setOnClickListener {
            // Volver a la pantalla de login y finalizar la actividad actual
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra AdminActivity para que no pueda volver con el botón atrás
    }
    }
}