package com.otamurod.reqres

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.reqres.databinding.ActivityMainBinding
import com.otamurod.reqres.models.create.ReqUser
import com.otamurod.reqres.models.create.ResUser
import com.otamurod.reqres.models.single_user.SingleUserResponse
import com.otamurod.reqres.models.update.ResUpdateUser
import com.otamurod.retrofit.retrofit.Common
import com.otamurod.retrofit.retrofit.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var retrofitService: RetrofitService
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitService = Common.retrofitService

        /*retrofitService.getUsers().enqueue(object : Callback<UserResponce> {
            override fun onResponse(call: Call<UserResponce>, response: Response<UserResponce>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse1: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<UserResponce>, t: Throwable) {

            }

        })*/

        /*retrofitService.getSingleUser(2).enqueue(object : Callback<SingleUserResponse> {
            override fun onResponse(
                call: Call<SingleUserResponse>,
                response: Response<SingleUserResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse2: ${response.body()?.data}")
                } else {
                    Log.d(TAG, "onResponse2: User Not Found")
                }
            }

            override fun onFailure(call: Call<SingleUserResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })*/

        /*val reqUser = ReqUser("developer", "Otamurod")

        retrofitService.createUser(reqUser).enqueue(object : Callback<ResUser> {
            override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse3: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ResUser>, t: Throwable) {

            }
        })*/

        val reqUser = ReqUser("developer", "Otamurod")
        /*retrofitService.updateUser(2, reqUser).enqueue(object :Callback<ResUpdateUser>{
            override fun onResponse(call: Call<ResUpdateUser>, response: Response<ResUpdateUser>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ResUpdateUser>, t: Throwable) {

            }

        })*/

        retrofitService.deleteUser(1).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })

    }
}