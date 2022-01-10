package com.br.pokedexv1.data.network

import com.br.pokedexv1.data.model.PokemonResponse
import com.br.pokedexv1.data.model.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonEndPoints {

    //    @TODO Implementar os limites pegando do usecase
    @GET("pokemon/?limit=100")
    suspend fun listarPokemons(): Response<PokemonsResponse>

    @GET("pokemon/{id}")
    suspend fun obterDetalhesDoPokemon(@Path("id") id: Int): Response<PokemonResponse>

}

object PokemonBaseUrlApi {
    val BASE_URL: String = "https://pokeapi.co/api/v2/"
}