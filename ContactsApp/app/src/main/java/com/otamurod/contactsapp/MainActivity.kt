package com.otamurod.contactsapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.contactsapp.adapters.ContactAdapter
import com.otamurod.contactsapp.databinding.ActivityMainBinding
import com.otamurod.contactsapp.databinding.MyDialogBinding
import com.otamurod.contactsapp.db.MyDbHelper
import com.otamurod.contactsapp.models.Contact

class MainActivity : AppCompatActivity() {

    lateinit var contactAdapter: ContactAdapter
    lateinit var myDbHelper: MyDbHelper
    lateinit var list: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        list = myDbHelper.getAllContacts()

        contactAdapter = ContactAdapter(list, object : ContactAdapter.OnItemClickListener {

            override fun onItemClick(contact: Contact, position: Int, imageView: ImageView) {
                val popupMenu = PopupMenu(this@MainActivity, imageView)
                popupMenu.inflate(R.menu.popup_menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {

                        val itemId = item?.itemId

                        when (itemId) {

                            R.id.edit -> {
                                val dialog = AlertDialog.Builder(this@MainActivity)
                                val myDialogBinding =
                                    MyDialogBinding.inflate(layoutInflater, null, false)

                                myDialogBinding.addName.setText(contact.name)
                                myDialogBinding.addNumber.setText(contact.phoneNumber)

                                dialog.setPositiveButton(
                                    "Update",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            contact.name = myDialogBinding.addName.text.toString()
                                            contact.phoneNumber =
                                                myDialogBinding.addNumber.text.toString()

                                            //update all
                                            myDbHelper.updateConctact(contact)
                                            list[position] = contact
                                            contactAdapter.notifyItemChanged(position)

                                        }

                                    })

                                dialog.setView(myDialogBinding.root)
                                dialog.show()
                            }
                            R.id.delete -> {
                                myDbHelper.deleteConctact(contact)
                                list.remove(contact)
                                contactAdapter.notifyItemRemoved(list.size)
                                contactAdapter.notifyItemRangeChanged(position, list.size)

                            }
                        }

                        return true
                    }

                })
                popupMenu.show()

            }

            override fun onItemContactClick(contact: Contact) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("id", contact.id)
                startActivity(intent)
            }

        })

        binding.rv.adapter = contactAdapter


        /*var contact = Contact(2, "Otamurod Safarov", "+998996895306")
        myDbHelper.updateConctact(contact)

        contact = Contact(1, "Otamurod Safarov", "+998946580516")
        myDbHelper.deleteConctact(contact)

        val allContacts = myDbHelper.getAllContacts()

        val string = StringBuilder()

        allContacts.forEach { contact ->
            string.append("${contact.id} -> ${contact.name} -> ${contact.phoneNumber}\n")
        }

        tv.text = string*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        when (itemId) {
            R.id.add -> {
                val dialog = AlertDialog.Builder(this)
                val myDialogBinding = MyDialogBinding.inflate(layoutInflater, null, false)
                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Save", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val name = myDialogBinding.addName.text.toString()
                        val phone = myDialogBinding.addNumber.text.toString()

                        val contact = Contact(name, phone)
                        myDbHelper.addConctact(contact)

                        list.add(contact)

                        contactAdapter.notifyItemInserted(list.size)
                    }
                })
                dialog.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}