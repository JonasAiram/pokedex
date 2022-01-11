package com.br.pokedexv1.data.db

import androidx.room.*
import com.br.pokedexv1.domain.model.PokemonType
import kotlinx.coroutines.flow.Flow

//@Dao
//interface PokemonTypeDAO {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertTipo(vararg pokemon: PokemonType)
//
//    @Update
//    fun updatePokemonType(pokemonInfo: PokemonType) : Int
//
//    @Delete
//    fun deletePokemonType(vararg pokemoninfo: PokemonType): Int
//
//    @Query("SELECT * FROM pokemons")
//    fun loadAllPokemonTypes(): Flow<List<PokemonType>>

//}
