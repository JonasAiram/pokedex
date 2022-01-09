package com.br.pokedexv1.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.br.pokedexv1.domain.model.PokemonType
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class PokemonsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    var results: List<PokemonResult>
): Parcelable

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)