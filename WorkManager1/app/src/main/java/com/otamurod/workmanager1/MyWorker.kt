package com.otamurod.workmanager1

import android.content.Context
import android.provider.SyncStateContract
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val userId = inputData.getString(SyncStateContract.Constants._ID)
        val userDetail = getDetail(userId)
        // Create the output of the work
        val firstName = workDataOf(SyncStateContract.Constants.DATA to userDetail.firstName)
        // Return the output
        return Result.success(firstName)

    }
}