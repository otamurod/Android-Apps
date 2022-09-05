package com.otamurod.customnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.otamurod.customnotification.Constants.getDefaultAlbumArt


class NotificationService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        if (intent.action == Constants.ACTION.STARTFOREGROUND_ACTION) {
            showNotification()
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
        } else if (intent.action == Constants.ACTION.PREV_ACTION) {
            Toast.makeText(this, "Clicked Previous", Toast.LENGTH_SHORT).show()
            Log.i(LOG_TAG, "Clicked Previous")
        } else if (intent.action == Constants.ACTION.PLAY_ACTION) {
            Toast.makeText(this, "Clicked Play", Toast.LENGTH_SHORT).show()
            Log.i(LOG_TAG, "Clicked Play")
        } else if (intent.action == Constants.ACTION.NEXT_ACTION) {
            Toast.makeText(this, "Clicked Next", Toast.LENGTH_SHORT).show()
            Log.i(LOG_TAG, "Clicked Next")
        } else if (intent.action ==
            Constants.ACTION.STOPFOREGROUND_ACTION
        ) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent")
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show()
            stopForeground(true)
            stopSelf()
        }
        return START_STICKY
    }

    var status: Notification? = null
    private val LOG_TAG = "NotificationService"

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        // Using RemoteViews to bind custom layouts into Notification
        val views = RemoteViews(
            packageName,
            R.layout.status_bar
        )
        val bigViews = RemoteViews(
            packageName,
            R.layout.status_bar_expanded
        )

        // showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE)
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE)
        bigViews.setImageViewBitmap(
            R.id.status_bar_album_art,
            getDefaultAlbumArt(this)
        )
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.action = Constants.ACTION.MAIN_ACTION
        notificationIntent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, 0
        )
        val previousIntent = Intent(this, NotificationService::class.java)
        previousIntent.action = Constants.ACTION.PREV_ACTION
        val ppreviousIntent = PendingIntent.getService(
            this, 0,
            previousIntent, 0
        )
        val playIntent = Intent(this, NotificationService::class.java)
        playIntent.action = Constants.ACTION.PLAY_ACTION
        val pplayIntent = PendingIntent.getService(
            this, 0,
            playIntent, 0
        )
        val nextIntent = Intent(this, NotificationService::class.java)
        nextIntent.action = Constants.ACTION.NEXT_ACTION
        val pnextIntent = PendingIntent.getService(
            this, 0,
            nextIntent, 0
        )
        val closeIntent = Intent(this, NotificationService::class.java)
        closeIntent.action = Constants.ACTION.STOPFOREGROUND_ACTION
        val pcloseIntent = PendingIntent.getService(
            this, 0,
            closeIntent, 0
        )
        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent)
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent)
        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent)
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent)
        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent)
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent)
        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent)
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent)
        views.setImageViewResource(
            R.id.status_bar_play,
            R.drawable.apollo_holo_dark_pause
        )
        bigViews.setImageViewResource(
            R.id.status_bar_play,
            R.drawable.apollo_holo_dark_pause
        )
        views.setTextViewText(R.id.status_bar_track_name, "Song Title")
        bigViews.setTextViewText(R.id.status_bar_track_name, "Song Title")

        views.setTextViewText(R.id.status_bar_artist_name, "Artist Name")
        bigViews.setTextViewText(R.id.status_bar_artist_name, "Artist Name")

        bigViews.setTextViewText(R.id.status_bar_album_name, "Album Name")
        status = Notification.Builder(this).setChannelId("channel_01").build()

        status!!.contentView = views
        status!!.bigContentView = bigViews
        status!!.flags = Notification.FLAG_ONGOING_EVENT
        status!!.icon = R.drawable.ic_music
        status!!.contentIntent = pendingIntent

        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status)

    }

}