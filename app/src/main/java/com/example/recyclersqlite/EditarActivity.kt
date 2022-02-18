package com.example.recyclersqlite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.VerActivity
import com.example.recyclersqlite.databinding.ActivityVerBinding

class EditarActivity : AppCompatActivity() {

    lateinit var binding: ActivityVerBinding
    lateinit var contacto: Contactos
    var correcto = false
    var id = 0

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getInt("ID") ?: null!!.toInt()
        } else {
            savedInstanceState.getSerializable("ID") as Int
        }
        val dbContactos = DbContactos(this)

        contacto = dbContactos.verContacto(id)!!
        binding.txtNombre.setText(contacto.nombre)
        binding.txtTelefono.setText(contacto.telefono)
        binding.txtCorreoElectronico.setText(contacto.correo_electronico)
        binding.btnGuarda.setOnClickListener {

            if (binding.txtNombre.text.toString() != "" && binding.txtTelefono.text.toString() != "") {
                correcto = dbContactos.editarContacto(id, binding.txtNombre.text.toString(), binding.txtTelefono.text.toString(), binding.txtCorreoElectronico.text.toString())
                if (correcto) {
                    Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show()
                    verRegistro()
                } else {
                    Toast.makeText(this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun verRegistro() { startActivity(Intent(this, VerActivity::class.java).putExtra("ID", id)) }

    override fun onBackPressed() { startActivity(Intent(this, MainActivity::class.java)) }
}