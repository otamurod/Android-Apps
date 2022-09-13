package com.otamurod.yolgahamroh.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.otamurod.yolgahamroh.R
import com.otamurod.yolgahamroh.adapters.UserAdapter
import com.otamurod.yolgahamroh.databinding.FragmentRealBinding
import com.otamurod.yolgahamroh.models.User
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RealFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val a = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    lateinit var binding: FragmentRealBinding
    lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var getActivity: AppCompatActivity
    private lateinit var userAdapter: UserAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for getActivity fragment
        binding = FragmentRealBinding.inflate(layoutInflater, container, false)

        getActivity = (activity as AppCompatActivity)
        getActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        getActivity.supportActionBar!!.setIcon(R.drawable.ic_launcher_round)
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("users")

        val current = LocalDateTime.now() //get current time
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a") //get current day
        val formatted = current.format(formatter) //format time
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm a")

        reference.child("/")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<User>()
                    val children = snapshot.children

                    for (child in children) {
                        val value = child.getValue(User::class.java)
                        if (value != null) {
                            list.add(value)

                            if (value.phone == auth.currentUser?.phoneNumber && sdf.parse("${value.date} ${value.time}")
                                    .before(sdf.parse(formatted))
                            ) {
                                /** check if values are not valid */
                                binding.date.text = "Sana:"
                                binding.partnerFromDir.text = "Stansiya:"
                                binding.partnerToDir.text = "Manzil:"
                            }
                            /** check old date values to delete. Should be in background */
                            if (sdf.parse("${value.date} ${value.time}")
                                    .before(sdf.parse(formatted))
                            ) {
                                reference.child("${value.phone}/")
                                    .setValue(null) // delete old data on the way
                            }

                        }
                    }

                    val listSupplier = arrayListOf<User>()
                    var currentUser: User? = null
                    for (user in list) {
                        if (user.phone != auth.currentUser?.phoneNumber) {
                            listSupplier.add(user)
                        } else {
                            currentUser = user

                            if (currentUser.type == "Haydovchi") {
                                binding.personType.text = "Yo'lovchilar"
                                binding.date.text = "Sana: ${currentUser.date}"
                                binding.partnerFromDir.text =
                                    "Stansiya: ${currentUser.fromCity}, ${currentUser.fromRegion}"
                                binding.partnerToDir.text =
                                    "Manzil: ${currentUser.toCity}, ${currentUser.toRegion}"
                            } else {
                                binding.personType.text = "Haydovchilar"
                                binding.date.text = "Sana: ${currentUser.date}"
                                binding.partnerFromDir.text =
                                    "Stansiya: ${currentUser.fromCity}, ${currentUser.fromRegion}"
                                binding.partnerToDir.text =
                                    "Manzil: ${currentUser.toCity}, ${currentUser.toRegion}"
                            }
                        }
                    }

                    val filteredList = listSupplier.filter { user: User ->
                        currentUser?.time == user.time &&
                                currentUser?.date == user.date &&
                                currentUser?.fromRegion == user.fromRegion &&
                                currentUser?.fromCity == user.fromCity &&
                                currentUser?.toRegion == user.toRegion &&
                                currentUser?.toCity == user.toCity &&
                                currentUser?.type != user.type
                    } as ArrayList<User>

                    userAdapter =
                        UserAdapter(filteredList, object : UserAdapter.OnItemClick {
                            override fun onCallClick(phoneNumber: String) {
                                dialPhone(phoneNumber)
                            }
                        })
                    binding.rvReal.adapter = userAdapter

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        binding.startTravelling.setOnClickListener {
            findNavController().navigate(R.id.addUserFragment)
        }

        return binding.root
    }

    private fun dialPhone(phoneNumber: String) {

        if (!TextUtils.isEmpty(phoneNumber)) {
            val intent =
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            val manager: PackageManager = getActivity.packageManager
            val info = manager.queryIntentActivities(intent, 0)
            if (info.size > 0) {
                getActivity.startActivity(intent)
            } else {
                Toast.makeText(
                    getActivity,
                    "Iltimos, telefon ilovasini o'rnating",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                getActivity,
                "Iltimos, telefon raqam kiriting",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            auth.signOut()
            findNavController().navigate(R.id.mainFragment)
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

}