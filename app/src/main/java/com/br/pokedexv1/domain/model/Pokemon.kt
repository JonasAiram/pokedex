package com.br.pokedexv1.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "pokemons")
@Parcelize
data class Pokemon(
    @PrimaryKey
    var name: String,
    var url: String,
    var id: Int? = null,
    val types: String = "",
//    val types: List<PokemonType>? = null,
    var favorite: Boolean = false
) : Parcelable {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$index.png"
    }
    fun getImageId(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${this.id}.png"
    }
    fun getIdString() = String.format("#%03d", id)
    fun getIdUrl() = Integer.parseInt(url.split("/".toRegex()).dropLast(1).last())
}