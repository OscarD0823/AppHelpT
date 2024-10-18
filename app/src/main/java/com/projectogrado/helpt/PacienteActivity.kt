package com.projectogrado.helpt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PacienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)

        // Botones de la vista de paciente
        val btnTerapias = findViewById<Button>(R.id.btnTerapias)
        val btnRecompensas = findViewById<Button>(R.id.btnRecompensas)

        // Manejar clic del botón Terapias
        btnTerapias.setOnClickListener {
            // Redirigir a la actividad de terapias
            val intent = Intent(this, TerapiasActivity::class.java)
            startActivity(intent)
        }

        // Manejar clic del botón Recompensas
        btnRecompensas.setOnClickListener {
            // Redirigir a la actividad de recompensas
            val intent = Intent(this, RecompensasActivity::class.java)
            startActivity(intent)
        }
    }
}
