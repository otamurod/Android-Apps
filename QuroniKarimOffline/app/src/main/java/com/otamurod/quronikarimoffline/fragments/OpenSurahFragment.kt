/**
 * BISMILLAHIR ROHMANIR ROHIYM
 *
 * PROGRAM NAME: QUR'ONI KARIM
 * AUTHOR: SAFAROV OTAMUROD YUSUFALI O'G'LI
 * FINISHED ON: 08.08.2022, at 08:00 a.m
 *
 * DESCRIPTION: Tinglovchilar Muborak Qur'oni Karim suralari qiroatlarini to'liq eshitib bahramand bo'lish, shuningdek, o'zbek tilidagi tarjimasini o'qishlari mumkin. Alloh barchamizni o'zi iymonimizni mustahkam qilsin! Ushbu ilova orqali musulmonlarga foydam tegishidan umidvorman. Barcha insonlarga Allohning roziligini qo'lga kiritish nasib qilsin!
 *
 */

package com.otamurod.quronikarimoffline.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.otamurod.quronikarimoffline.R
import com.otamurod.quronikarimoffline.databinding.FragmentOpenSurahBinding
import com.otamurod.quronikarimoffline.models.Surahs


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class OpenSurahFragment : Fragment() {
    private var position: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)

        if (arguments != null) {
            position = arguments?.getInt("position") as Int
        }

    }

    private lateinit var getActivity: AppCompatActivity
    lateinit var openSurahBinding: FragmentOpenSurahBinding

    lateinit var handler: Handler
    private var mediaPlayer: MediaPlayer? = null
    private var endTime: String? = null
    private var currentTime: String? = null
    private var audioDuration: Int = 0

    private val surahs = Surahs()
    private val listOfSurahs = surahs.getListOfSurahs()


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        openSurahBinding = FragmentOpenSurahBinding.inflate(layoutInflater, container, false)

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.show() //show appbar/toolbar
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)//set visible
        getActivity.supportActionBar!!.setHomeButtonEnabled(true)
        getActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back) //set navigation icon

        setInfo(position)

        openSurahBinding.nextBtn.setOnClickListener {
            if (position!! < 113) {
                releaseMP()
                position = position!! + 1
                setInfo(position)
            }
        }
        openSurahBinding.previousBtn.setOnClickListener {
            if (position!! > 0) {
                releaseMP()
                position = position!! - 1
                setInfo(position)
            }
        }

        handler = Handler(Looper.getMainLooper())

        openSurahBinding.playBtn.setOnClickListener {

            if (mediaPlayer == null) {
                playAudioFromRawFolder(requireActivity(), "surah_${position!! + 1}")
                openSurahBinding.playBtn.setImageResource(R.drawable.ic_pause)
            } else if (mediaPlayer?.isPlaying!!) {
                openSurahBinding.playBtn.setImageResource(R.drawable.ic_play_arrow)
                mediaPlayer?.pause()
            } else if (!mediaPlayer?.isPlaying!!) {
                openSurahBinding.playBtn.setImageResource(R.drawable.ic_pause)
                mediaPlayer?.start()
            }

        }

        openSurahBinding.readBtn.setOnClickListener {
            val bundle = bundleOf("position" to position)
            findNavController().navigate(R.id.readSurahFragment, bundle)
        }

        openSurahBinding.seekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (mediaPlayer != null) {
                        mediaPlayer?.seekTo(openSurahBinding.seekbar.progress)
                        returnCurrentTime(mediaPlayer?.currentPosition)
                    } else {
                        openSurahBinding.seekbar.progress = 0
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        return openSurahBinding.root
    }

    /**
     *  Play mp3 from raw folder offline
     */
    private fun playAudioFromRawFolder(activity: Activity, soundFileName: String?) {

        val resID = resources.getIdentifier(soundFileName, "raw", activity.packageName)
        mediaPlayer = MediaPlayer.create(activity, resID)
        returnEndTime()
        openSurahBinding.totalTime.text = endTime
        mediaPlayer!!.start()

        handler.postDelayed(runnable, 100)

    }

    private fun resetPlayer() {
        openSurahBinding.playBtn.setImageResource(R.drawable.ic_play_arrow)
        currentTime = "00:00:00"
        endTime = "/00:00:00"
        openSurahBinding.currentTime.text = currentTime
        openSurahBinding.totalTime.text = endTime

        openSurahBinding.seekbar.progress = 0

    }

    private fun setInfo(position: Int?) {
        openSurahBinding.surah.text = listOfSurahs[position!!]
        getActivity.supportActionBar!!.title = listOfSurahs[position]

        val url =
            "@drawable/number_${position + 1}" // where number (without the extension) is the file
        val imageResource: Int = resources.getIdentifier(url, null, requireContext().packageName)

        Glide.with(requireContext()).load(imageResource)
            .into(openSurahBinding.surahNumber)

        resetPlayer()

    }

    private fun returnCurrentTime(currentPosition: Int?) {
        audioDuration = currentPosition!! / 1000

        val hour = (audioDuration / 3600)
        val min = (audioDuration % 3600) / 60
        val sec = (audioDuration % 60)

        when {
            hour <= 0 -> {
                if (min < 10) {
                    currentTime = if (sec < 10) {
                        "00:0$min:0$sec"
                    } else {
                        "00:0$min:$sec"
                    }
                }
                if (min >= 10) {
                    currentTime = if (sec < 10) {
                        "00:$min:0$sec"
                    } else {
                        "00:$min:$sec"
                    }
                }
            }

            hour in 1..9 -> {

                if (min < 10) {
                    currentTime = if (sec < 10) {
                        "0$hour:0$min:0$sec"
                    } else {
                        "0$hour:0$min:$sec"
                    }
                }
                if (min >= 10) {
                    currentTime = if (sec < 10) {
                        "0$hour:$min:0$sec"
                    } else {
                        "0$hour:$min:$sec"
                    }
                }
            }

            hour in 10..25 -> {

                if (min < 10) {
                    currentTime = if (sec < 10) {
                        "$hour:0$min:0$sec"
                    } else {
                        "$hour:0$min:$sec"
                    }
                }
                if (min >= 10) {
                    currentTime = if (sec < 10) {
                        "$hour:$min:0$sec"
                    } else {
                        "$hour:$min:$sec"
                    }
                }
            }

        }
    }

    private fun returnEndTime() {
        audioDuration = mediaPlayer!!.duration / 1000

        val hourEnd = (audioDuration / 3600)
        val minEnd = (audioDuration % 3600) / 60
        val secEnd = (audioDuration % 60)

        when {
            hourEnd <= 0 -> {
                if (minEnd < 10) {
                    endTime = if (secEnd < 10) {
                        "/00:0$minEnd:0$secEnd"
                    } else {
                        "/00:0$minEnd:$secEnd"
                    }
                }
                if (minEnd >= 10) {
                    endTime = if (secEnd < 10) {
                        "/00:$minEnd:0$secEnd"
                    } else {
                        "/00:$minEnd:$secEnd"
                    }
                }
            }

            hourEnd in 1..9 -> {

                if (minEnd < 10) {
                    endTime = if (secEnd < 10) {
                        "/0$hourEnd:0$minEnd:0$secEnd"
                    } else {
                        "/0$hourEnd:0$minEnd:$secEnd"
                    }
                }
                if (minEnd >= 10) {
                    endTime = if (secEnd < 10) {
                        "/0$hourEnd:$minEnd:0$secEnd"
                    } else {
                        "/0$hourEnd:$minEnd:$secEnd"
                    }
                }
            }

            hourEnd in 10..25 -> {

                if (minEnd < 10) {
                    endTime = if (secEnd < 10) {
                        "/$hourEnd:0$minEnd:0$secEnd"
                    } else {
                        "/$hourEnd:0$minEnd:$secEnd"
                    }
                }
                if (minEnd >= 10) {
                    endTime = if (secEnd < 10) {
                        "/$hourEnd:$minEnd:0$secEnd"
                    } else {
                        "/$hourEnd:$minEnd:$secEnd"
                    }
                }
            }
        }
    }

    private var runnable = object : Runnable {
        override fun run() {
            if (mediaPlayer != null) {
                returnCurrentTime(mediaPlayer?.currentPosition!!)
            }
            val currentPosition = if (mediaPlayer != null) mediaPlayer?.currentPosition!! else 0

            if (mediaPlayer != null && !mediaPlayer?.isPlaying!!) {
                openSurahBinding.playBtn.setImageResource(R.drawable.ic_play_arrow) //set play icon if music finished
            } else if (mediaPlayer != null && mediaPlayer?.isPlaying!!) {
                openSurahBinding.playBtn.setImageResource(R.drawable.ic_pause)
            }
            /** working */
            if (mediaPlayer != null) {
                openSurahBinding.currentTime.text = currentTime
                openSurahBinding.seekbar.max = mediaPlayer?.duration!!
                openSurahBinding.seekbar.progress = currentPosition
            }
            /**  Play Next Song If player finished*/
            if (mediaPlayer != null && currentTime == endTime) {
                openSurahBinding.playBtn.setImageResource(R.drawable.ic_play_arrow) //set play icon if music finished
                mediaPlayer!!.stop()
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

    override fun onResume() {
        super.onResume()
        setInfo(position)

        if (mediaPlayer != null) {
            returnEndTime()
            openSurahBinding.totalTime.text = endTime
            mediaPlayer!!.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }

}