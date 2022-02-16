package com.example.recyclersqlite

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.VerActivity
import java.util.stream.Collectors

class ListaContactosAdapter(var listaContactos: ArrayList<Contactos>, var listaOriginal: ArrayList<Contactos>) : RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lista_item_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        holder.viewNombre.text = listaContactos[position].nombre
        holder.viewTelefono.text = listaContactos[position].telefono
        holder.viewCorreo.text = listaContactos[position].correo_electronico
    }

    fun filtrado(txtBuscar: String?) {
        val longitud = txtBuscar?.length
        if (longitud == 0) {
            listaContactos.clear()
            listaContactos.addAll(listaOriginal)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val collecion: List<Contactos> = listaContactos.stream()
                    .filter { i: Contactos ->
                        i.nombre.toLowerCase().contains(txtBuscar?.toLowerCase() ?: "")
                    }.collect(Collectors.toList<Any>()) as List<Contactos>
                listaContactos.clear()
                listaContactos.addAll(collecion)
            } else {
                for (c in listaOriginal) {
                    if (c.nombre.toLowerCase().contains(txtBuscar?.toLowerCase() ?: "") ) {
                        listaContactos.add(c)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int { return listaContactos.size }

    inner class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewNombre: TextView = itemView.findViewById(R.id.viewNombre)
        var viewTelefono: TextView = itemView.findViewById(R.id.viewTelefono)
        var viewCorreo: TextView = itemView.findViewById(R.id.viewCorreo)

        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, VerActivity::class.java)
                intent.putExtra("ID", listaContactos[adapterPosition].id)
                context.startActivity(intent)
            }
        }
    }
}