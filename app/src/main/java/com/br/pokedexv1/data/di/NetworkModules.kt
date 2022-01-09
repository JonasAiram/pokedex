package com.br.pokedexv1.data.di

import com.br.pokedexv1.data.network.PokemonBaseUrlApi
import com.br.pokedexv1.data.network.PokemonEndPoints
import com.br.pokedexv1.data.network.RetrofitInitializer
import org.koin.dsl.module

val networkPokemon = module {
    fun getNetworkPokemon(): PokemonEndPoints {
        return RetrofitInitializer(PokemonBaseUrlApi.BASE_URL).pokemonAPIService()
    }
    single { getNetworkPokemon() }
}