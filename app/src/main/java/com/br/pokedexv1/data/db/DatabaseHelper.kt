package com.br.pokedexv1.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br.pokedexv1.domain.model.Pokemon

@Database(entities = arrayOf(Pokemon::class),version = 1)
abstract class DatabaseHelper:RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO
//    abstract fun pokemonTypeDAO(): PokemonTypeDAO


    companion object {
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper? {
            if (INSTANCE == null) {
                synchronized(DatabaseHelper::class) {
                    INSTANCE = Room.databaseBuilder(context, DatabaseHelper::class.java, "pokemondb")
                        //.allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}