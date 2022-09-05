package com.otamurod.customnotification

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


object Constants {
    fun getDefaultAlbumArt(context: Context): Bitmap? {
        var bm: Bitmap? = null
        val options = BitmapFactory.Options()
        try {
            bm = BitmapFactory.decodeResource(
                context.resources,
                R.drawable.default_album_art, options
            )
        } catch (ee: Error) {
        } catch (e: Exception) {
        }
        return bm
    }

    interface ACTION {
        companion object {
            const val MAIN_ACTION = "com.otamurod.customnotification.action.main"
            const val INIT_ACTION = "com.otamurod.customnotification.action.init"
            const val PREV_ACTION = "com.otamurod.customnotification.action.prev"
            const val PLAY_ACTION = "com.otamurod.customnotification.action.play"
            const val NEXT_ACTION = "com.otamurod.customnotification.action.next"
            const val STARTFOREGROUND_ACTION =
                "com.otamurod.customnotification.action.startforeground"
            const val STOPFOREGROUND_ACTION =
                "com.otamurod.customnotification.action.stopforeground"
        }
    }

    interface NOTIFICATION_ID {
        companion object {
            const val FOREGROUND_SERVICE = 101
        }
    }
}