package com.br.pokedexv1.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonType(
    val name: String
) : Parcelable