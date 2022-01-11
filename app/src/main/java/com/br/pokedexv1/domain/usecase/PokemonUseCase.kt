package com.br.pokedexv1.domain.usecase

import com.br.pokedexv1.data.model.PokemonResponse
import com.br.pokedexv1.data.model.PokemonsResponse
import com.br.pokedexv1.data.repository.PokemonRepositoryImplements
import com.br.pokedexv1.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokemonUseCase(private val pokemonRepositoryImplements: PokemonRepositoryImplements) {

    suspend fun listarPokemons() = flow {
        val dadosPokemons = pokemonRepositoryImplements.listarPokemons()
        dadosPokemons.collect { emit(pokemonsResponseToPokemon(it)) }
    }

    suspend fun obterDetalhes(id: Int) = flow {
        val dadosPokemons = pokemonRepositoryImplements.obterDetalhesPokemon(id)
        dadosPokemons.collect { emit(pokemonResponseToPokemon(it)) }
    }

    suspend fun obterListaPokemonsFavoritos() : Flow<List<Pokemon>> {
        return pokemonRepositoryImplements.obterListaPokemonsFavoritos()
    }

    suspend fun favoritarPokemonInfo(pokemonModel: Pokemon) {
        return pokemonRepositoryImplements.favoritarPokemon(pokemonModel)
    }


    fun pokemonsResponseToPokemon(pokemonsResponse: PokemonsResponse?): List<Pokemon> {
        val list: MutableList<Pokemon> = ArrayList()
        pokemonsResponse?.results?.map { pokemonsResult ->

            val id = Integer.parseInt(pokemonsResult.url.split("/".toRegex()).dropLast(1).last())

            val pokemon = Pokemon(
                pokemonsResult.name,
                pokemonsResult.url,
                id
            )
            pokemon.url = pokemon.getImageUrl()
            list.add(pokemon)
        }
        val listPokemon: List<Pokemon> = list
        return listPokemon
    }

    fun pokemonResponseToPokemon(pokemonResponse: PokemonResponse?): List<Pokemon> {
        val list: MutableList<Pokemon> = ArrayList()
        pokemonResponse?.let {
            val pokemon = Pokemon(
                pokemonResponse.name,
                "",
                pokemonResponse.id,
                pokemonResponse.types.joinToString(",")
            )
            list.add(pokemon)
        }
        val listPokemon: List<Pokemon> = list
        return listPokemon
    }

}