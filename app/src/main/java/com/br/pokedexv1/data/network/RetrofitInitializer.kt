package com.br.pokedexv1.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer(baseUrl: String) {

    var okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()) // .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    fun pokemonAPIService(): PokemonEndPoints {
        return retrofit.create(PokemonEndPoints::class.java)
    }
}
