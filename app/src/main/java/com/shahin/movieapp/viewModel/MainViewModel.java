package com.shahin.movieapp.viewModel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.shahin.movieapp.model.Movie;
import com.shahin.movieapp.model.MoviesList;
import com.shahin.movieapp.model.SearchItem;
import com.shahin.movieapp.network.ItemFetcher;
import com.shahin.movieapp.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Movie mMovie;
    private ItemFetcher mItemFetcher;
    private MovieRepository mMovieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mItemFetcher = ItemFetcher.getInstance();
        mMovieRepository = MovieRepository.getInstance(application);
    }

    public LiveData<MoviesList> getMoviesList() {
        mItemFetcher.getMoviesList();
        return mItemFetcher.getMutableLDMoviesList();
    }

    public LiveData<Movie> getMovie(String imdbId) {
        mItemFetcher.getMovie(imdbId);
        return mItemFetcher.getMutableLDMovie();
    }

    public void addListToDB(List<SearchItem> list) {
        mMovieRepository.addMovieListToDB(list);
    }

    public void addMovieToDB(Movie movie) {
        mMovieRepository.addMovieToDB(movie);
    }

    public List<SearchItem> getMoviesListFromDB() {
        List<Movie> listMovie = mMovieRepository.getListFromDB();
        if (listMovie.size() > 0) {
            List<SearchItem> searchItemList = new ArrayList<>();
            for (int i = 0; i < listMovie.size(); i++) {
                SearchItem searchItem = new SearchItem(
                        listMovie.get(i).getImdbID(), listMovie.get(i).getTitle(), listMovie.get(i).getYear(),
                        listMovie.get(i).getPoster(), listMovie.get(i).getType());

                searchItemList.add(searchItem);
            }
            return searchItemList;
        }
        return new ArrayList<>();
    }

    public Movie getMovieFromDB(String imdbId) {
        return mMovieRepository.getMovieFromDB(imdbId);
    }

    public boolean isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


}
