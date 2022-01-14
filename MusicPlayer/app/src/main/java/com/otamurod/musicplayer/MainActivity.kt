package com.otamurod.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            playMusic(button1)
        }
        button2.setOnClickListener {
            playMusic(button2)
        }
        button3.setOnClickListener {
            playMusic(button3)
        }


    }


    fun playMusic(button: View){

        var button = button as Button
        val mediaPlayer = MediaPlayer.create(this, resources.getIdentifier(button.tag as String, "raw", packageName))
        mediaPlayer.start()
    }
}