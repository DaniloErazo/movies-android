package com.globant.imdb.model.entity

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("original_title")
    val movieName: String,
    @SerializedName("release_date")
    val movieDate: String,
    @SerializedName("poster_path")
    val movieImage: String
)

data class MovieResponse(
    val results: ArrayList<MovieDTO>
)
