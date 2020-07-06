package com.shahin.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shahin.movieapp.R;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_IMDBID = "Extra_imdb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.container_frag, DetailMovieFragment.newInstance(getIntent().getStringExtra(EXTRA_IMDBID)))
                .commit();

    }

    public static Intent newIntent(Context context , String midbId) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_IMDBID,midbId);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}