package com.otamurod.pdpuz.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.pdpuz.databinding.ActivityCoursesBinding


class CoursesActivity : AppCompatActivity() {

    lateinit var courseBinding: ActivityCoursesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        courseBinding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(courseBinding.root)

    }

    //set click listener to navigation button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        /** return value matters. If true returned, cannot be overwritten in fragment
         * So return false if you want to override */
        return false
    }
}