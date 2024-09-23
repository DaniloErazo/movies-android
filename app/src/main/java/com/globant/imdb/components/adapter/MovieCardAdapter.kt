package com.globant.imdb.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.globant.imdb.R
import com.globant.imdb.components.viewholder.MovieCardVH
import com.globant.imdb.model.entity.MovieDTO


class MovieCardAdapter: ListAdapter<MovieDTO, MovieCardVH>(MovieItemDiffUtil()), Filterable {

    private var movies = listOf<MovieDTO>()
    private val imageBase = "https://image.tmdb.org/t/p"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_main_card, null, false)
        return MovieCardVH(view)
    }


    override fun onBindViewHolder(holder: MovieCardVH, position: Int) {
        val data = getItem(position)
        holder.movieTitle.text = data.movieName
        holder.movieYear.text = data.movieDate

       //Glide.with(holder.movieImage.context).load(imageBase+data.movieImage).into(holder.movieImage)

    }


    fun setData(list: ArrayList<MovieDTO>?){
        this.movies = list!!
        submitList(list)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val charSearch = query.toString()
                val filteredList = if (charSearch.isEmpty()) {
                    movies
                } else {
                    movies.filter { it.movieName.lowercase().contains(charSearch) }
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as List<MovieDTO>)
            }
        }
    }
}