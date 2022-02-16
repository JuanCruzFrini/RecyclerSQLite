package com.example.recyclersqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclersqlite.databinding.ActivityNuevoBinding

class NuevoActivity : AppCompatActivity() {

    lateinit var binding: ActivityNuevoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuarda.setOnClickListener {
            if (binding.txtNombre.text.toString() != "" && binding.txtTelefono.text.toString() != "") {
                val dbContactos = DbContactos(this)
                val id = dbContactos.insertarContacto(
                    binding.txtNombre.text.toString(),
                    binding.txtTelefono.text.toString(),
                    binding.txtCorreoElectronico.text.toString()
                )
                if (id > 0) {
                    Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                    limpiar()
                } else {
                    Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun limpiar() {
        binding.txtNombre.setText("")
        binding.txtTelefono.setText("")
        binding.txtCorreoElectronico.setText("")
    }
    override fun onBackPressed() { startActivity(Intent(this, MainActivity::class.java)) }
}