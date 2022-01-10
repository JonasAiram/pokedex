package com.br.pokedexv1.presentation.ui.home

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.br.pokedexv1.commons.RequestState
import com.br.pokedexv1.databinding.DetalhesPokemonBinding
import com.br.pokedexv1.domain.model.Pokemon
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : Fragment() {

//    private lateinit var notificationsViewModel: NotificationsViewModel
//    private val viewModel by viewModel<HomeViewModel>()
    private var _binding: DetalhesPokemonBinding? = null
    private val binding get() = _binding!!

    private val args: DetalhesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetalhesPokemonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadPokemon(args.pokemon)


        return root
    }

    // @TODO ajustar types trazendo objeto types
    private fun loadPokemon(pokemon: Pokemon) {
        binding.tvId.text = pokemon.getIdString()
        binding.tvPokemon.text = pokemon.name
        binding.tvTipo.text = pokemon.types.toString()

        Glide.with(binding.root.context).load(pokemon.getImageId()).into(binding.ivImagem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}