package com.otamurod.pdpuz.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.pdpuz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportActionBar?.hide()

        activityMainBinding.course.setOnClickListener {

            val intent = Intent(this, CoursesActivity::class.java)
            startActivity(intent)
        }

        activityMainBinding.group.setOnClickListener {

            val intent = Intent(this, GroupsActivity::class.java)
            startActivity(intent)

        }

        activityMainBinding.mentor.setOnClickListener {
            val intent = Intent(this, MentorsActivity::class.java)
            startActivity(intent)

        }

        activityMainBinding.logo.setOnClickListener {
            activityMainBinding.logo.animate()
                .scaleX(1.5f).scaleY(1.5f) //scale to 1 and a quarter(half x,half y)
                .translationY((activityMainBinding.logo.height / 2).toFloat())
                .translationX(0.0f) // move to bottom
                .alpha(0.8f) // make it less visible
                .rotation(360f) // one round turns
                .setDuration(2000) // all take 2 seconds
                .withEndAction(Runnable {
                    //animation ended
                    activityMainBinding.logo.animate()
                        .scaleX(1.0f).scaleY(1.0f) //scale to original
                        .translationY(0.0f)
                        .translationX(0.0f) // move to original place
                        .alpha(1.0f) // make it visible
                        .rotation(-360f) // one round turns
                        .setDuration(2000) // all take 2 seconds
                        .withEndAction(Runnable {
                            //animation ended
                        })
                })
        }
    }
}