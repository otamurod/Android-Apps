package com.otamurod.playmusic

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.otamurod.playmusic.databinding.ActivityPlayBinding
import com.otamurod.playmusic.models.Music
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.util.*


class PlayActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var playBinding: ActivityPlayBinding
    lateinit var musicList: ArrayList<Music>
    lateinit var handler: Handler
    private var mediaPlayer: MediaPlayer? = null
    private var musicPosition: Int = 0
    private var endTime: String? = null
    private var currentTime: String? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playBinding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(playBinding.root)

        handler = Handler(mainLooper)
        musicPosition = intent.getIntExtra("position", 0)
        musicList = getAllAudio()
        setMusicInfo(musicPosition)

        playBinding.repeat.setOnClickListener {
            playBinding.repeat.visibility = View.GONE
            playBinding.shuffle.visibility = View.VISIBLE
            playBinding.previousBtn.isClickable = true
            playBinding.nextBtn.isClickable = true
        }
        playBinding.shuffle.setOnClickListener {
            playBinding.shuffle.visibility = View.GONE
            playBinding.loop.visibility = View.VISIBLE
        }
        playBinding.loop.setOnClickListener {
            playBinding.loop.visibility = View.GONE
            playBinding.repeat.visibility = View.VISIBLE
            playBinding.previousBtn.isClickable = false
            playBinding.nextBtn.isClickable = false
        }

        playBinding.playBtn.setOnClickListener {

            if (mediaPlayer == null) {
                playMusic(musicPosition)
            } else if (mediaPlayer?.isPlaying!!) {
                mediaPlayer?.pause()
                playBinding.playBtn.setImageResource(R.drawable.play)
            } else if (!mediaPlayer?.isPlaying!!) {
                mediaPlayer?.start()
                playBinding.playBtn.setImageResource(R.drawable.pause)
            }

        }

        playBinding.previousBtn.setOnClickListener {
            if (musicPosition > 0) {
                mediaPlayer?.pause()
                mediaPlayer?.release()
                releaseMP()
                playBinding.playBtn.setImageResource(R.drawable.play)
                musicPosition -= 1
                onCompletion()
            }
        }

        playBinding.nextBtn.setOnClickListener {
            if (musicPosition < musicList.size - 1) {
                mediaPlayer?.pause()
                mediaPlayer?.release()
                releaseMP()
                playBinding.playBtn.setImageResource(R.drawable.play)
                musicPosition += 1
                onCompletion()
            }
        }

        playBinding.backwardBtn.setOnClickListener {
            if (mediaPlayer != null && mediaPlayer?.currentPosition != 0) {
                mediaPlayer?.seekTo(mediaPlayer?.currentPosition?.minus(3000)!!)
                returnCurrentTime(mediaPlayer?.currentPosition)
                playBinding.musicStartTime.text = currentTime
            }
        }

        playBinding.forwardBtn.setOnClickListener {
            if (mediaPlayer?.currentPosition != mediaPlayer?.duration) {
                mediaPlayer?.seekTo(mediaPlayer?.currentPosition?.plus(3000)!!)
                returnCurrentTime(mediaPlayer?.currentPosition)
                playBinding.musicStartTime.text = currentTime
            }
        }

        /** working*/
        playBinding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (mediaPlayer != null) {
                        mediaPlayer?.seekTo(playBinding.seekbar.progress)
                        returnCurrentTime(mediaPlayer?.currentPosition)
                    } else {
                        playBinding.seekbar.progress = 0
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

    private fun returnCurrentTime(currentPosition: Int?) {
        val min = (currentPosition!! / 60_000)
        val sec = (currentPosition % 60_000) / 1000
        when {
            min < 10 -> {
                if (sec < 10) {
                    currentTime = "0$min:0$sec"
                } else {
                    currentTime = "0$min:$sec"
                }
            }
            min >= 10 -> {
                if (sec < 10) {
                    currentTime = "$min:0$sec"
                } else {
                    currentTime = "$min:$sec"
                }
            }
        }
    }

    private fun returnEndTime(musicPosition: Int) {
        val minEnd = (musicList[musicPosition].duration!! / 60_000).toInt()
        val secEnd = (musicList[musicPosition].duration!! % 60_000).toInt() / 1000
        when {
            minEnd < 10 -> {
                if (secEnd < 10) {
                    endTime = "0$minEnd:0$secEnd"
                } else {
                    endTime = "0$minEnd:$secEnd"
                }
            }
            minEnd >= 10 -> {
                if (secEnd < 10) {
                    endTime = "$minEnd:0$secEnd"
                } else {
                    endTime = "$minEnd:$secEnd"
                }
            }
        }
    }

    private fun setMusicInfo(musicPosition: Int) {

        playBinding.musicTitle.text = musicList[musicPosition].title
        playBinding.position.text = "${musicPosition + 1} / ${musicList.size}"

        Glide.with(this)
            .asBitmap()
            .load(musicList[musicPosition].artUri)
            .placeholder(R.drawable.music_bg)
            .into(playBinding.container)

        Glide.with(this)
            .asBitmap()
            .load(musicList[musicPosition].artUri)
            .placeholder(R.drawable.music_bg) //set default image if no art is exist
            .into(playBinding.musicThumb)

        playBinding.musicArtist.text = musicList[musicPosition].artist

        currentTime = "00:00"
        returnEndTime(musicPosition)

        playBinding.musicStartTime.text = currentTime
        playBinding.musicEndTime.text = endTime

    }

    private fun playMusic(musicPosition: Int) {

        releaseMP()
        val file = File(musicList[musicPosition].path!!)
        val inputStream = FileInputStream(file)
        val fileDescriptor: FileDescriptor = inputStream.fd
        playBinding.playBtn.setImageResource(R.drawable.pause)

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource(fileDescriptor)
            mediaPlayer?.setOnPreparedListener(this)
            mediaPlayer?.prepareAsync()

            handler.postDelayed(runnable, 100)
        }
    }

    private var runnable = object : Runnable {
        override fun run() {
            if (mediaPlayer != null) {
                returnCurrentTime(mediaPlayer?.currentPosition!!)
            }
            val currentPosition = if (mediaPlayer != null) mediaPlayer?.currentPosition!! else 0
            if (mediaPlayer != null && !mediaPlayer?.isPlaying!!) {
                playBinding.playBtn.setImageResource(R.drawable.play) //set play icon if music finished
            } else if (mediaPlayer != null && mediaPlayer?.isPlaying!!) {
                playBinding.playBtn.setImageResource(R.drawable.pause)
            }
            playBinding.seekbar.progress = currentPosition
            /** working */
            playBinding.musicStartTime.text = currentTime

            if (mediaPlayer != null) {
                playBinding.seekbar.max = mediaPlayer?.duration!!
            }

            handler.postDelayed(this, 100)
        }
    }

    private fun releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer?.release()
                mediaPlayer = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        handler.postDelayed(runnable, 100)
    }

    @SuppressLint("Range")
    private fun getAllAudio(): ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + " !=0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID
        )
        val cursor = this.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            MediaStore.Audio.Media.DATE_ADDED + " ASC",
            null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val title =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val album =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val pathC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val duration =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val albumIdC =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                            .toString()
                    val uri = Uri.parse("content://media/external/audio/albumart")
                    val artUri = Uri.withAppendedPath(uri, albumIdC).toString()

                    val music = Music(id, title, album, artist, duration, pathC, artUri)

                    val file = File(music.path!!)
                    if (file.exists()) {
                        tempList.add(music)
                    }
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return tempList
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }

    private fun onCompletion() {

        if (playBinding.repeat.visibility == View.VISIBLE) {
            // repeat is on play same song again
            setMusicInfo(musicPosition)
        } else if (playBinding.shuffle.visibility == View.VISIBLE) {
            // shuffle is on - play a random song
            val rand = Random()
            musicPosition = rand.nextInt(musicList.size - 1)
            setMusicInfo(musicPosition)
        } else if (playBinding.loop.visibility == View.VISIBLE) {
            // no repeat or shuffle ON - play next song
            if (musicPosition < musicList.size - 1) {
                setMusicInfo(musicPosition)
            } else {
                // play first song
                musicPosition = 0
                setMusicInfo(musicPosition)
            }
        }
    }

}