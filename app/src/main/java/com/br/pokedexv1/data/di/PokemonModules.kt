package com.br.pokedexv1.data.di

import com.br.pokedexv1.data.repository.PokemonRepositoryImplements
import com.br.pokedexv1.domain.usecase.PokemonUseCase
import com.br.pokedexv1.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModuleMain = module {
    viewModel {
        HomeViewModel(get())
    }
}

val repositoryModulePokemon = module {
    single { PokemonRepositoryImplements(get()) }
}

val useCaseModulePokemon = module {
    single { PokemonUseCase(get()) }
}
