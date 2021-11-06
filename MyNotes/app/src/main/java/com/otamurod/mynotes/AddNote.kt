package com.otamurod.mynotes

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNote : AppCompatActivity() {
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        try {
            var bundle: Bundle? = intent.extras
            id = bundle!!.getInt("ID", 0)
            if (id != 0) {
                tvAddTitle.setText(bundle.getString("Title").toString())
                tvAddNoteContent.setText(bundle.getString("Content").toString())
            }
        }catch (ex: Exception){}

    }

    fun saveNote(view: View){

        var dbManager = DBManager(this)
        var contentValues = ContentValues()
        contentValues.put("Title", tvAddTitle.text.toString())
        contentValues.put("Content", tvAddNoteContent.text.toString())

        when (id) {
            0 -> {
                val ID = dbManager.insert(contentValues)
                if (ID > 0){
                    Toast.makeText(this, "Note Is Added", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this, "Cannot Add Note!", Toast.LENGTH_LONG).show()
                }
            }
            else -> {
                val selectionArgs = arrayOf(id.toString())
                val ID = dbManager.update(contentValues, "ID=?", selectionArgs)
                if (ID > 0){
                    Toast.makeText(this, "Note Is Updated", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this, "Cannot Update Note!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}