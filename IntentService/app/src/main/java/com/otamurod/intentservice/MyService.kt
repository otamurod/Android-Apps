package com.otamurod.intentservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "MyService"

    init {
        Log.d(TAG, "Service is running...")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d(TAG, "$dataString")
        }

        Thread {
            for (i in 1..20) {
                Log.d(TAG, "New Thread is running")
            }
        }.start()

        return START_STICKY  //when system service kills the service, it will be created as soon as possible
//        return START_NOT_STICKY  //when system service kills the service, it will not be created as soon as possible
//        return START_REDELIVER_INTENT  //returns last intent
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service is killed")
    }

}