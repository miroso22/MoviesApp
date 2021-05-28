package com.example.movies

import com.example.movies.retrofitModel.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    companion object {
        const val API_KEY = "3d86f62ce157e41ebdf62a5169f264b6"
    }

    @GET("trending/movie/week?api_key=$API_KEY")
    fun getMoviesList(@Query("page") page: Int): Call<MoviesList>
}