package com.otamurod.pdpuz.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.pdpuz.R
import com.otamurod.pdpuz.databinding.ActivityMentorsBinding
import com.otamurod.pdpuz.fragments.mentor_fragments.CoursesInMentorFragment

class MentorsActivity : AppCompatActivity() {
    lateinit var mentorsBinding: ActivityMentorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mentorsBinding = ActivityMentorsBinding.inflate(layoutInflater)
        setContentView(mentorsBinding.root)
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