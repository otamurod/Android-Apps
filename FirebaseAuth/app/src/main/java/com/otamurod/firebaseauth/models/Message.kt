package com.otamurod.firebaseauth.models

class Message {
    var text: String? = null
    var date: String? = null
    var fromUid: String? = null
    var toUid: String? = null

    constructor()

    constructor(text: String?, date: String?, fromUid: String?, toUid: String?) {
        this.text = text
        this.date = date
        this.fromUid = fromUid
        this.toUid = toUid
    }
}