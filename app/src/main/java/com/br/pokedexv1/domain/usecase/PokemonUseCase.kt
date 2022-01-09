package com.br.pokedexv1.domain.usecase

import com.br.pokedexv1.commons.InvalidNetworking
import com.br.pokedexv1.commons.PokemonInvalidException
import com.br.pokedexv1.data.model.PokemonsResponse
import com.br.pokedexv1.data.repository.PokemonRepositoryImplements
import com.br.pokedexv1.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokemonUseCase(private val pokemonRepositoryImplements: PokemonRepositoryImplements) {

    suspend fun listarPokemons(): Flow<Pokemon> = flow {
        val dadosPokemons = pokemonRepositoryImplements.listarPokemons()
        dadosPokemons.collect { emit(pokemonResponseToPokemon(it)) }
    }

    private fun pokemonResponseToPokemon(pokemonsResponse: PokemonsResponse?): Pokemon {
        pokemonsResponse?.results?.map { pokemonsResult ->
                val pokemon = Pokemon(
                    pokemonsResult.name,
                    pokemonsResult.url
                )
                return pokemon
            }
        throw PokemonInvalidException()
    }

}