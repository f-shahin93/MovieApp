package com.shahin.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sergivonavi.materialbanner.Banner
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.ActivityMainBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.viewModel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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