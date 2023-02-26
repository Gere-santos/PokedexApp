package com.example.pokedex.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon

class PokemonAdapter (private val items: List<Pokemon?>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
    return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return items.size    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: Pokemon?) = with(itemView){
            val imagePokemon = findViewById<ImageView>(R.id.imagePokemon)
            val textNumber = findViewById<TextView>(R.id.textNumber)
            val textNameType1 = findViewById<TextView>(R.id.textNameType1)
            val textName = findViewById<TextView>(R.id.textName)
            val textNameType2 = findViewById<TextView>(R.id.textNameType2)

            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(imagePokemon)
                textNumber.text = "Nª ${item.formattedNumber}"
                textName.text = item.name
                textNameType1.text = item.types[0].name

                if (item.types.size > 1){
                    textNameType2.text = item.types[1].name
                    textNameType2.visibility = View.VISIBLE
                }else{
                    textNameType2.visibility = View.GONE
                }

            }


        }
    }
}