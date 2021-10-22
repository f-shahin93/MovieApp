package com.shahin.movieapp.ui.movieList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieListBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.model.MovieItemList
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.utiles.bannerNetwork
import com.shahin.movieapp.viewModel.MainViewModel
import javax.inject.Inject


class MovieListFragment : Fragment(), MovieListAdapter.ItemClickListener {

    private lateinit var adapter: MovieListAdapter
    private lateinit var binding: FragmentMovieListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerAndAdapter()
        setupObservers()
    }

    private fun setupRecyclerAndAdapter() {
        adapter = MovieListAdapter(requireContext(), this)
        binding.recyclerViewMainActivity.let {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObservers() {
        viewModel.getMoviesList().observe(viewLifecycleOwner) { moviesList ->
            adapter.submitList(moviesList)
            setupSlider(moviesList)
        }
        viewModel.error.observe(viewLifecycleOwner){
            if (adapter.currentList.isNullOrEmpty() && viewModel.getMoviesList().value.isNullOrEmpty()){
                requireContext().bannerNetwork(binding.llBannerMain) { viewModel.isOnline(requireContext()) }
            }
        }
    }

    private fun setupSlider(list: List<MovieItemList>) {
        if (list.isNullOrEmpty())
            return

        for (item in list.subList(0, 5)) {
            val textSliderView = TextSliderView(requireContext())
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

    override fun onMovieItemClicked(imdbId: String) {
        findNavController().navigate(MovieListFragmentDirections.listMovieToDetailMovie(imdbId))
    }

}