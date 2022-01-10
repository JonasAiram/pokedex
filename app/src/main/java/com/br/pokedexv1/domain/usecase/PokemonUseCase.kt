package com.br.pokedexv1.domain.usecase

import com.br.pokedexv1.data.model.PokemonResponse
import com.br.pokedexv1.data.model.PokemonsResponse
import com.br.pokedexv1.data.repository.PokemonRepositoryImplements
import com.br.pokedexv1.domain.model.Pokemon
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokemonUseCase(private val pokemonRepositoryImplements: PokemonRepositoryImplements) {

    suspend fun listarPokemons() = flow {
        val dadosPokemons = pokemonRepositoryImplements.listarPokemons()
        dadosPokemons.collect { emit(pokemonsResponseToPokemon(it)) }
    }

    suspend fun pokemonsResponseToPokemon(pokemonsResponse: PokemonsResponse?): List<Pokemon> {
        val list: MutableList<Pokemon> = ArrayList()
        pokemonsResponse?.results?.map { pokemonsResult ->

            val id = Integer.parseInt(pokemonsResult.url.split("/".toRegex()).dropLast(1).last())
            val detalhesPokemon = pokemonRepositoryImplements.obterDetalhesPokemon(id)

            detalhesPokemon.collect { pokemonResponse ->
                pokemonResponse?.let {
                    val pokemon = Pokemon(
                        pokemonResponse.name,
                        pokemonsResult.url,
                        pokemonResponse.id,
                        pokemonResponse.types.map { type -> type.type }
                    )
                list.add(pokemon)
                }
            }
        }
        val listPokemon: List<Pokemon> = list
        return listPokemon
    }

}