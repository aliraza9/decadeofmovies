package com.ali.decadeofmovies.repositories.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ali.decadeofmovies.models.Movie
import com.ali.decadeofmovies.utils.JsonListConverter

@Database(
    entities = [Movie::class],
    version = Db.VERSION
)
@TypeConverters(JsonListConverter::class)
 abstract class Db: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "decades_of_movies_db"

        @Volatile
        private var _instance: Db? = null

        fun getInstance(context: Context): Db {
            val tempInstance = _instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Db::class.java,
                    DB_NAME
                ).build()

                _instance = instance
                return instance
            }
        }
    }
}