package com.shahin.movieapp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentDetailMovieBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.model.Movie
import javax.inject.Inject

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding

    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private val viewModel by viewModels<DetailMovieViewModel> { viewModelFactory }

    private var flagOffLine = false
    private val navArgument : DetailMovieFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // if (viewModel.isOnline(requireContext())) {
            sendRequest()
        //} else {
          //  flagOffLine = true
        //}

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)

        if (flagOffLine) {
            val movieDB: Movie? = viewModel.getMovieFromDB(navArgument.imdbId)
            if (movieDB != null) {
                if (movieDB.runtime != null && movieDB.runtime != "") {
                    initUI(movieDB)
                } else {
                    initPic(movieDB)
                }
            }
        }

        return binding.root
    }

    private fun sendRequest() {
        if (navArgument.imdbId != null){
            viewModel.getMovie(navArgument.imdbId).observe(viewLifecycleOwner) { movie ->
                initUI(movie!!)
                viewModel.addMovieToDB(movie)
            }
        }
    }

    private fun initUI(movie: Movie) {
        binding.tvTitleDetailMovie.text = movie.title
        binding.tvTimeDetailMovie.text = movie.runtime
        binding.tvYearDetailMovie.text = movie.year
        binding.tvImdbRateDetailMovie.text = movie.imdbRating
        binding.tvMetascoreDetailMovie.text = "% " + movie.metaScore
        binding.tvGenreDetailMovie.text = movie.genre
        binding.tvCountryDetailMovie.text = movie.country
        binding.tvLanguageDetailMovie.text = movie.language
        binding.tvDirectorDetailMovie.text = movie.director
        binding.tvWriterDetailMovie.text = movie.writer
        binding.tvActorsDetailMovie.text = movie.actors
        binding.tvAwardDetailMovie.text = movie.awards
        binding.tvPlotDetailMovie.text = movie.plot

        // Picasso.with(getContext()).load(movie.getPoster()).fit().into(binding.ivPicDetailMovie);
        //Picasso.with(getContext()).load(movie.getPoster()).into(binding.ivSmallPicDetailMovie);
    }

    private fun initPic(movie: Movie) {
        // Picasso.with(getContext()).load(movie.getPoster()).fit().into(binding.ivPicDetailMovie);
        // Picasso.with(getContext()).load(movie.getPoster()).into(binding.ivSmallPicDetailMovie);
        visibleCompo(false)
    }

    private fun visibleCompo(isVisible: Boolean) {
        if (isVisible) {
            binding.linearLayoutCompat4.visibility = View.VISIBLE
            binding.linearLayoutCompat2.visibility = View.VISIBLE
            binding.linearLayoutCompat.visibility = View.VISIBLE
            binding.ivShowFilmDetailMovie.visibility = View.VISIBLE
        } else {
            binding.linearLayoutCompat4.visibility = View.INVISIBLE
            binding.linearLayoutCompat2.visibility = View.INVISIBLE
            binding.linearLayoutCompat.visibility = View.INVISIBLE
            binding.ivShowFilmDetailMovie.visibility = View.INVISIBLE
        }
    }

}