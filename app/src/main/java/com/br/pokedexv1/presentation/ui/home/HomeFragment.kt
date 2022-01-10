package com.br.pokedexv1.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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

//            homeViewModel.liveDataObterDetalhesPokemon.collect {
//                when (it) {
//                    is RequestState.Error -> {
//                        Log.e("detalhes", "ERROR" + it)
//                        Toast.makeText(context, "Error" + it, Toast.LENGTH_LONG).show()
//                    }
//                    is RequestState.Success -> {
//                        Log.e("detalhes", "SUCCESS")
////                        @TODO Exibir detalhes do pokemon na tela
//                    }
//                    is RequestState.Loading -> {
//                        Log.e("detalhes", "LOADING")
//                        Toast.makeText(context, "CARREGANDO...", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
        }
    }

    private fun carregarLista(listPokemon: List<Pokemon>) {
        Log.d("teste", listPokemon[0].types!![0].name)
        Toast.makeText(context, "SUCCESS -> " + listPokemon[0].name, Toast.LENGTH_LONG).show()
        adapter.submitList(listPokemon)
    }

    override fun clickPokemon(pokemon: Pokemon) {
        Toast.makeText(context,pokemon.name, Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_navigation_home_to_navigation_detalhes)

//        homeViewModel.obterDetalhesPokemon(pokemon.getIdUrl())
//        val intent = Intent(this, DetalhesActivity::class.java)
//        intent.putExtra("pokemonId", pokemon.getIdUrl())
//        startActivity(intent)
    }
}