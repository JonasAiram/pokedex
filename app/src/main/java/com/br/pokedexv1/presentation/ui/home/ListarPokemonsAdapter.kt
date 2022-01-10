package com.br.pokedexv1.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.br.pokedexv1.databinding.ItemCardBinding
import com.br.pokedexv1.domain.model.Pokemon
import com.bumptech.glide.Glide

class ListarPokemonsAdapter(var clickCardView: ClickPokemon) : ListAdapter<Pokemon, ListarPokemonsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon){
            binding.tvNome.text = item.name

            binding.cvContentCard.setOnClickListener{
                clickCardView.clickPokemon(item)
            }

            Glide.with(binding.root.context)
                .load(item.getImageUrl()).into(binding.ivFoto)

        }
    }

    interface ClickPokemon {
        fun clickPokemon(pokemon: Pokemon)
    }


}

class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.name == newItem.name
}
