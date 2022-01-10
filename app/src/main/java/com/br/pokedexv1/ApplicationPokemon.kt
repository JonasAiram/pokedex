package com.br.pokedexv1

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.br.pokedexv1.data.di.networkPokemon
import com.br.pokedexv1.data.di.repositoryModulePokemon
import com.br.pokedexv1.data.di.useCaseModulePokemon
import com.br.pokedexv1.data.di.viewModelModuleMain
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
                    viewModelModuleMain

                )
            )
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


}