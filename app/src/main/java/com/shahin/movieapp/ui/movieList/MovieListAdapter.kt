package com.shahin.movieapp.ui.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.ItemListMainPageBinding
import com.shahin.movieapp.model.MovieItemList
import com.shahin.movieapp.ui.utiles.glideLoad

class MovieListAdapter(
    val context: Context,
    val listener: ItemClickListener
) : ListAdapter<MovieItemList, MovieListAdapter.MovieItemViewHolder>(MovieDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_list_main_page,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieItemViewHolder(val binding: ItemListMainPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemVH: MovieItemList? = null
        fun bind(item: MovieItemList) {
            itemVH = item
            if (item.poster != null) {
                context.glideLoad(binding.ivItemListMainPage,item.poster)
            }
            binding.tvNameItemListMainPage.text = item.title
        }

        init {
            binding.root.setOnClickListener {
                listener.onMovieItemClicked(itemVH!!.imdbID)
            }
        }
    }

    interface ItemClickListener {
        fun onMovieItemClicked(imdbId: String)
    }

}

class MovieDiffUtilCallback : DiffUtil.ItemCallback<MovieItemList>() {
    override fun areItemsTheSame(oldItem: MovieItemList, newItem: MovieItemList): Boolean =
        oldItem.imdbID == newItem.imdbID

    override fun areContentsTheSame(oldItem: MovieItemList, newItem: MovieItemList): Boolean =
        oldItem == newItem

}