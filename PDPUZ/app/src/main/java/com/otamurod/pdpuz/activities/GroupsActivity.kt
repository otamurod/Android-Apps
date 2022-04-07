package com.otamurod.pdpuz.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.pdpuz.databinding.ActivityGroupsBinding

class GroupsActivity : AppCompatActivity() {
    lateinit var groupsBinding: ActivityGroupsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        groupsBinding = ActivityGroupsBinding.inflate(layoutInflater)
        setContentView(groupsBinding.root)

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