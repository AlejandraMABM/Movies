package com.example.mainactivity.data

import com.google.gson.annotations.SerializedName

data class MovieName(
// movie name es igual q movie response
// estructura de la api, por cada llave

    @SerializedName("imdbID") val imdbID:String,
    @SerializedName("Title") var title:String,
    @SerializedName("Year") var year:String,
    @SerializedName("Poster") val image: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Director") val director: String,

    )
{}


data class MovieNameList(
    @SerializedName("Search") val movies:List<MovieName>
)