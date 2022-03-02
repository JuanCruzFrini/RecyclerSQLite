package com.example.recyclersqlite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.VerActivity

class ListaFavsAdapter(var listaContactos: ArrayList<Contactos>, var listaOriginal: ArrayList<Contactos>) : RecyclerView.Adapter<ListaFavsAdapter.ContactoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lista_item_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        holder.viewNombre.text = listaContactos[position].nombre
        holder.viewTelefono.text = listaContactos[position].telefono
        holder.viewCorreo.text = listaContactos[position].correo_electronico

        holder.noFav.setOnClickListener{
            holder.fav.visibility = View.VISIBLE
            holder.noFav.visibility = View.GONE
            val context = holder.itemView.context
            val db = DbContactos(context)
            db.guardarFavorito(listaContactos[holder.adapterPosition].id)
        }

        holder.fav.setOnClickListener{
            holder.fav.visibility = View.GONE
            holder.noFav.visibility = View.VISIBLE
            val context = holder.itemView.context
            val db = DbContactos(context)
            db.eliminarFavorito(listaContactos[holder.adapterPosition].id)
        }

    }
    override fun getItemCount(): Int { return listaContactos.size }

    inner class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewNombre: TextView = itemView.findViewById(R.id.viewNombre)
        var viewTelefono: TextView = itemView.findViewById(R.id.viewTelefono)
        var viewCorreo: TextView = itemView.findViewById(R.id.viewCorreo)
        var fav: ImageButton = itemView.findViewById(R.id.btnFav)
        var noFav: ImageButton = itemView.findViewById(R.id.btnNoFav)

        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, VerActivity::class.java)
                intent.putExtra("ID", listaContactos[adapterPosition].id)
                context.startActivity(intent)
            }

            noFav.setOnClickListener{
                fav.visibility = View.VISIBLE
                noFav.visibility = View.GONE
                val context = itemView.context
                val db = DbContactos(context)
                db.guardarFavorito(listaContactos[adapterPosition].id)
            }
            fav.setOnClickListener {
                fav.visibility = View.GONE
                noFav.visibility = View.VISIBLE
                val context = itemView.context
                val db = DbContactos(context)
                db.eliminarFavorito(listaContactos[adapterPosition].id)
            }
        }
    }
}