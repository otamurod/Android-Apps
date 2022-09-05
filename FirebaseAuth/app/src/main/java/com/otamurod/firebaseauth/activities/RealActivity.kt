package com.otamurod.firebaseauth.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.otamurod.firebaseauth.R
import com.otamurod.firebaseauth.adapters.MessageAdapter
import com.otamurod.firebaseauth.adapters.UserAdapter
import com.otamurod.firebaseauth.databinding.ActivityRealBinding
import com.otamurod.firebaseauth.models.Message
import com.otamurod.firebaseauth.models.User
import java.io.IOException
import java.net.URL

class RealActivity : AppCompatActivity() {

    lateinit var binding: ActivityRealBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    private val TAG = "RealActivity"
    private lateinit var user: User
    lateinit var userAdapter: UserAdapter
    private var userList = ArrayList<User>()
    private var lastMessage: String? = null


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customizeActionBar()

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        val email = currentUser!!.email
        val displayName = currentUser.displayName
        val phoneNumber = currentUser.phoneNumber
        val photoUrl = currentUser.photoUrl
        val uid = currentUser.uid
        val status = "online"

        user = User(
            email.toString(), displayName.toString(),
            phoneNumber.toString(), photoUrl.toString(), uid, status
        )

        reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.uid}")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val children = snapshot.children

                    for (child in children) {
                        val message = child.getValue(Message::class.java)
                        lastMessage = if (message?.text != null) {
                            message.text
                        }else{
                            null
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        reference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                val filterList = arrayListOf<User>()
                val children = snapshot.children

                for (child in children) {
                    val user = child.getValue(User::class.java)
                    if (user != null) {
                        if (uid != user.uid) {
                            userList.add(user)
                        } else {
                            filterList.add(user)
                        }
                    }
                }

                if (filterList.isEmpty()) {
                    reference.child(uid).setValue(user)
                }

                userAdapter =
                    UserAdapter(userList, object : UserAdapter.OnItemClickListener {
                        override fun onItemClick(user: User) {

                            val intent = Intent(this@RealActivity, MessageActivity::class.java)
                            intent.putExtra("user", user)
                            startActivity(intent)
                        }

                    })
                binding.userRv.adapter = userAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun customizeActionBar() {

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Users"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}