package com.otamurod.retrofit.retrofit

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getRetrofit(baseUrl: String, context: Context): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//        val interceptor = ChuckInterceptor(context)
//
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()


        val build = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

        return build
    }
}