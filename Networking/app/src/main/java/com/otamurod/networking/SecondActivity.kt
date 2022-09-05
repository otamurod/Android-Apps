package com.otamurod.networking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.otamurod.networking.adapters.UserAdapter
import com.otamurod.networking.databinding.ActivitySecondBinding
import com.otamurod.networking.models.User
import org.json.JSONArray

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var requestQueue: RequestQueue
    private val url = "https://api.github.com/users"
    lateinit var userAdapter: UserAdapter

    private val TAG = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null, object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {

                    val type = object : TypeToken<List<User>>() {}.type
                    val list: List<User> = Gson().fromJson(response.toString(), type)
                    userAdapter = UserAdapter(this@SecondActivity, list)
                    binding.rv.adapter = userAdapter

                    for (user in list) {
                        Log.d(TAG, "onResponse: ${response.toString()}")
                    }
                }

            },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {

                    }

                })

        requestQueue.add(jsonArrayRequest)

    }
}