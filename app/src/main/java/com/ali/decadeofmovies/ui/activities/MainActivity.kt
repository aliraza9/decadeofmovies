package com.ali.decadeofmovies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ali.decadeofmovies.R
import com.ali.decadeofmovies.viewmodels.MoviesViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val moviesViewModel: MoviesViewModel by inject<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}