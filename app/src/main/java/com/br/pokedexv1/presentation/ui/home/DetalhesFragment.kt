package com.br.pokedexv1.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.br.pokedexv1.databinding.DetalhesPokemonBinding
import com.br.pokedexv1.domain.model.Pokemon
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

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
        setUpListeners()

        return root
    }


    private fun setUpListeners() {
        binding.ibFavorito.setOnClickListener {
            viewModel.onClickFavoriteButton()
        }
    }

    // @TODO ajustar types trazendo objeto types
    private fun loadPokemon(pokemon: Pokemon) {
        binding.tvId.text = pokemon.getIdString()
        binding.tvPokemon.text = pokemon.name
        binding.tvTipo.text = pokemon.types

        Glide.with(binding.root.context).load(pokemon.getImageId()).into(binding.ivImagem)

        // Verificar se como pode compartilhar viewModel para capturar esse valor pela propria viewmodel
        viewModel.pokemon.value = args.pokemon

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}