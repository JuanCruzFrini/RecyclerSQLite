package com.example

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclersqlite.Contactos
import com.example.recyclersqlite.DbContactos
import com.example.recyclersqlite.EditarActivity
import com.example.recyclersqlite.MainActivity
import com.example.recyclersqlite.databinding.ActivityVerBinding

class VerActivity : AppCompatActivity() {

    lateinit var binding: ActivityVerBinding
    lateinit var contacto: Contactos
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuarda.visibility = View.INVISIBLE
        id = if (savedInstanceState == null) {
            intent.extras?.getInt("ID") ?: 1.toInt()
        } else {
            savedInstanceState.getSerializable("ID") as Int
        }
        val dbContactos = DbContactos(this)
        contacto = dbContactos.verContacto(id)!!

        binding.txtNombre.setText(contacto.nombre)
        binding.txtTelefono.setText(contacto.telefono)
        binding.txtCorreoElectronico.setText(contacto.correo_electronico)
        binding.txtNombre.inputType = InputType.TYPE_NULL
        binding.txtTelefono.inputType = InputType.TYPE_NULL
        binding.txtCorreoElectronico.inputType = InputType.TYPE_NULL
        binding.fabEditar.setOnClickListener { startActivity(Intent(this, EditarActivity::class.java).putExtra("ID", id)) }
        binding.fabEliminar.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("¿Desea eliminar este contacto?")
                .setPositiveButton("SI") { dialogInterface, i -> if (dbContactos.eliminarContacto(id)) { lista() } }
                .setNegativeButton("NO") { dialogInterface, i -> }.show()
        }
    }

    private fun lista() { startActivity( Intent(this, MainActivity::class.java)) }

    override fun onBackPressed() { lista() }
}