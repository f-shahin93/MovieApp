package com.shahin.movieapp.ui.movieList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.ItemListMainPageBinding
import com.shahin.movieapp.model.MovieItemList
import com.shahin.movieapp.ui.moviedetail.SecondActivity

class MovieListAdapter(
    var list: List<MovieItemList>,
    val context: Context,
): RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder>() {

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

    inner class MovieItemViewHolder(binding: ItemListMainPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var mMovieItelistVh: MovieItemList? = null
        var mBinding: ItemListMainPageBinding
        fun bind(searchItem: MovieItemList) {
            mMovieItelistVh = searchItem
            if (searchItem.poster != null) {
                /*Picasso.with(context)
                        .load(searchItem.getPoster())
                        .into(mBinding.ivItelistMainPage);*/
            }
            mBinding.tvNameItemListMainPage.text = searchItem.title
        }

        init {
            mBinding = binding
            mBinding.getRoot().setOnClickListener { view ->
                val intent = SecondActivity.newIntent(context, mMovieItelistVh!!.imdbID)
                context.startActivity(intent)
            }
        }
    }
    
}