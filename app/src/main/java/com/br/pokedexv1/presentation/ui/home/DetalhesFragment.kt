package com.br.pokedexv1.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.br.pokedexv1.databinding.DetalhesPokemonBinding
import com.br.pokedexv1.domain.model.Pokemon

class DetalhesFragment : Fragment() {

//    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: DetalhesPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetalhesPokemonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val bundle = this.arguments
        if (bundle != null) {
            val receivedCar: Pokemon? = bundle.getParcelable("pokemon")
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}