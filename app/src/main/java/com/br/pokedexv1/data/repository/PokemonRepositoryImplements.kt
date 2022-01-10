package com.br.pokedexv1.data.repository

import com.br.pokedexv1.commons.InvalidNetworking
import com.br.pokedexv1.commons.NotFoundNetWorking
import com.br.pokedexv1.data.model.PokemonResponse
import com.br.pokedexv1.data.network.PokemonEndPoints
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class PokemonRepositoryImplements(private val pokemonEndPoints: PokemonEndPoints) : PokemonRepository {
    override suspend fun listarPokemons() = flow {
        try {
            val api = pokemonEndPoints.listarPokemons()
            if (!api.isSuccessful) {
                throw InvalidNetworking(api.code(), api.errorBody()?.string() ?: "0")
            }
            emit(api.body())
        } catch (exception: Exception) {
            if (exception is UnknownHostException)
                throw NotFoundNetWorking()
            else
                throw exception
        }
    }

    override suspend fun obterDetalhesPokemon(id: Int) =  flow {
        try {
            val api = pokemonEndPoints.obterDetalhesDoPokemon(id)
            if (!api.isSuccessful) {
                throw InvalidNetworking(api.code(), api.errorBody()?.string() ?: "0")
            }
            emit(api.body())
        } catch (exception: Exception) {
            if (exception is UnknownHostException)
                throw NotFoundNetWorking()
            else
                throw exception
        }
    }

}
