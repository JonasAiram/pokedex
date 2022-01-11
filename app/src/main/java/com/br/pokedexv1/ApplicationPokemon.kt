package com.br.pokedexv1

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.br.pokedexv1.data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationPokemon : Application(), LifecycleObserver {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationPokemon)
            modules(
                listOf(
                    repositoryModulePokemon,
                    useCaseModulePokemon,
                    networkPokemon,
                    viewModelModuleMain,
                    pokemonModuleDAO,
                    viewModelModuleFavorito

                )
            )
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


}