package com.example.pokedex.domain

data class Pokemon(

    val number: Int,
    val name: String,
    val types: List<PokemonType>
    )
{
    val formattedNumber = number.toString().padStart(3, '0')
    val imageUrl: String = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"

}
