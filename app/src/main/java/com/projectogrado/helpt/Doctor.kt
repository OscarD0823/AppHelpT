package com.projectogrado.helpt

data class Doctor(
    val nombre: String,
    val documento: String,
    val password: String,
    val telefono: String,
    val rol: String = "Doctor" // El rol se establece por defecto como "Doctor"
)