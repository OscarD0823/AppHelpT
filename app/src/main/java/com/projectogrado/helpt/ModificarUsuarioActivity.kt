package com.projectogrado.helpt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ModificarUsuarioActivity : AppCompatActivity() {

    private lateinit var etNombreDoctor: EditText
    private lateinit var etDocumentoDoctor: EditText
    private lateinit var etPasswordDoctor: EditText
    private lateinit var etTelefonoDoctor: EditText
    private lateinit var btnGuardarDoctor: Button

    private var documentoOriginal: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_usuario)

        // Vincular las vistas con sus IDs
        etNombreDoctor = findViewById(R.id.etNombreDoctor)
        etDocumentoDoctor = findViewById(R.id.etDocumentoDoctor)
        etPasswordDoctor = findViewById(R.id.etPasswordDoctor)
        etTelefonoDoctor = findViewById(R.id.etTelefonoDoctor)
        btnGuardarDoctor = findViewById(R.id.btnGuardarDoctor)

        // Recibir el documento del doctor seleccionado de la actividad anterior
        documentoOriginal = intent.getStringExtra("documentoDoctor")

        // Cargar los datos del doctor si el documento es válido
        if (documentoOriginal != null) {
            cargarDatosDoctor(documentoOriginal!!)
        }

        // Guardar los cambios al hacer clic en el botón
        btnGuardarDoctor.setOnClickListener {
            guardarCambiosDoctor()
        }
    }

    private fun cargarDatosDoctor(documento: String) {
        // Obtener el doctor desde el UsuarioManager usando el documento
        val doctor = UsuarioManager.obtenerDoctorPorDocumento(documento)

        if (doctor != null) {
            etNombreDoctor.setText(doctor.nombre)
            etDocumentoDoctor.setText(doctor.documento)
            etPasswordDoctor.setText(doctor.password)
            etTelefonoDoctor.setText(doctor.telefono)
        } else {
            Toast.makeText(this, "Doctor no encontrado", Toast.LENGTH_SHORT).show()
            finish() // Salir de la actividad si no se encuentra el doctor
        }
    }

    private fun guardarCambiosDoctor() {
        val nuevoNombre = etNombreDoctor.text.toString()
        val nuevoDocumento = etDocumentoDoctor.text.toString()
        val nuevoPassword = etPasswordDoctor.text.toString()
        val nuevoTelefono = etTelefonoDoctor.text.toString()

        if (nuevoNombre.isNotEmpty() && nuevoDocumento.isNotEmpty() &&
            nuevoPassword.isNotEmpty() && nuevoTelefono.isNotEmpty()) {

            // Obtener el doctor actual
            val doctorActual = UsuarioManager.obtenerDoctorPorDocumento(documentoOriginal!!)

            if (doctorActual != null) {
                // Eliminar el doctor anterior si cambió el documento
                if (documentoOriginal != nuevoDocumento) {
                    UsuarioManager.eliminarDoctor(documentoOriginal!!)
                }

                // Crear un nuevo objeto Doctor con los cambios
                val doctorActualizado = Doctor(
                    nombre = nuevoNombre,
                    documento = nuevoDocumento,
                    password = nuevoPassword,
                    telefono = nuevoTelefono
                )

                // Agregar el doctor actualizado al UsuarioManager
                UsuarioManager.agregarDoctor(doctorActualizado)

                Toast.makeText(this, "Doctor actualizado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al actualizar el doctor", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
