package com.sdk.rvpagination103.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.sdk.rvpagination103.databinding.ItemDataBinding
import com.sdk.rvpagination103.model.CharacterData

class RickAndMortyAdapter :
    PagingDataAdapter<CharacterData, RickAndMortyAdapter.RickAndMortyViewHolder>(DiffCallBack()) {
    private class DiffCallBack : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyViewHolder {
        return RickAndMortyViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class RickAndMortyViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CharacterData?) {
            binding.apply {
                tv1.text = data?.name
                tv2.text = data?.species
                Glide.with(avatar)
                    .load(data?.image ?: "https://rickandmortyapi.com/api/character/avatar/21.jpeg")
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(avatar)
            }
        }
    }
}