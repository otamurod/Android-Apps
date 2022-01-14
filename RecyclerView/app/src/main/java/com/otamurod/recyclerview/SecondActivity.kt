package com.otamurod.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otamurod.recyclerview.models.Contact
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val contact = intent.getSerializableExtra("contact") as Contact
        val position = intent.getSerializableExtra("position") as Int

        contact_name.text = contact.name
        contact_number.text = contact.number

        /*contact_name.text = intent.getStringExtra("name")
        contact_number.text = intent.getStringExtra("number")*/

        update_contact.setOnClickListener {
            contact_name.text = "Contact ${position + 1} Updated"
            contact_number.text = "New number"
        }

        delete_contact.setOnClickListener {
            contact_name.text = "Contact ${position + 1}"
            contact_number.text = "Deleted"
        }
    }
}