package com.ali.decadeofmovies.di

import com.ali.decadeofmovies.repositories.IMovieRepository
import com.ali.decadeofmovies.repositories.MovieRepositoryImpl
import com.ali.decadeofmovies.repositories.local.db.Db
import com.ali.decadeofmovies.repositories.local.prefs.PreferencesManager
import com.ali.decadeofmovies.repositories.remote.FlickerApiService
import com.ali.decadeofmovies.utils.rxjava.AppSchedulerProviderImpl
import com.ali.decadeofmovies.utils.rxjava.ISchedulerProvider
import com.ali.decadeofmovies.viewmodels.MovieDetailsViewModel
import com.ali.decadeofmovies.viewmodels.MoviesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//Injectable objects
val viewModelModule = module {

    viewModel { MoviesViewModel(get(), get()) }

    viewModel { MovieDetailsViewModel(get(), get()) }
}

val repositoryModule = module {

    single<ISchedulerProvider> { AppSchedulerProviderImpl() }

    single<IMovieRepository> { MovieRepositoryImpl(get(), get(), get(), get()) }

    single { PreferencesManager() }
    single { Db.getInstance(androidApplication()).getMoviesDao() }

    single<FlickerApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl(FlickerApiService.FLICKER_API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        retrofit.create(FlickerApiService::class.java)

    }

    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    }
}