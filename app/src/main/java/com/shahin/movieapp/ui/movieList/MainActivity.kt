package com.shahin.movieapp.ui.movieList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.sergivonavi.materialbanner.Banner
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.ActivityMainBinding
import com.shahin.movieapp.model.MovieItemList
import com.shahin.movieapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieListAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (viewModel.isOnline(this)) {
            viewModel.getLDMoviesList().observe(this) { moviesList ->
                setupAdapter(moviesList)
                setupSlider(moviesList)
                viewModel.addListToDB(moviesList)
            }
        } else {
            val list: List<MovieItemList> = viewModel.getMoviesListFromDB()
            if (list.isNotEmpty()) {
                setupAdapter(list)
                setupSlider(list)
            } else {
                initBanner()
            }
        }

        binding.recyclerViewMainActivity.layoutManager = GridLayoutManager(this, 2)

    }


    private fun setupAdapter(moviesList: List<MovieItemList>) {
        adapter =
            MovieListAdapter(moviesList,this)
        binding.recyclerViewMainActivity.adapter = adapter
    }

    private fun setupSlider(list: List<MovieItemList>) {

        for (item in list.subList(0, 5)) {
            val textSliderView = TextSliderView(this)
            // initialize a SliderLayout
            textSliderView
                .description(item.title)
                .image(item.poster)
                .scaleType = BaseSliderView.ScaleType.Fit

            //add your extra information
            textSliderView.bundle(Bundle())
            textSliderView.bundle.putString("extra", item.title)

            binding.sliderPicApp.addSlider(textSliderView)
        }

        binding.sliderPicApp.setPresetTransformer(SliderLayout.Transformer.Accordion)
        binding.sliderPicApp.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        binding.sliderPicApp.setDuration(5000)
    }

    private var banner: Banner? = null
    private fun initBanner() {
        banner = Banner.Builder(this)
            .setParent(binding.llBannerMain)
            .setIcon(R.drawable.ic_alart)
            .setMessage("You have lost connection to the Internet. This app is offline.")
            .setLeftButton("Dismiss") { banner ->
                banner.dismiss()
                finish()
            }
            .setRightButton("Turn on wifi") { _ ->
                if (viewModel.isOnline(this)) {
                    banner?.visibility = View.GONE
                    banner?.dismiss()
                    viewModel.getMoviesList()
                    viewModel.getLDMoviesList().observe(this) { moviesList ->
                        setupAdapter(moviesList)
                        setupSlider(moviesList)
                        viewModel.addListToDB(moviesList)
                    }

                } else {
                    banner?.show()
                    banner?.visibility = View.VISIBLE
                }
            }
            .create()

        banner?.show()
        banner?.visibility = View.VISIBLE
    }


}