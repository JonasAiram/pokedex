package com.br.pokedexv1.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.br.pokedexv1.R
import com.br.pokedexv1.commons.RequestState
import com.br.pokedexv1.databinding.ListarPokmeonFragmentBinding
import com.br.pokedexv1.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), ListarPokemonsAdapter.ClickPokemon {

//    private lateinit var homeViewModel: HomeViewModel
    private val homeViewModel by viewModel<HomeViewModel>()
    private var _binding: ListarPokmeonFragmentBinding? = null
    private val adapter by lazy { ListarPokemonsAdapter(this) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = ListarPokmeonFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvCard.adapter = adapter

        setUpObservers()
        homeViewModel.listarPokemons()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            homeViewModel.liveDataListarPokemons.collect {
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
        lifecycleScope.launch(Dispatchers.Main) {
            homeViewModel.liveDataObterDetalhesPokemon.collect {
                when (it) {
                    is RequestState.Error -> {
                        Log.e("detalhes", "ERROR" + it)
                        Toast.makeText(context, "Error" + it, Toast.LENGTH_LONG).show()
                    }
                    is RequestState.Success -> {
                        Log.e("detalhes", "SUCCESS")
                        Toast.makeText(context, "CARREGANDO...", Toast.LENGTH_LONG).show()

//                        @TODO ajustar para trazer 1 objeto em vez de uma lista e falta trazer a url
                        abrirDetalhes(it.list[0])
                    }
                    is RequestState.Loading -> {
                        Log.e("detalhes", "LOADING")
                        Toast.makeText(context, "CARREGANDO...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun abrirDetalhes(pokemon: Pokemon) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetalhes(pokemon)
//        @TODO apresentando bug ao voltar tela de detalhes e abrir um novo detalhe
        findNavController().navigate(action)
    }

    private fun carregarLista(listPokemon: List<Pokemon>) {
        Toast.makeText(context, "SUCCESS", Toast.LENGTH_LONG).show()
        adapter.submitList(listPokemon)
    }

    override fun clickPokemon(pokemon: Pokemon) {
        Toast.makeText(context,pokemon.name, Toast.LENGTH_SHORT).show()
        homeViewModel.obterDetalhesPokemon(pokemon.getIdUrl())
    }
}