package com.br.pokedexv1.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.pokedexv1.commons.RequestState
import com.br.pokedexv1.domain.model.Pokemon
import com.br.pokedexv1.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pokemonUseCase: PokemonUseCase,
) : ViewModel() {

    private val _liveDataListarPokemons = MutableSharedFlow<RequestState<List<Pokemon>>>()
    val liveDataListarPokemons get() = _liveDataListarPokemons.asSharedFlow()

    private val _liveDataObterDetalhesPokemon = MutableSharedFlow<RequestState<List<Pokemon>>>()
    val liveDataObterDetalhesPokemon get() = _liveDataObterDetalhesPokemon.asSharedFlow()

    fun listarPokemons() = viewModelScope.launch {
        pokemonUseCase.listarPokemons()
            .onStart {  }
            .onCompletion {  }
            .catch {
                _liveDataListarPokemons.emit(RequestState.Error(it))
            }
            .collect {
                _liveDataListarPokemons.emit(RequestState.Success(it))
            }
    }

    fun obterDetalhesPokemon(id: Int) = viewModelScope.launch {
        pokemonUseCase.obterDetalhes(id)
            .onStart {  }
            .onCompletion {  }
            .catch {
                _liveDataObterDetalhesPokemon.emit(RequestState.Error(it))
            }
            .collect {
                _liveDataObterDetalhesPokemon.emit(RequestState.Success(it))
            }
    }

}