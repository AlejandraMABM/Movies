package com.example.mainactivity.provider

import com.example.mainactivity.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        fun getRetrofit() : MovieService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")  // colocamos la ruta de acceso a la api
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieService::class.java)
        }
    }

}