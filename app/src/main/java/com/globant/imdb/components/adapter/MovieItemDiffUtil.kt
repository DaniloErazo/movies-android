package com.globant.imdb.components.adapter

import androidx.recyclerview.widget.DiffUtil
import com.globant.imdb.model.entity.MovieDTO

class MovieItemDiffUtil : DiffUtil.ItemCallback<MovieDTO>() {
    override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
        return when {
            oldItem.identifier != newItem.identifier -> {false }
            oldItem.movieName != newItem.movieName -> {false}
            oldItem.movieDate != newItem.movieDate -> {false}
            oldItem.movieImage != newItem.movieImage -> {false}
            else -> true
        }
    }
}