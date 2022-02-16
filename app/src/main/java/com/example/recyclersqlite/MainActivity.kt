package com.example.recyclersqlite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclersqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ListaContactosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listaContactos.layoutManager = LinearLayoutManager(this)
        val dbContactos = DbContactos(this)

        adapter = ListaContactosAdapter(dbContactos.mostrarContactos() , dbContactos.mostrarContactos() )
        binding.listaContactos.adapter = adapter
        binding.favNuevo.setOnClickListener{ nuevoRegistro() }
        binding.txtBuscar.setOnQueryTextListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuNuevo -> {
                nuevoRegistro()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun nuevoRegistro() { startActivity(Intent(this, NuevoActivity::class.java)) }

    override fun onQueryTextSubmit(s: String?): Boolean { return false }

    override fun onQueryTextChange(s: String?): Boolean {
        adapter.filtrado(s!!)
        return false
    }

    override fun onBackPressed() {finish()}
}