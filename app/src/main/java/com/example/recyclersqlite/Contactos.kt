package com.example.recyclersqlite

data class Contactos(val id:Int, val nombre:String, val telefono:String, val correo_electronico : String, val favorito: Boolean = false, val enCarrito:Boolean = false)
