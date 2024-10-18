package com.projectogrado.helpt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PREFERENCES_FILE = "credenciales"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agregar el usuario admin manualmente
        guardarCredenciales("admin", "admin")

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Cargar credenciales desde SharedPreferences
        val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val users = loadUsers(sharedPreferences)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString() // Documento es el "email" aquí
            val password = etPassword.text.toString()

            // Validar las credenciales
            if (users.containsKey(email) && users[email] == password) {
                when (email) {
                    "admin" -> {
                        // Login exitoso para admin
                        val intent = Intent(this, AdminActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else -> {
                        // Login exitoso para los usuarios creados dinámicamente (doctores)
                        val intent = Intent(this, DoctorActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para guardar credenciales en SharedPreferences
    private fun guardarCredenciales(email: String, password: String) {
        val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(email, password)
        editor.apply() // Aplicar cambios
    }

    // Función para cargar credenciales desde SharedPreferences
    private fun loadUsers(sharedPreferences: android.content.SharedPreferences): MutableMap<String, String> {
        val users = mutableMapOf<String, String>()

        // Iterar sobre todas las entradas de SharedPreferences
        for (entry in sharedPreferences.all) {
            val email = entry.key
            val password = entry.value as? String
            if (password != null) {
                users[email] = password
            }
        }

        return users
    }
}
