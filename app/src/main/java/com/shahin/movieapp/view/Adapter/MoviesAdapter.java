package com.shahin.movieapp.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.shahin.movieapp.R;
import com.shahin.movieapp.databinding.ItemListMainPageBinding;
import com.shahin.movieapp.model.SearchItem;
import com.shahin.movieapp.view.DetailMovieFragment;
import com.shahin.movieapp.view.MainActivity;
import com.shahin.movieapp.view.SecondActivity;
import com.shahin.movieapp.viewModel.MainViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder> {

    private Context mContext;
    private List<SearchItem> mList;

    public MoviesAdapter(Context context, List<SearchItem> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<SearchItem> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListMainPageBinding itemMainBinding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext), R.layout.item_list_main_page, parent, false);

        return new MovieItemViewHolder(itemMainBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {

        SearchItem mSearchItemVh;
        ItemListMainPageBinding mBinding;

        public MovieItemViewHolder(ItemListMainPageBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
            mBinding.getRoot().setOnClickListener(view -> {
                Intent intent = SecondActivity.newIntent(mContext,mSearchItemVh.getImdbID() );
                mContext.startActivity(intent);
            });
        }

        public void bind(SearchItem searchItem) {
            mSearchItemVh = searchItem;

            if (searchItem.getPoster() != null) {
                Picasso.with(mContext)
                        .load(searchItem.getPoster())
                        .into(mBinding.ivItemListMainPage);
            }

            mBinding.tvNameItemListMainPage.setText(searchItem.getTitle());
        }
    }


}

