package com.otamurod.retrofit.retrofit

import android.content.Context

object Common {

    var BASE_URL = "https://www.simplifiedcoding.net/demos/"
    fun retrofitService(context: Context): RetrofitService {
        return RetrofitClient.getRetrofit(BASE_URL, context).create(RetrofitService::class.java)
    }
}