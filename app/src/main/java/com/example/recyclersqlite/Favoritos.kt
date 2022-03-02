package com.example.recyclersqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclersqlite.databinding.ActivityFavoritosBinding

class Favoritos : AppCompatActivity() {

    lateinit var binding: ActivityFavoritosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Favoritos"

        val dbContactos = DbContactos(this)
        binding.favoritosLista.adapter = ListaFavsAdapter(dbContactos.mostrarFavoritos()!!, dbContactos.mostrarFavoritos()!!)
        binding.favoritosLista.layoutManager = LinearLayoutManager(this)
    }
/*
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
*/
}