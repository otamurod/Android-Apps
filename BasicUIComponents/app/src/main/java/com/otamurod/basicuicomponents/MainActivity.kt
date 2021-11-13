package com.otamurod.basicuicomponents

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.otamurod.basicuicomponents.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*handler.postDelayed({
            progress_circular.visibility = View.GONE
        }, 5000)*/

        /**
        * Load Image From Internet Using Picasso
        * Image URL:  "https://wallpaperaccess.com/full/2416779.jpg"
        */
        Picasso.get().load("https://wallpaperaccess.com/full/2416779.jpg").placeholder(R.drawable.motorbike).into(binding.imagePicasso)

        /**
         * Load Image From Internet Using Glide
         * Image URL:  "https://wallpaperaccess.com/full/41819.jpg"
         */

        Glide.with(this).load("https://wallpaperaccess.com/full/41819.jpg").placeholder(R.drawable.picasso).into(binding.imageGlide)

        /**
         * Load Image From Internet Using Fresco
         * Image URL:  "https://wallpaperaccess.com/full/1808486.jpg"
         */

        val uri = Uri.parse("https://wallpaperaccess.com/full/1808486.jpg")

        binding.imageFresco.setImageURI(uri)

        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                MyThread().start()
                Log.d("Seek Bar", "on progress")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.d("Seek Bar", "on start")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.d("Seek Bar", "on stop")
            }


        })

        MyThread().start()

        ratingbar2.rating = 4.5f

        Picasso.get().load("https://image.pngaaa.com/287/5650287-middle.png").into(binding.imageButton)


        button.setOnClickListener {
            val toast = Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }

    }

    inner class MyThread(): Thread(){
        override fun run() {
            sleep(1500)
            seekbar.progress += 4
        }
    }

}