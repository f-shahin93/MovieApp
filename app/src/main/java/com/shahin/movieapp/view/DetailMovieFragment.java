package com.shahin.movieapp.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shahin.movieapp.R;
import com.shahin.movieapp.databinding.FragmentDetailMovieBinding;
import com.shahin.movieapp.model.Movie;
import com.shahin.movieapp.viewModel.MainViewModel;
import com.squareup.picasso.Picasso;

public class DetailMovieFragment extends Fragment {

    public static final String ARG_MOVIE = "ArgMovie";
    private FragmentDetailMovieBinding mMovieBinding;
    private MainViewModel mMainViewModel;
    private String mImdbId;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    public static DetailMovieFragment newInstance(String imdbId) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE,imdbId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImdbId = getArguments().getString(ARG_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false);
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mMainViewModel.getMovie(mImdbId).observe(this, movie -> initUI(movie));
        return mMovieBinding.getRoot();
    }

    private void initUI(Movie movie) {
        mMovieBinding.tvTitleDetailMovie.setText(movie.getTitle());
        mMovieBinding.tvTimeDetailMovie.setText(movie.getRuntime());
        mMovieBinding.tvYearDetailMovie.setText(movie.getYear());
        mMovieBinding.tvImdbRateDetailMovie.setText(movie.getImdbRating());
        mMovieBinding.tvMetascoreDetailMovie.setText("% "+movie.getMetascore());
        mMovieBinding.tvGenreDetailMovie.setText(movie.getGenre());
        mMovieBinding.tvCountryDetailMovie.setText(movie.getCountry());
        mMovieBinding.tvLanguageDetailMovie.setText(movie.getLanguage());
        mMovieBinding.tvDirectorDetailMovie.setText(movie.getDirector());
        mMovieBinding.tvWriterDetailMovie.setText(movie.getWriter());
        mMovieBinding.tvActorsDetailMovie.setText(movie.getActors());
        mMovieBinding.tvAwardDetailMovie.setText(movie.getAwards());
        mMovieBinding.tvPlotDetailMovie.setText(movie.getPlot());

        Picasso.with(getContext()).load(movie.getPoster()).fit().into(mMovieBinding.ivPicDetailMovie);
        Picasso.with(getContext()).load(movie.getPoster()).into(mMovieBinding.ivSmallPicDetailMovie);

    }

}