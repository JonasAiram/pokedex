package com.br.pokedexv1.presentation.ui.favorito

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.pokedexv1.commons.RequestState
import com.br.pokedexv1.domain.model.Pokemon
import com.br.pokedexv1.domain.usecase.PokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritoViewModel(
    private val pokemonUseCase: PokemonUseCase
    ) : ViewModel() {

    private val _liveDataListarPokemonsFavorito = MutableSharedFlow<RequestState<List<Pokemon>>>()
    val liveDataListarPokemonsFavorito get() = _liveDataListarPokemonsFavorito.asSharedFlow()

    private val _liveDataObterDetalhesPokemonFavorito = MutableSharedFlow<RequestState<List<Pokemon>>>()
    val liveDataObterDetalhesPokemonFavorito get() = _liveDataObterDetalhesPokemonFavorito.asSharedFlow()

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon = _pokemon

    fun listarPokemonsFavorito() = viewModelScope.launch {
        pokemonUseCase.obterListaPokemonsFavoritos()
            .onStart {  }
            .onCompletion {  }
            .catch {
                _liveDataListarPokemonsFavorito.emit(RequestState.Error(it))
            }
            .collect {
                _liveDataListarPokemonsFavorito.emit(RequestState.Success(it))
            }
    }

    fun obterDetalhesPokemonFavorito(id: Int) = viewModelScope.launch {
        pokemonUseCase.obterDetalhes(id)
            .onStart {  }
            .onCompletion {  }
            .catch {
                _liveDataObterDetalhesPokemonFavorito.emit(RequestState.Error(it))
            }
            .collect {
                _pokemon.value = it[0]
                _liveDataObterDetalhesPokemonFavorito.emit(RequestState.Success(it))
            }
    }

}