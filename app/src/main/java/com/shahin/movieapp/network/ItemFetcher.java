package com.shahin.movieapp.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.shahin.movieapp.model.Movie;
import com.shahin.movieapp.model.MoviesList;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemFetcher {

    private static final String BASE_URL = "https://www.omdbapi.com/";
    private static ItemFetcher mInstance;
    private Map<String, String> mQueries;
    private static Retrofit mRetrofit;
    private ApiService mApiService;
    private MutableLiveData<MoviesList> mMutableLDMoviesList = new MutableLiveData<>();
    private MutableLiveData<Movie> mMutableLDMovie = new MutableLiveData<>();
    private static final String TAG = "Movie";

    private ItemFetcher() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);

        mQueries = new HashMap<String, String>() {{
            put("apikey", "3e974fca");
        }};
    }

    public static ItemFetcher getInstance() {
        if (mInstance == null) {
            mInstance = new ItemFetcher();
        }
        return mInstance;
    }

    public void getMoviesList() {
        Map<String, String> queries = new HashMap<>();
        queries.putAll(mQueries);
        queries.put("s", "batman");

        mApiService.getMoviesList(queries).enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Successful getList ");
                    MoviesList moviesList = response.body();
                    mMutableLDMoviesList.postValue(moviesList);
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.d(TAG, "Failed getList : " + t.getMessage());
            }
        });

    }

    public void getMovie(String imdbID){
        Map<String,String> queries = new HashMap<>();
        queries.putAll(mQueries);
        queries.put("i",imdbID);

        mApiService.getMovie(queries).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "Successful getMovie ");
                    Movie movie = response.body();
                    mMutableLDMovie.postValue(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "Failed getList " + t.getMessage());
            }
        });

    }

    public MutableLiveData<MoviesList> getMutableLDMoviesList() {
        return mMutableLDMoviesList;
    }

    public void setMutableLDMoviesList(MutableLiveData<MoviesList> mutableLDMoviesList) {
        mMutableLDMoviesList = mutableLDMoviesList;
    }

    public MutableLiveData<Movie> getMutableLDMovie() {
        return mMutableLDMovie;
    }

    public void setMutableLDMovie(MutableLiveData<Movie> mutableLDMovie) {
        mMutableLDMovie = mutableLDMovie;
    }
}
