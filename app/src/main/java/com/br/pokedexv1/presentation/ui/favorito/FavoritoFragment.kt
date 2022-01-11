package com.br.pokedexv1.presentation.ui.favorito

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.br.pokedexv1.commons.RequestState
import com.br.pokedexv1.databinding.ListarPokmeonFragmentBinding
import com.br.pokedexv1.domain.model.Pokemon
import com.br.pokedexv1.presentation.ui.home.HomeViewModel
import com.br.pokedexv1.presentation.ui.home.ListarPokemonsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritoFragment : Fragment(), ListarPokemonsAdapter.ClickPokemon {

    private val adapter by lazy { ListarPokemonsAdapter(this) }
    private val viewModel by viewModel<FavoritoViewModel>()


    private var _binding: ListarPokmeonFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ListarPokmeonFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvCard.adapter = adapter

        setUpObservers()
        viewModel.listarPokemonsFavorito()


        return root
    }


    private fun setUpObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.liveDataListarPokemonsFavorito.collect {
                when (it) {
                    is RequestState.Error -> {
                        Log.e("listar_pokemons", "ERROR" + it)
                        Toast.makeText(context, "Error" + it, Toast.LENGTH_LONG).show()
                    }
                    is RequestState.Success -> {
//                        Log.e("listar_pokemons", "SUCCESS")
                        carregarLista(it.list)
                    }
                    is RequestState.Loading -> {
                        Log.e("listar_pokemons", "LOADING")
                        Toast.makeText(context, "CARREGANDO...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun carregarLista(listPokemon: List<Pokemon>) {
        Toast.makeText(context, "SUCCESS", Toast.LENGTH_LONG).show()
        adapter.submitList(listPokemon)
    }

    override fun clickPokemon(pokemon: Pokemon) {
        Toast.makeText(context,pokemon.name, Toast.LENGTH_SHORT).show()
//        viewModel.obterDetalhesPokemon(pokemon.getIdUrl())
    }

}