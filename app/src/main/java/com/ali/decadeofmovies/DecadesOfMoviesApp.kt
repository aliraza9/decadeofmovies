package com.ali.decadeofmovies

import android.app.Application
import com.ali.decadeofmovies.di.repositoryModule
import com.ali.decadeofmovies.di.viewModelModule
import com.ali.decadeofmovies.repositories.local.prefs.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DecadesOfMoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesManager.init(this)
        startKoin {
            // use modules
            androidContext(this@DecadesOfMoviesApp)
            modules(viewModelModule, repositoryModule)
        }
    }
}