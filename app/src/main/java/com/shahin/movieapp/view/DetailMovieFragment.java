package com.shahin.movieapp.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.sergivonavi.materialbanner.Banner;
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
    private Banner mBanner;
    private boolean flagOffLine = false;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    public static DetailMovieFragment newInstance(String imdbId) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE, imdbId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImdbId = getArguments().getString(ARG_MOVIE);
        }

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (mMainViewModel.isOnline(getContext())) {
            sendRequest();
        } else {
            flagOffLine = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false);

        if (flagOffLine) {
            Movie movieDB = mMainViewModel.getMovieFromDB(mImdbId);
            if (movieDB != null) {
                if (movieDB.getRuntime() != null && !movieDB.getRuntime().equals("")) {
                    initUI(movieDB);
                } else {
                    initPic(movieDB);
                    initBanner();
                }
            }
        }

        return mMovieBinding.getRoot();
    }

    public void initBanner() {
        mBanner = new Banner.Builder(getContext())
                .setParent(mMovieBinding.llBannerDetail)
                .setIcon(R.drawable.ic_alart)
                .setMessage("You have lost connection to the Internet. This app is offline.")
                .setLeftButton("Dismiss", banner -> {
                    mBanner.dismiss();

                })
                .setRightButton("Turn on wifi", banner -> {
                    if (mMainViewModel.isOnline(getContext())) {
                        mBanner.setVisibility(View.GONE);
                        mBanner.dismiss();
                        sendRequest();
                        visibleCompo(true);
                    } else {
                        mBanner.show();
                        mBanner.setVisibility(View.VISIBLE);
                    }
                })
                .create();

        mBanner.show();
        mBanner.setVisibility(View.VISIBLE);
    }

    private void sendRequest() {
        mMainViewModel.getMovie(mImdbId).observe(this, movie -> {
            initUI(movie);
            mMainViewModel.addMovieToDB(movie);
        });
    }

    private void initUI(Movie movie) {
        mMovieBinding.tvTitleDetailMovie.setText(movie.getTitle());
        mMovieBinding.tvTimeDetailMovie.setText(movie.getRuntime());
        mMovieBinding.tvYearDetailMovie.setText(movie.getYear());
        mMovieBinding.tvImdbRateDetailMovie.setText(movie.getImdbRating());
        mMovieBinding.tvMetascoreDetailMovie.setText("% " + movie.getMetascore());
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

    private void initPic(Movie movie) {
        Picasso.with(getContext()).load(movie.getPoster()).fit().into(mMovieBinding.ivPicDetailMovie);
        Picasso.with(getContext()).load(movie.getPoster()).into(mMovieBinding.ivSmallPicDetailMovie);
        visibleCompo(false);
    }

    private void visibleCompo(boolean isVisible){
        if(isVisible){
            mMovieBinding.linearLayoutCompat4.setVisibility(View.VISIBLE);
            mMovieBinding.linearLayoutCompat2.setVisibility(View.VISIBLE);
            mMovieBinding.linearLayoutCompat.setVisibility(View.VISIBLE);
            mMovieBinding.ivShowFilmDetailMovie.setVisibility(View.VISIBLE);
        }else {
            mMovieBinding.linearLayoutCompat4.setVisibility(View.INVISIBLE);
            mMovieBinding.linearLayoutCompat2.setVisibility(View.INVISIBLE);
            mMovieBinding.linearLayoutCompat.setVisibility(View.INVISIBLE);
            mMovieBinding.ivShowFilmDetailMovie.setVisibility(View.INVISIBLE);
        }
    }

}