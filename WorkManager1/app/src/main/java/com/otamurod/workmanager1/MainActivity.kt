package com.otamurod.workmanager1

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val lifecycleOwner = this

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myConstraints = Constraints.Builder()
            .setRequiresDeviceIdle(true) //checks whether device should be idle for the WorkRequest to run
            .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
            .setRequiredNetworkType(NetworkType.CONNECTED) //checks whether device should have Network Connection
            .setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
            .setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
            .build()

        val myWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(myConstraints)
            .setInitialDelay(0, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)

        val myPeriodicWorkRequest =
            PeriodicWorkRequest.Builder(MyWorker::class.java, 1, TimeUnit.HOURS)
                .setConstraints(myConstraints)
                .build()

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id)
            .observe(lifecycleOwner, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    //Toast
                    Toast.makeText(this, "workmanager info", Toast.LENGTH_SHORT).show()
                }
            })

        WorkManager.getInstance(this).beginWith(myWorkRequest)
//            .then(myPeriodicWorkRequest)
            .enqueue()

    }
}