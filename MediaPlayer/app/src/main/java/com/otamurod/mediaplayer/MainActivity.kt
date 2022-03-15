package com.otamurod.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.mediaplayer.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val httpURL = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3"
        handler = Handler(mainLooper)


        binding.stream.setOnClickListener {
            binding.playBtn.visibility = View.VISIBLE
            binding.playBtn2.visibility = View.GONE
            if(mediaPlayer != null){
                releaseMP()
            }
            binding.stream.isClickable = false
            binding.library.isClickable = true
        }

        binding.playBtn.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setDataSource(httpURL)
                mediaPlayer?.setOnPreparedListener(this)
                mediaPlayer?.prepareAsync()

                binding.seekbar.max = mediaPlayer?.duration!!
                handler.postDelayed(runnable, 100)

                binding.pauseBtn.visibility = View.VISIBLE
                binding.stopBtn.visibility = View.VISIBLE
                binding.playBtn.isClickable = false
            }
        }

        binding.library.setOnClickListener {
            binding.playBtn.visibility = View.GONE
            binding.playBtn2.visibility = View.VISIBLE
            if(mediaPlayer != null){
                releaseMP()
            }
            binding.stream.isClickable = true
            binding.library.isClickable = false
        }

        binding.playBtn2.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this,R.raw.an_epic_story)
                mediaPlayer?.start()

                binding.seekbar.max = mediaPlayer?.duration!!
                handler.postDelayed(runnable, 100)

                binding.pauseBtn.visibility = View.VISIBLE
                binding.stopBtn.visibility = View.VISIBLE
                binding.playBtn2.isClickable = false
            }
        }

        binding.pauseBtn.setOnClickListener {
            if (mediaPlayer?.isPlaying!!) {
                mediaPlayer?.pause()
            }
            binding.resumeBtn.visibility = View.VISIBLE
            binding.pauseBtn.visibility = View.GONE
        }

        binding.resumeBtn.setOnClickListener {
            if (!mediaPlayer?.isPlaying!!) {
                mediaPlayer?.start()
            }
            binding.resumeBtn.visibility = View.GONE
            binding.pauseBtn.visibility = View.VISIBLE
        }

        binding.stopBtn.setOnClickListener {
            mediaPlayer?.stop()
            releaseMP()
            binding.playBtn.isClickable = true
            binding.playBtn2.isClickable = true
        }

        binding.backwardBtn.setOnClickListener {
            mediaPlayer?.seekTo(mediaPlayer?.currentPosition?.minus(3000)!!)
        }

        binding.forwardBtn.setOnClickListener {
            mediaPlayer?.seekTo(mediaPlayer?.currentPosition?.plus(3000)!!)
        }

        binding.seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer?.seekTo(binding.seekbar.progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private var runnable = object :Runnable {
        override fun run() {
            val currentPosition = if (mediaPlayer != null ) mediaPlayer?.currentPosition!! else 0
            binding.seekbar.progress = currentPosition
            handler.postDelayed(this, 100)
        }

    }

    private fun releaseMP(){
        if(mediaPlayer != null){
            try {
                mediaPlayer?.release()
                mediaPlayer = null
                binding.pauseBtn.visibility = View.GONE
                binding.stopBtn.visibility = View.GONE
                binding.resumeBtn.visibility = View.GONE
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        handler.postDelayed(runnable, 100)
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }
}