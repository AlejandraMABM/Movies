package com.example.mainactivity

import com.example.mainactivity.data.MovieName
import com.example.mainactivity.data.MovieNameList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(".")
    suspend fun findAllMovies(
        @Query("s") query: String,
        @Query("apikey") apikey: String = "fb7aca4"
    ): MovieNameList


    //http://www.omdbapi.com/?t=cars


    @GET(".")
    suspend fun findMovieById(
        @Query("i") imdbID: String,
        @Query("apikey") apikey: String = "fb7aca4"
    ): MovieName

}
