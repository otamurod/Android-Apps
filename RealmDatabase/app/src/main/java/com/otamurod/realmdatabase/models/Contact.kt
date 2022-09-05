package com.otamurod.realmdatabase.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Contact : RealmObject() {

    companion object {
        val CONTACT_NAME = "name"
        val CONTACT_NUMBER = "number"
    }

    @Required
    var name: String? = null

    @PrimaryKey
    @Required
    var number: String? = null
}