package com.otamurod.firebaseauth.activities

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.otamurod.firebaseauth.R
import com.otamurod.firebaseauth.adapters.MessageAdapter
import com.otamurod.firebaseauth.databinding.ActivityMessageBinding
import com.otamurod.firebaseauth.models.Message
import com.otamurod.firebaseauth.models.User
import java.io.IOException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MessageActivity : AppCompatActivity() {
    lateinit var messageBinding: ActivityMessageBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var messageAdapter: MessageAdapter

    private lateinit var sourceBitmap: Bitmap
    private lateinit var user: User

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageBinding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(messageBinding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        user = intent.getSerializableExtra("user") as User

        //change policy to use stream in Main Thread
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        customizeActionBar()

        messageBinding.sendBtn.setOnClickListener {
            val text = messageBinding.messageEt.text.toString()
            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val date = simpleDateFormat.format(Date())

            val message = Message(text, date, firebaseAuth.currentUser?.uid, user.uid)
            val key = reference.push().key

            reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.uid!!}/$key")
                .setValue(message)

            reference.child("${user.uid}/messages/${firebaseAuth.currentUser?.uid}/$key")
                .setValue(message)

            messageBinding.messageEt.setText("")

        }

        reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.uid}")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Message>()
                    val children = snapshot.children

                    for (child in children) {
                        val value = child.getValue(Message::class.java)
                        if (value != null) {
                            list.add(value)
                        }
                    }

                    messageAdapter = MessageAdapter(list, firebaseAuth.currentUser!!.uid)
                    messageBinding.rvMessage.adapter = messageAdapter

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun customizeActionBar() {
        try {
            val url = URL(user.photoUrl)
            sourceBitmap = BitmapFactory.decodeStream(url.openStream())
            sourceBitmap = sourceBitmap.copy(Bitmap.Config.ARGB_8888, true)
        } catch (e: IOException) {
            println(e)
        }

        sourceBitmap = Bitmap.createScaledBitmap(sourceBitmap, 155, 155, true)
        val drawable: Drawable = BitmapDrawable(resources, createCircleBitmap(sourceBitmap))

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(drawable)
        supportActionBar?.title = user.displayName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun createCircleBitmap(bitmapImg: Bitmap?): Bitmap {

        val output = Bitmap.createBitmap(
            bitmapImg!!.width,
            bitmapImg.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)

        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(
            0, 0, bitmapImg.width,
            bitmapImg.height
        )

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(
            (bitmapImg.width / 2).toFloat(),
            (bitmapImg.height / 2).toFloat(), (bitmapImg.width / 2).toFloat(), paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmapImg, rect, rect, paint)
        return output
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}