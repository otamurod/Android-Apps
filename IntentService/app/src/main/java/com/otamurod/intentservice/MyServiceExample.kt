package com.otamurod.intentservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class MyServiceExample : Service() {

    private val TAG = "ServiceExample"
    private var randomNumber: Int? = null
    private var isGeneratorOn: Boolean? = null
    private val MIN = 0
    private val MAX = 100

    internal class MyServiceBinder : Binder() {
        val service: MyServiceExample
            get() = this//TODO
    }

    private val binder = MyServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        showLogs("In OnBind")
        return binder
    }

    fun showLogs(message: String) {
        Log.d(TAG, message)
    }

    override fun onCreate() {
        showLogs("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showLogs("onStartCommand")

        isGeneratorOn = true
        Thread {
            startRandomNumberGenerator()
        }.start()

        return START_STICKY
    }

    private fun startRandomNumberGenerator() {
        while (isGeneratorOn!!) {
            try {
                Thread.sleep(1000)
                randomNumber = Random().nextInt(MAX) + MIN
                showLogs("Random Number: $randomNumber")
            } catch (e: InterruptedException) {
                showLogs("Thread Interrupted")
            }
        }
    }

    private fun stopRandomNumberGenerator() {
        isGeneratorOn = false
    }

    fun getRandomNumber(): Int? {
        return randomNumber!!
    }

    override fun onUnbind(intent: Intent?): Boolean {
        showLogs("In UnBind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        stopRandomNumberGenerator()
        showLogs("onDestroy")
        super.onDestroy()
    }
}