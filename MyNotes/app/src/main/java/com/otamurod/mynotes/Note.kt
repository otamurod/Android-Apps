package com.otamurod.mynotes

class Note {

    var noteID: Int? = null
    var noteTitle: String? = null
    var noteContent: String? =null

    //constructor
    constructor(noteID: Int, noteTitle: String, noteContent: String){
        this.noteID = noteID
        this.noteTitle = noteTitle
        this.noteContent = noteContent
    }

}