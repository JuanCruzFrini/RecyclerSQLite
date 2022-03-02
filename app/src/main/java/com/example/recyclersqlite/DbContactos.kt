package com.example.recyclersqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class DbContactos(var context: Context?) : DbHelper(context) {

    fun insertarContacto(nombre: String?, telefono: String?, correo_electronico: String?): Long {
        var id: Long = 0
        try {
            val dbHelper = DbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("nombre", nombre)
            values.put("telefono", telefono)
            values.put("correo_electronico", correo_electronico)
            id = db.insert(TABLE_CONTACTOS, null, values)
        } catch (ex: Exception) {
            ex.toString()
        }
        return id
    }

    fun mostrarContactos(): ArrayList<Contactos> {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        val listaContactos: ArrayList<Contactos> = ArrayList<Contactos>()
        var contacto: Contactos
        val cursorContactos: Cursor = db.rawQuery("SELECT * FROM $TABLE_CONTACTOS ORDER BY nombre ASC", null)
        if (cursorContactos.moveToFirst()) {
            do {
                contacto = Contactos(cursorContactos.getInt(0), cursorContactos.getString(1),cursorContactos.getString(2),cursorContactos.getString(3))
                listaContactos.add(contacto)
            } while (cursorContactos.moveToNext())
        }
        cursorContactos.close()
        return listaContactos
    }

    fun verContacto(id: Int): Contactos? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var contacto: Contactos? = null
        val cursorContactos: Cursor = db.rawQuery("SELECT * FROM $TABLE_CONTACTOS WHERE id = $id LIMIT 1", null)
        if (cursorContactos.moveToFirst()) {
            contacto = Contactos(cursorContactos.getInt(0), cursorContactos.getString(1),cursorContactos.getString(2),cursorContactos.getString(3))
        }
        cursorContactos.close()
        return contacto
    }

    fun editarContacto(id: Int, nombre: String, telefono: String, correo_electronico: String): Boolean {
        var correcto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        correcto = try {
            db.execSQL("UPDATE $TABLE_CONTACTOS SET nombre = '$nombre', telefono = '$telefono', correo_electronico = '$correo_electronico' WHERE id='$id' ")
            true
        } catch (ex: Exception) {
            ex.toString()
            false
        } finally {
            db.close()
        }
        return correcto
    }

    fun eliminarContacto(id: Int): Boolean {
        var correcto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        correcto = try {
            db.execSQL("DELETE FROM $TABLE_CONTACTOS WHERE id = '$id'")
            true
        } catch (ex: Exception) {
            ex.toString()
            false
        } finally {
            db.close()
        }
        return correcto
    }

    fun guardarFavorito(id: Int) : Boolean{
        var correcto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        correcto = try {
            db.execSQL("UPDATE $TABLE_CONTACTOS SET favorito = 1 WHERE id='$id' ")
            true
        } catch (ex: Exception) {
            ex.toString()
            false
        } finally {
            db.close()
        }
        return correcto
    }

    fun eliminarFavorito(id: Int) : Boolean{
        var correcto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        db.execSQL("UPDATE $TABLE_CONTACTOS SET favorito = 0 WHERE id ='$id'")
        db.close()
        return correcto
    }

    fun mostrarFavoritos() : ArrayList<Contactos>? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var contacto: Contactos
        val favoritos: ArrayList<Contactos>? = ArrayList()
        val favorito = 1

        val cursor:Cursor = db.rawQuery("SELECT * FROM $TABLE_CONTACTOS WHERE favorito ='$favorito' ORDER BY nombre ASC ", null)
        if (cursor.moveToFirst()){
            do {
                contacto = Contactos(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))
                favoritos?.add(contacto)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return favoritos
    }

  /*  fun checkFav(id: Int): Boolean {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        val cursor:Cursor = db.rawQuery("SELECT $id FROM $TABLE_CONTACTOS WHERE favorito= 1", null)

        var retorno:Boolean?
        retorno = !cursor.equals(null)

        return retorno
    }*/


}