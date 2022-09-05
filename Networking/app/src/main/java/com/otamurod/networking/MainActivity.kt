package com.otamurod.networking

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.otamurod.networking.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var networkHelper: NetworkHelper
    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkHelper = NetworkHelper(this)

        if (networkHelper.isNetworkConnected()) {
            binding.tv.text = "Connected"

            requestQueue = Volley.newRequestQueue(this)
            fetchImage()
            fetchObject()
        } else {
            binding.tv.text = "Disconnected"
        }
    }

    private fun fetchObject() {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            "http://ip.jsontest.com/",
            null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {
                    val string = response?.getString("ip")
                    binding.tv.text = string
                }

            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    binding.tv.text = error?.message
                }

            })

        requestQueue.add(jsonObjectRequest)
    }

    private fun fetchImage() {
        val imageRequest = ImageRequest(
            "https://i.imgur.com/Nwk25LA.jpg",
            object : Response.Listener<Bitmap> {
                override fun onResponse(response: Bitmap?) {
                    binding.imageView.setImageBitmap(response)
                }
            },
            0,
            0,
            ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    binding.tv.text = error?.message
                }

            })
        requestQueue.add(imageRequest)
    }
}