package com.otamurod.playmusic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.playmusic.databinding.ActivityVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class VideoActivity : AppCompatActivity() {
    lateinit var videoBinding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoBinding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(videoBinding.root)

        videoBinding.musicPage.setOnClickListener {
            finish()
        }

        val videoURL = "gLuufZ4zO_8"

        videoBinding.videoView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoURL, 0f)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}