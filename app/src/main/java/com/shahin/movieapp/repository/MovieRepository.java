package com.shahin.movieapp.repository;

import android.content.Context;

import com.shahin.movieapp.model.Movie;
import com.shahin.movieapp.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MovieRepository {
    private static MovieRepository mInstance;
    private Realm mRealm;
    private Context mContext;

    private MovieRepository(Context context) {
        mRealm = Realm.getDefaultInstance();
        this.mContext = context;
    }

    public static MovieRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MovieRepository(context);
        }
        return mInstance;
    }

    public void addMovieListToDB(List<SearchItem> searchItemListParam) {
        if (!mRealm.isInTransaction())
            mRealm.beginTransaction();

        for (SearchItem searchItem : searchItemListParam) {
            if (!isExistMovie(searchItem.getImdbID())) {
                Movie movie = mRealm.createObject(Movie.class, searchItem.getImdbID());
                movie.setPoster(searchItem.getPoster());
                movie.setTitle(searchItem.getTitle());
                movie.setType(searchItem.getType());
                movie.setYear(searchItem.getYear());
            }
        }

        if (mRealm.isInTransaction())
            mRealm.commitTransaction();
    }

    public void addMovieToDB(Movie movieParam) {
        if (!mRealm.isInTransaction())
            mRealm.beginTransaction();

        if (!isExistCompleteMovie(movieParam.getRuntime())) {
            for (Movie movieComplete : mRealm.where(Movie.class).findAll()) {

                if (movieComplete.getImdbID().equals(movieParam.getImdbID())) {
                    movieComplete.setActors(movieParam.getActors());
                    movieComplete.setAwards(movieParam.getAwards());
                    movieComplete.setCountry(movieParam.getCountry());
                    movieComplete.setDirector(movieParam.getDirector());
                    movieComplete.setGenre(movieParam.getGenre());
                    movieComplete.setImdbRating(movieParam.getImdbRating());
                    movieComplete.setLanguage(movieParam.getLanguage());
                    movieComplete.setMetascore(movieParam.getMetascore());
                    movieComplete.setPlot(movieParam.getPlot());
                    movieComplete.setRuntime(movieParam.getRuntime());
                    movieComplete.setWriter(movieParam.getWriter());
                }
            }
        }

        if (mRealm.isInTransaction())
            mRealm.commitTransaction();
    }

    private boolean isExistMovie(String imdbIdParam) {
        if (!mRealm.isInTransaction())
            mRealm.beginTransaction();

        for (Movie movie : mRealm.where(Movie.class).findAll()) {
            if (movie.getImdbID().equals(imdbIdParam)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistCompleteMovie(String runTimeParam) {
        if (!mRealm.isInTransaction())
            mRealm.beginTransaction();

        for (Movie movie : mRealm.where(Movie.class).findAll()) {
            if (movie.getRuntime() != null && movie.getRuntime().equals(runTimeParam)) {
                return true;
            }
        }
        return false;
    }

    public List<Movie> getListFromDB() {
        if (!mRealm.isInTransaction())
            mRealm.beginTransaction();

        List<Movie> movieList = new ArrayList<>();
        for (Movie movie : mRealm.where(Movie.class).findAll()) {
            movieList.add(movie);
        }

        if (mRealm.isInTransaction())
            mRealm.commitTransaction();

        return movieList;
    }

    public Movie getMovieFromDB(String imdbId) {
        if (!mRealm.isInTransaction())
            mRealm.beginTransaction();

        for (Movie movie : mRealm.where(Movie.class).findAll()) {
            if (movie.getImdbID().equals(imdbId)) {
                    return movie;
            }
        }

        if (mRealm.isInTransaction())
            mRealm.commitTransaction();

        return null;
    }


}
