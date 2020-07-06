package com.shahin.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.shahin.movieapp.R;
import com.shahin.movieapp.databinding.ActivityMainBinding;
import com.shahin.movieapp.model.MoviesList;
import com.shahin.movieapp.model.SearchItem;
import com.shahin.movieapp.view.Adapter.MoviesAdapter;
import com.shahin.movieapp.viewModel.MainViewModel;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter mMoviesListAdapter;
    private ActivityMainBinding mMainBinding;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mMainViewModel.getMoviesList().observe(this, moviesList -> {
            setupAdapter(moviesList.getSearch());
            setupSlider(moviesList.getSearch());
            mMainViewModel.addListToDB(moviesList.getSearch());
        });

        mMainBinding.recyclerViewMainActivity.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void setupAdapter(List<SearchItem> moviesList) {
        mMoviesListAdapter = new MoviesAdapter(this, moviesList);
        mMainBinding.recyclerViewMainActivity.setAdapter(mMoviesListAdapter);
    }

    private void setupSlider(List<SearchItem> list) {

        HashMap<String, String> url_maps = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            url_maps.put(list.get(i).getTitle(), list.get(i).getPoster());
        }

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mMainBinding.sliderPicApp.addSlider(textSliderView);
        }
        mMainBinding.sliderPicApp.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mMainBinding.sliderPicApp.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mMainBinding.sliderPicApp.setDuration(5000);
    }


}