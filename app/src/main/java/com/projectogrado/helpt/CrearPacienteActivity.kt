package com.projectogrado.helpt

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.util.*

class CrearPacienteActivity : AppCompatActivity() {

    // Declaración de los campos
    private lateinit var etNombrePaciente: EditText
    private lateinit var etDocumentoPaciente: EditText
    private lateinit var spinnerTipoDocumentoPaciente: Spinner
    private lateinit var etTelefonoPaciente: EditText
    private lateinit var etAcudientePaciente: EditText
    private lateinit var etFechaNacimientoPaciente: EditText

    private lateinit var etNombreResponsable: EditText
    private lateinit var etDocumentoResponsable: EditText
    private lateinit var spinnerTipoDocumentoResponsable: Spinner
    private lateinit var etTelefonoResponsable: EditText
    private lateinit var etFechaNacimientoResponsable: EditText

    private lateinit var btnGuardarPaciente: Button
    private lateinit var btnVolver: Button

    // Nombre del archivo de SharedPreferences
    private val PREFERENCES_FILE = "credenciales"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_paciente)

        // Inicializar los campos de entrada
        etNombrePaciente = findViewById(R.id.etNombrePaciente)
        etDocumentoPaciente = findViewById(R.id.etDocumentoPaciente)
        spinnerTipoDocumentoPaciente = findViewById(R.id.spinnerTipoDocumentoPaciente)
        etTelefonoPaciente = findViewById(R.id.etTelefonoPaciente)
        etAcudientePaciente = findViewById(R.id.etAcudientePaciente)
        etFechaNacimientoPaciente = findViewById(R.id.etFechaNacimientoPaciente)

        etNombreResponsable = findViewById(R.id.etNombreResponsable)
        etDocumentoResponsable = findViewById(R.id.etDocumentoResponsable)
        spinnerTipoDocumentoResponsable = findViewById(R.id.spinnerTipoDocumentoResponsable)
        etTelefonoResponsable = findViewById(R.id.etTelefonoResponsable)
        etFechaNacimientoResponsable = findViewById(R.id.etFechaNacimientoResponsable)

        btnGuardarPaciente = findViewById(R.id.btnGuardarPaciente)
        btnVolver = findViewById(R.id.btnVolver)

        // Configurar los DatePickers para los campos de fecha de nacimiento
        configurarDatePicker(etFechaNacimientoPaciente)
        configurarDatePicker(etFechaNacimientoResponsable)

        // Guardar los datos al hacer clic en el botón
        btnGuardarPaciente.setOnClickListener {
            guardarPacienteYResponsable()
        }

        btnVolver.setOnClickListener( 
            {
                val intent = Intent(this, DoctorActivity::class.java)
                startActivity(intent)
            }
        )
    }

    // Función para configurar el DatePicker en un campo de fecha
    private fun configurarDatePicker(editText: EditText) {
        editText.setOnClickListener {
            val calendario = Calendar.getInstance()
            val year = calendario.get(Calendar.YEAR)
            val month = calendario.get(Calendar.MONTH)
            val day = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { _, yearSelected, monthSelected, daySelected ->
                    // Mostrar la fecha seleccionada en el EditText
                    editText.setText("$daySelected/${monthSelected + 1}/$yearSelected")
                },
                year, month, day
            )
            datePicker.show()
        }
    }

    // Función para guardar los datos del Paciente y Responsable
    private fun guardarPacienteYResponsable() {
        // Datos del Paciente
        val nombrePaciente = etNombrePaciente.text.toString()
        val documentoPaciente = etDocumentoPaciente.text.toString()
        val tipoDocumentoPaciente = spinnerTipoDocumentoPaciente.selectedItem.toString()
        val telefonoPaciente = etTelefonoPaciente.text.toString()
        val acudientePaciente = etAcudientePaciente.text.toString()
        val fechaNacimientoPaciente = etFechaNacimientoPaciente.text.toString()

        // Datos del Responsable
        val nombreResponsable = etNombreResponsable.text.toString()
        val documentoResponsable = etDocumentoResponsable.text.toString()
        val tipoDocumentoResponsable = spinnerTipoDocumentoResponsable.selectedItem.toString()
        val telefonoResponsable = etTelefonoResponsable.text.toString()
        val fechaNacimientoResponsable = etFechaNacimientoResponsable.text.toString()

        // Validar que todos los campos estén llenos
        if (nombrePaciente.isNotEmpty() && documentoPaciente.isNotEmpty() && telefonoPaciente.isNotEmpty() &&
            acudientePaciente.isNotEmpty() && fechaNacimientoPaciente.isNotEmpty() &&
            nombreResponsable.isNotEmpty() && documentoResponsable.isNotEmpty() &&
            telefonoResponsable.isNotEmpty() && fechaNacimientoResponsable.isNotEmpty()) {

            // Guardar Paciente y Responsable en SharedPreferences
            val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            // Crear objeto JSON para el Paciente
            val pacienteData = JSONObject()
            pacienteData.put("nombre", nombrePaciente)
            pacienteData.put("tipoDocumento", tipoDocumentoPaciente)
            pacienteData.put("telefono", telefonoPaciente)
            pacienteData.put("acudiente", acudientePaciente)
            pacienteData.put("fechaNacimiento", fechaNacimientoPaciente)
            pacienteData.put("password", documentoPaciente) // Contraseña es el documento

            // Crear objeto JSON para el Responsable
            val responsableData = JSONObject()
            responsableData.put("nombre", nombreResponsable)
            responsableData.put("tipoDocumento", tipoDocumentoResponsable)
            responsableData.put("telefono", telefonoResponsable)
            responsableData.put("fechaNacimiento", fechaNacimientoResponsable)
            responsableData.put("password", documentoResponsable) // Contraseña es el documento

            // Guardar los datos en SharedPreferences
            editor.putString(documentoPaciente, pacienteData.toString())
            editor.putString(documentoResponsable, responsableData.toString())
            editor.apply()

            Toast.makeText(this, "Paciente y Responsable creados correctamente", Toast.LENGTH_SHORT).show()

            // Limpiar los campos
            limpiarCampos()
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para limpiar los campos después de guardar
    private fun limpiarCampos() {
        etNombrePaciente.text.clear()
        etDocumentoPaciente.text.clear()
        etTelefonoPaciente.text.clear()
        etAcudientePaciente.text.clear()
        etFechaNacimientoPaciente.text.clear()
        etNombreResponsable.text.clear()
        etDocumentoResponsable.text.clear()
        etTelefonoResponsable.text.clear()
        etFechaNacimientoResponsable.text.clear()
    }
}
