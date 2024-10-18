package com.projectogrado.helpt

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CrearUsuarioActivity : AppCompatActivity() {

    private lateinit var etNombreDoctor: EditText
    private lateinit var etDocumentoDoctor: EditText
    private lateinit var etPasswordDoctor: EditText
    private lateinit var etTelefonoDoctor: EditText
    private lateinit var btnCrearDoctor: Button

    private val PREFERENCES_FILE = "credenciales"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)

        // Vincular las vistas con sus IDs
        etNombreDoctor = findViewById(R.id.etNombreDoctor)
        etDocumentoDoctor = findViewById(R.id.etDocumentoDoctor)
        etPasswordDoctor = findViewById(R.id.etPasswordDoctor)
        etTelefonoDoctor = findViewById(R.id.etTelefonoDoctor)
        btnCrearDoctor = findViewById(R.id.btnCrearDoctor)

        // Configurar el botón de creación del doctor
        btnCrearDoctor.setOnClickListener {
            crearDoctor()
        }
    }

    private fun crearDoctor() {
        // Datos del doctor
        val nombreDoctor = etNombreDoctor.text.toString()
        val documentoDoctor = etDocumentoDoctor.text.toString() // Documento es el usuario
        val passwordDoctor = etPasswordDoctor.text.toString()
        val telefonoDoctor = etTelefonoDoctor.text.toString()

        if (nombreDoctor.isNotEmpty() && documentoDoctor.isNotEmpty() &&
            passwordDoctor.isNotEmpty() && telefonoDoctor.isNotEmpty()) {

            // Guardar el doctor en SharedPreferences
            guardarCredenciales(documentoDoctor, passwordDoctor)

            Toast.makeText(this, "Doctor creado correctamente", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarCredenciales(documento: String, password: String) {
        // Guardar el documento y la contraseña en SharedPreferences
        val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(documento, password) // Documento como clave, contraseña como valor
        editor.apply()
    }
}


