package com.otamurod.handleronline

import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.handleronline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    var gameOn = false
    var isBack = false
    var startTime: Long = 0L
    var closeTime: Long = 0L
    private val TAG = "MainActivity"

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 1000)

        MyAsynTask().execute()


//        handler2()
//        handler1()
    }

    inner class MyAsynTask : AsyncTask<Void, Void, Void>() {
        override fun onPreExecute() {
            super.onPreExecute()

            binding.tv.text = "Beginning.."

        }

        override fun doInBackground(vararg params: Void?): Void? {
            Thread.sleep(5000)
            binding.tv.text = "Running..."
            Thread.sleep(3000)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            binding.tv.text = "Ending..."

        }
    }


    private var runnable = object : Runnable {
        override fun run() {


//            runnable1()
//            runnable2()
        }

    }

    private fun handler2() {
        handler = Handler(Looper.getMainLooper())
        if (startTime == 0L) {
            startTime = SystemClock.uptimeMillis()
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 100)
        }
        binding.button.setOnClickListener {
            binding.counterTv.text = (++counter).toString()
        }
    }

    private fun handler1() {
        startTime = System.currentTimeMillis()

        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (gameOn) {
                    val seconds = (System.currentTimeMillis() - startTime) / 1000
                    Log.d(TAG, "handleMessage: $seconds")
                }

                handler.sendEmptyMessageDelayed(0, 1000)
            }
        }
        gameOn = true
        handler.sendEmptyMessage(0)
    }

    private fun runnable1() {
        val start = startTime
        val millis = SystemClock.uptimeMillis() - start
        var second = millis / 1000
        val minute = second / 60
        second %= 60

        binding.timeTv.text = String.format("%02d:%02d", minute, second)
        handler.postDelayed(runnable, 200)
    }

    private fun runnable2() {
        binding.progressBar.setProgress(binding.progressBar.progress + 2)
        Thread.sleep(1000)
        handler.postDelayed(runnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
        closeTime = SystemClock.uptimeMillis() - startTime
    }

    override fun onResume() {
        super.onResume()
        startTime = SystemClock.uptimeMillis() - closeTime

        handler.postDelayed(runnable, 100)
    }

    override fun onBackPressed() {

        if (isBack) {
            super.onBackPressed()
        }
        isBack = true
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            isBack = false
        }, 2000)
    }
}