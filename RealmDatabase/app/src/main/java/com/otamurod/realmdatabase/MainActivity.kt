package com.otamurod.realmdatabase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.otamurod.realmdatabase.adapters.ContactAdapater
import com.otamurod.realmdatabase.databinding.ActivityMainBinding
import com.otamurod.realmdatabase.models.Contact
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var realm: Realm
    private val TAG = "MainActivity"
    lateinit var contactAdapater: ContactAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        realm = Realm.getDefaultInstance()

        val realmResults = realm.where(Contact::class.java).findAll()
        contactAdapater =
            ContactAdapater(realmResults, object : ContactAdapater.OnItemClickListener {
                override fun onItemClick(contact: Contact, position: Int) {

                    realm.executeTransaction {
                        val contact = it.where(Contact::class.java)
                            .equalTo(Contact.CONTACT_NUMBER, contact.number).findFirst()
                        contact?.deleteFromRealm() //delete object
                    }
                    contactAdapater.notifyItemRemoved(position)
                    contactAdapater.notifyItemRangeChanged(position, realmResults.size)

                    /*realm.executeTransaction {
                        contact.name = contact.name + " (Updated)"
                        realm.insertOrUpdate(contact)
                        contactAdapater.notifyDataSetChanged()
                    }*/
                }

            })

        binding.saveBtn.setOnClickListener {
            val name = binding.edit1.text.toString()
            val number = binding.edit2.text.toString()

            val contact = Contact()
            contact.name = name
            contact.number = number

            realm.executeTransaction {
                it.insert(contact)
                contactAdapater.notifyItemInserted(realmResults.size)
            }

        }

        binding.rv.adapter = contactAdapater

    }
}