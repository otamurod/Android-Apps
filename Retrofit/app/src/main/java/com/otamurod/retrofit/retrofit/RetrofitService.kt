package com.otamurod.retrofit.retrofit

import com.otamurod.retrofit.models.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {

//    @GET("marvel")
//    fun getMovie():Call<List<Movie>>

    @GET("marvel") //with RxJava
    fun getMovie(): Single<List<Movie>>

}