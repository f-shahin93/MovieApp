package com.shahin.movieapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.shahin.movieapp.R
import com.shahin.movieapp.ui.movieList.MainActivity
import com.shahin.movieapp.viewModel.MainViewModel

class SplashActivity : AppCompatActivity() {
    private var mMainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel!!.getMoviesList()
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }

}