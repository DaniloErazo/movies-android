package com.globant.imdb.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.globant.imdb.R
import com.globant.imdb.components.viewholder.MovieCardVH
import com.globant.imdb.model.entity.MovieDTO


class MovieCardAdapter: RecyclerView.Adapter<MovieCardVH>(), Filterable {

    private var movies = ArrayList<MovieDTO>()
    private var filteredList = ArrayList<MovieDTO>()
    private val imageBase = "https://image.tmdb.org/t/p"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_main_card, null, false)
        return MovieCardVH(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MovieCardVH, position: Int) {
        val data = filteredList[position]
        holder.movieTitle.text = data.movieName
        holder.movieYear.text = data.movieDate

       // Glide.with(holder.movieImage.context).load(imageBase+data.movieImage).into(holder.movieImage)

    }


    fun setMovies(movies: ArrayList<MovieDTO>){
        this.filteredList = movies
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val charSearch = query.toString()
                if (charSearch.isEmpty()) {
                    filteredList = movies
                } else {
                    val resultList = ArrayList<MovieDTO>()
                    for (movie in movies) {
                        if (movie.movieName.contains(charSearch, ignoreCase = true)) {
                            resultList.add(movie)
                        }
                    }
                    filteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<MovieDTO>
                notifyDataSetChanged()
            }
        }
    }
}