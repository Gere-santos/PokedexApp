package com.example.pokedex.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon




class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var simpleSearchView :SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_pokemon)



        Thread(Runnable { loadPokemons() }).start()



    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {

            val pokemons: List<Pokemon?> = it.map { PokemonResult ->
                val number =
                    PokemonResult.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                        .replace("/", "").toInt()
                val pokemonApiResult = PokemonRepository.getPokemon(number)
                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map
                    { type -> type.type

                    })
                }
            }

            //val layoutManager = LinearLayoutManager(this)

         recyclerView.post{ recyclerView.layoutManager = GridLayoutManager(this,2)
             recyclerView.adapter = PokemonAdapter(pokemons)
         }


         }
    }

}