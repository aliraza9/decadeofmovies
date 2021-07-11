package com.ali.decadeofmovies.repositories.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ali.decadeofmovies.models.Movie


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM ${Movie.TABLE_NAME}")
    fun getAllMovies(): MutableList<Movie>
}