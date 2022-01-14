package com.otamurod.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.otamurod.recyclerview.adapters.ContactAdapter
import com.otamurod.recyclerview.models.Contact
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity() {

    lateinit var contactList:ArrayList<Contact>
    lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContacts()

        contactAdapter = ContactAdapter(contactList, object :ContactAdapter.OnMyItemClickListener{

            override fun onMyItemClick(contact: Contact, position:Int) {

                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("contact", contact)
                intent.putExtra("position", position)

//                intent.putExtra("name", contact.name)
//                intent.putExtra("number", contact.number)

                startActivity(intent)
            }

            override fun onMyItemLongClick(contact: Contact) {
                Toast.makeText(this@MainActivity, "${contact.name} : ${contact.number}", Toast.LENGTH_SHORT).show()
            }

        })

        /*val linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager*/

        /*val gridLayoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = gridLayoutManager*/

        rv.adapter = contactAdapter

        add_contact_btn.setOnClickListener {

            val contactName = editTextPersonName.text.toString()
            val contactNumber = editTextPhone.text.toString()

            val contact = Contact(contactName, contactNumber)

            contactList.add(contact)
//            contactAdapter.notifyDataSetChanged()
            contactAdapter.notifyItemInserted(contactList.size)

        }

    }

    private fun loadContacts() {
        contactList = ArrayList()

        for (i in 1..100){
            contactList.add(Contact( "Contact $i", "+998 9- --- -- --"))
        }
    }
}