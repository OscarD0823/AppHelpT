package com.projectogrado.helpt

object UsuarioManager {

    // Lista para almacenar los doctores
    private val doctores = mutableListOf<Doctor>()

    // Función para agregar un Doctor
    fun agregarDoctor(doctor: Doctor) {
        doctores.add(doctor)
    }

    // Obtener todos los Doctores
    fun obtenerDoctores(): List<Doctor> {
        return doctores
    }

    // Obtener un Doctor por documento
    fun obtenerDoctorPorDocumento(documento: String): Doctor? {
        return doctores.find { it.documento == documento }
    }

    // Eliminar un Doctor por documento
    fun eliminarDoctor(documento: String) {
        doctores.removeAll { it.documento == documento }
    }
}

