package com.shahin.movieapp.ui.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.ItemListMainPageBinding
import com.shahin.movieapp.model.MovieItemList

class MovieListAdapter(
    var list: List<MovieItemList>,
    val context: Context,
    val listener:ItemClickListener
) : RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder>() {

    fun setMovieList(list: List<MovieItemList>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemMainBinding: ItemListMainPageBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.item_list_main_page, parent, false)
        return MovieItemViewHolder(itemMainBinding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieItemViewHolder(val binding: ItemListMainPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemVH: MovieItemList? = null
        fun bind(item: MovieItemList) {
            itemVH = item
            if (item.poster != null) {
                /*Picasso.with(context)
                        .load(searchItem.getPoster())
                        .into(mBinding.ivItelistMainPage);*/
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