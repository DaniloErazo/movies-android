package com.globant.imdb.components.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.globant.imdb.databinding.MovieMainCardBinding

class MovieCardVH(root: View): ViewHolder(root){

    private val binding = MovieMainCardBinding.bind(root)

    val movieTitle = binding.movieName
    val movieYear = binding.movieYear
    val movieImage = binding.movieImage

    //bind in VH
}