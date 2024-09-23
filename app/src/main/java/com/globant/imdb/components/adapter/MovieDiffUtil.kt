package com.globant.imdb.components.adapter

import androidx.recyclerview.widget.DiffUtil
import com.globant.imdb.model.entity.MovieDTO

class MovieDiffUtil(
    private val oldList: List<MovieDTO>,
    private val newList: List<MovieDTO>
):DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].identifier == newList[newItemPosition].identifier
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return when {
            old.identifier != new.identifier -> {false }
            old.movieName != new.movieName -> {false}
            old.movieDate != new.movieDate -> {false}
            old.movieImage != new.movieImage -> {false}
            else -> true
        }
    }
}