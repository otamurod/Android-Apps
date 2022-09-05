package com.otamurod.reqres

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.reqres.databinding.ActivitySecondBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SecondActivity : AppCompatActivity() {

    var okHttpClient = OkHttpClient()
    lateinit var binding: ActivitySecondBinding
    val URL = "http://api.icndb.com/jokes/random"
    private val TAG = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {
                    val string = response.body!!.string()

                    val txt = (JSONObject(string).getJSONObject("value").get("joke")).toString()
                    Log.d(TAG, "onResponse: $txt")
                    binding.text.text = txt

                }
            }

        })

    }
}