package com.br.pokedexv1.data.repository

import com.br.pokedexv1.data.model.PokemonResponse
import com.br.pokedexv1.data.model.PokemonsResponse
import com.br.pokedexv1.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun listarPokemons() : Flow<PokemonsResponse?>
    suspend fun obterDetalhesPokemon(id: Int): Flow<PokemonResponse?>
    suspend fun favoritarPokemon(pokemon: Pokemon)
    suspend fun obterListaPokemonsFavoritos() : Flow<List<Pokemon>>


}