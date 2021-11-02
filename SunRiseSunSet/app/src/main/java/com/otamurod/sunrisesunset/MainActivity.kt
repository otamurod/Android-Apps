package com.otamurod.sunrisesunset

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun getSunSet(view: View){
        var city=etCityName.text.toString()
        val url="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+ city +"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
        MyAsyncTask().execute(url)
    }

    inner class MyAsyncTask: AsyncTask<String, String, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String? {
//            TODO https call
            try {

                val url = URL(p0[0])

                val urlConnect = url.openConnection() as HttpsURLConnection
                urlConnect.connectTimeout = 7000
                //convert to String
                val inString = convertStreamToString(urlConnect.inputStream)
                //cannot access to UI

                publishProgress(inString)

            }catch (ex: Exception){}

            return "  "
        }

        override fun onProgressUpdate(vararg values: String?) {
//            super.onProgressUpdate(*values)
            try {
                var json = JSONObject(values[0])
                val query = json.getJSONObject("query")
                val results = query.getJSONObject("results")
                val channel = results.getJSONObject("channel")
                val astronomy = channel.getJSONObject("astronomy")
                val sunrise = astronomy.getString("sunrise")

                tvSunRiseTime.text = "Sunrise time is $sunrise"
            }catch (ex: java.lang.Exception){}

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }

    private fun convertStreamToString(inputStream: InputStream?): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var allString:String = ""

        try {
            line = bufferReader.readLine()
            while (line != null){
                allString += line
            }
            inputStream!!.close()
        }catch (ex: Exception){

        }

        return allString
    }


}