package com.otamurod.mynotes

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_ticket.view.*

class MainActivity : AppCompatActivity() {

    private var listOfNotes = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Add data
        /**
        listOfNotes.add(Note(1, "First Note", "Content of the first note created"))
        listOfNotes.add(Note(2, "Second Note", "Content of the second note"))
        listOfNotes.add(Note(2, "Third Note", "Content of the last note"))
         */

        //Load From Database
        loadQuery("%")

    }

    //reload view after adding or deleting, etc
    override fun onResume() {
        super.onResume()
        loadQuery("%")
    }
    @SuppressLint("Range")
    fun loadQuery(title: String){
        var dbManager = DBManager(this)
        val projection = arrayOf("ID", "Title", "Content")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.query(projection, "Title LIKE ?", selectionArgs, "ID")

        listOfNotes.clear()

        if (cursor.moveToFirst()){
            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Content"))

                listOfNotes.add(Note(ID, Title, Description))

            }while (cursor.moveToNext())
        }

        val myNotesAdapter = MyNotesAdapter(this, listOfNotes)
        lsViewNotes.adapter = myNotesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                //search database
                loadQuery("%$query%")

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.addNote ->{
                //go to add note page
                val intent = Intent(this, AddNote::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //adapter class
    inner class MyNotesAdapter: BaseAdapter{
        var listNotesAdapter: ArrayList<Note>
        var context: Context? = null
        //constructor
        constructor(context: Context, listNotesAdapter: ArrayList<Note>): super(){
            this.context = context
            this.listNotesAdapter = listNotesAdapter
        }


        override fun getCount(): Int {
            return listNotesAdapter.size
        }

        override fun getItem(p0: Int): Any {
            return listNotesAdapter[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.note_ticket, null)
            val myNote = listNotesAdapter[p0]
            myView.tvTitle.text = myNote.noteTitle
            myView.tvContent.text = myNote.noteContent
            //Listener
            myView.imgVDelete.setOnClickListener(View.OnClickListener {
                var dbManager = DBManager(this.context!!)
                val selectionArgs = arrayOf(myNote.noteID.toString())
                //delete note
                dbManager.delete("ID=?", selectionArgs)
                //reload
                loadQuery("%")
            })

            myView.imgVEdit.setOnClickListener(View.OnClickListener {
                goToUpdate(myNote)
            })

            return myView
        }
    }

    private fun goToUpdate(note: Note) {
        //go to add note page
        val intent = Intent(this, AddNote::class.java)
        intent.putExtra("ID", note.noteID)
        intent.putExtra("Title", note.noteTitle)
        intent.putExtra("Content", note.noteContent)

        startActivity(intent)
    }
}