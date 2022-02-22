package com.otamurod.contactsapp.db

import com.otamurod.contactsapp.models.Contact

interface DatabaseService {

    fun addConctact(contact: Contact)

    fun deleteConctact(contact: Contact)

    fun updateConctact(contact: Contact): Int

    fun getContactById(id:Int):Contact

    fun getAllContacts(): List<Contact>

}