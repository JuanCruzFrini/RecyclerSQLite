package com.example.recyclersqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NuevoActivity : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var txtTelefono:EditText
    lateinit var txtCorreoElectronico:EditText
    lateinit var btnGuarda: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)
        txtNombre = findViewById(R.id.txtNombre)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico)
        btnGuarda = findViewById(R.id.btnGuarda)
        btnGuarda.setOnClickListener {
            if (txtNombre.text.toString() != "" && txtTelefono.text.toString() != "") {
                val dbContactos = DbContactos(this@NuevoActivity)
                val id = dbContactos.insertarContacto(
                    txtNombre.text.toString(),
                    txtTelefono.text.toString(),
                    txtCorreoElectronico.text.toString()
                )
                if (id > 0) {
                    Toast.makeText(this@NuevoActivity, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                    limpiar()
                } else {
                    Toast.makeText(this@NuevoActivity, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@NuevoActivity, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun limpiar() {
        txtNombre.setText("")
        txtTelefono.setText("")
        txtCorreoElectronico.setText("")
    }

    override fun onBackPressed() {

        startActivity(Intent(this, MainActivity::class.java))
        super.onBackPressed()

    }
}