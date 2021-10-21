package com.shahin.movieapp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sergivonavi.materialbanner.Banner
import com.sergivonavi.materialbanner.BannerInterface
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentDetailMovieBinding
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.viewModel.MainViewModel

const val ARG_MOVIE = "ArgMovie"

class DetailMovieFragment : Fragment() {

    
    private lateinit var binding: FragmentDetailMovieBinding
    private lateinit var viewModel: MainViewModel
    private var imdbId: String? = null
    private var flagOffLine = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            imdbId = arguments?.getString(ARG_MOVIE)
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (viewModel.isOnline(requireContext())) {
            sendRequest()
        } else {
            flagOffLine = true
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)

        if (flagOffLine && imdbId != null) {
            val movieDB: Movie? = viewModel.getMovieFromDB(imdbId!!)
            if (movieDB != null) {
                if (movieDB.runtime != null && movieDB.runtime != "") {
                    initUI(movieDB)
                } else {
                    initPic(movieDB)
                    initBanner()
                }
            }
        }

        return binding.root
    }

    private var banner: Banner? = null
    private fun initBanner() {
        banner = Banner.Builder(requireContext())
            .setParent(binding.llBannerDetail)
            .setIcon(R.drawable.ic_alart)
            .setMessage("You have lost connection to the Internet. This app is offline.")
            .setLeftButton("Dismiss") { banner: BannerInterface? -> banner!!.dismiss() }
            .setRightButton("Turn on wifi") {
                if (viewModel.isOnline(requireContext())) {
                    banner?.visibility = View.GONE
                    banner?.dismiss()
                    sendRequest()
                    visibleCompo(true)
                } else {
                    banner?.show()
                    banner?.visibility = View.VISIBLE
                }
            }
            .create()
        banner?.show()
        banner?.visibility = View.VISIBLE
    }

    private fun sendRequest() {
        if (imdbId != null){
            viewModel.getMovie(imdbId!!).observe(viewLifecycleOwner) { movie ->
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


    companion object{
        fun newInstance(imdbId: String): DetailMovieFragment{
            val fragment = DetailMovieFragment()
            val args = Bundle()
            args.putString(ARG_MOVIE, imdbId)
            fragment.arguments = args
            return fragment
        }
    }

}