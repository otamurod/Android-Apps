package com.otamurod.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.retrofit.adapters.MovieAdapter
import com.otamurod.retrofit.databinding.ActivityMainBinding
import com.otamurod.retrofit.models.Movie
import com.otamurod.retrofit.retrofit.Common
import com.otamurod.retrofit.retrofit.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var retrofitService: RetrofitService

    private val TAG = "MainActivity"

    lateinit var movieAdapter: MovieAdapter
    lateinit var list: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        movieAdapter = MovieAdapter(list)

        retrofitService = Common.retrofitService(this)
        /*retrofitService.getMovie().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful && response.body() != null) {
//                    val list = response.body()
//                    list?.forEach {
//                        Log.d(TAG, "onResponse: $it")
//                    }

                    list.addAll(response.body()!!)
                    movieAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {

            }

        })*/

        retrofitService.getMovie().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                list.addAll(result)
                movieAdapter.notifyDataSetChanged()
            },
                { error ->
                })

        binding.rv.adapter = movieAdapter
    }
}