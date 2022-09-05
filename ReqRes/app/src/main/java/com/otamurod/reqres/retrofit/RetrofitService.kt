package com.otamurod.retrofit.retrofit

import com.otamurod.reqres.models.create.ReqUser
import com.otamurod.reqres.models.create.ResUser
import com.otamurod.reqres.models.list_users.UserResponse
import com.otamurod.reqres.models.single_user.SingleUserResponse
import com.otamurod.reqres.models.update.ResUpdateUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("users")
    fun getUsers(): Call<UserResponse>

    @GET("users/{id}")
    fun getSingleUser(@Path("id") id: Int): Call<SingleUserResponse>

    @POST("users")
    fun createUser(@Body reqUser: ReqUser): Call<ResUser>

    @PUT("users/{id}") // @PATCH("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body reqUser: ReqUser): Call<ResUpdateUser>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<ResponseBody>


}