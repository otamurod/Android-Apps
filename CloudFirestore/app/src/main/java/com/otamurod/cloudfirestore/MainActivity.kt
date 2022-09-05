package com.otamurod.cloudfirestore

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.otamurod.cloudfirestore.adapters.UserAdapter
import com.otamurod.cloudfirestore.databinding.ActivityMainBinding
import com.otamurod.cloudfirestore.models.User


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    var userAdapter: UserAdapter? = null
    var userList: ArrayList<User>? = null

    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference

    private val TAG = "MainActivity"

    private var imgURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        //Request Permission
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) { /* ... */
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) { /* ... */
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) { /* ... */
                }
            }).check()

        //Get Firestore Instance
        firebaseFirestore = FirebaseFirestore.getInstance()

        //Get Storage Instance
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("images")

        activityMainBinding.button.setOnClickListener {
            val name = activityMainBinding.editName.text.toString()
            val age = activityMainBinding.editAge.text.toString().toInt()

            val user = User(name, age, imgURL)

            //Write Data
            firebaseFirestore.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, it.id, Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

            activityMainBinding.editName.setText("")
            activityMainBinding.editAge.setText("")
            imgURL = null

        }

        //Retrieve Data
        firebaseFirestore.collection("users")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userList = ArrayList()
                    val result = it.result
                    result.forEach { queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(User::class.java)
                        userList!!.add(user)
                    }
                    //Set date to adapter
                    userAdapter = UserAdapter(this, userList!!)
                    activityMainBinding.rv.adapter = userAdapter
                }
            }.addOnFailureListener {

                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }

        //Listen Changes On Firestore
        firebaseFirestore.collection("users")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                    if (error != null) {
                        return
                    }
                    for (documentChange in value?.documentChanges!!) {
                        if (documentChange.type == DocumentChange.Type.ADDED) {
                            //Notify Adapter
                            userList?.add(documentChange.document.toObject(User::class.java))
                            userAdapter?.notifyItemInserted(userList!!.size)
                        }
                    }

                }

            })

        //Upload File to Firebase Cloud Storage
        activityMainBinding.image.setOnClickListener {
            imageContent.launch("image/*")
        }

    }

    private var imageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            val currentTime = System.currentTimeMillis().toString()
            val uploadTask = reference.child(currentTime).putFile(uri!!)
            uploadTask.addOnSuccessListener {
                if (it.task.isSuccessful) {
                    Log.d(TAG, "${it.task}")
                    Toast.makeText(this, "Uploaded!", Toast.LENGTH_SHORT).show()
                    activityMainBinding.image.setImageURI(uri)

                    val downloadUrl = it.metadata?.reference?.downloadUrl
                    downloadUrl!!.addOnSuccessListener { imageUri ->
                        imgURL = imageUri.toString()
                    }
                }
            }.addOnFailureListener {
                Log.d(TAG, "${it.message}")
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
}