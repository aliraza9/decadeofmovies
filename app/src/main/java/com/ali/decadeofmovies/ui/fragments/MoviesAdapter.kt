package com.ali.decadeofmovies.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ali.decadeofmovies.R
import com.ali.decadeofmovies.databinding.ItemMovieBinding
import com.ali.decadeofmovies.models.Movie
import com.ali.decadeofmovies.utils.extensions.hide
import com.ali.decadeofmovies.utils.extensions.show


class MoviesAdapter(
    private var movies: MutableList<Movie>,
    private val movieClickCallback: (Movie, Int) -> Unit,
    private val updatedSearchQuery: () -> String,
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies[position].let { movie ->
            holder.bind(movie, position)
            holder.binding.cardView.setOnClickListener {
                movieClickCallback.invoke(movie, position)
            }
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)
        fun bind(movie: Movie, position: Int) {
            binding.apply {
                movieTitleTV.text = movie.title
                movieYearTV.text = "${movie.year}"
                movieRating.rating = movie.rating.toFloat()
                movieRating.stepSize = 1.0f
                movieRating.numStars = 5
                heading.text = "${movie.year}"
                if (updatedSearchQuery.invoke().isEmpty()) {
                    heading.hide()
                } else {
                    when {
                        position == 0 -> heading.show()
                        movie.year != movies[position - 1].year -> heading.show()
                        else -> heading.hide()
                    }
                }
            }
        }
    }

    fun update(list: MutableList<Movie>) {
        //will be used to optimize recyclerview
        val diffResult = DiffUtil.calculateDiff(
            PostsDiffUtilCallback(
                movies,
                list
            )
        )
        movies = list
        diffResult.dispatchUpdatesTo(this)
    }

    fun clearData() {
        movies.clear()
        notifyDataSetChanged()
    }
}
