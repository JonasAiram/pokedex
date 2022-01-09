package com.br.pokedexv1.domain.model

//@Entity(tableName = "pokemons")
data class Pokemon(
    var id: Int?,
    var name: String,
    var url: String,
    val types: List<PokemonType>
)  {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$index.png"
    }
    fun getIdString() = String.format("#%03d", id)
    fun getIdUrl() = Integer.parseInt(url.split("/".toRegex()).dropLast(1).last())
}