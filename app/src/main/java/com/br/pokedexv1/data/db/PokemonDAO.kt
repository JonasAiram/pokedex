package com.br.pokedexv1.data.db

import androidx.room.*
import com.br.pokedexv1.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(vararg pokemon: Pokemon)

    @Update
    fun updatePokemon(pokemonInfo: Pokemon) : Int

    @Delete
    fun deletePokemon(vararg pokemoninfo: Pokemon): Int

    @Query("SELECT * FROM pokemons")
    fun loadAllPokemons(): Flow<List<Pokemon>>

}

