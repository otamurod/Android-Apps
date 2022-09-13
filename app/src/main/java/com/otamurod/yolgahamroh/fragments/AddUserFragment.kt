package com.otamurod.yolgahamroh.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.otamurod.yolgahamroh.R
import com.otamurod.yolgahamroh.databinding.FragmentAddUserBinding
import com.otamurod.yolgahamroh.models.Place
import com.otamurod.yolgahamroh.models.User
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddUserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private var targetCities: ArrayList<String>? = null
    var beginningCities: ArrayList<String>? = null
    private lateinit var getActivity: AppCompatActivity
    lateinit var binding: FragmentAddUserBinding

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddUserBinding.inflate(layoutInflater, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("users")

        getActivity = (activity as AppCompatActivity)
        getActivity.supportActionBar!!.setDisplayShowHomeEnabled(false)
        getActivity.supportActionBar!!.setDisplayUseLogoEnabled(true)
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        getActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

        binding.phoneNumberEt.setText(auth.currentUser?.phoneNumber)

        /** person type spinner */
        val type = ArrayList<String>()
        type.add("Shaxsingizni tasdiqlang👇")
        type.add("Haydovchi")
        type.add("Yo'lovchi")
        val typeSpinnerAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(getActivity, android.R.layout.simple_spinner_dropdown_item, type)
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.personType.adapter = typeSpinnerAdapter

        /** Starting region spinner */
        binding.fromRegionSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = binding.fromRegionSpinner.selectedItem.toString()
                    setBeginningCities(selectedItem)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        val fromRegionSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            getActivity,
            android.R.layout.simple_spinner_dropdown_item,
            arrayListOf(
                "Viloyatni Tanlang",
                "Andijon",
                "Buxoro",
                "Fargʻona",
                "Jizzax",
                "Namangan",
                "Navoiy",
                "Qashqadaryo",
                "Qoraqalpogʻiston Resp.",
                "Samarqand",
                "Sirdaryo",
                "Surxondaryo",
                "Toshkent",
                "Toshkent shahri",
                "Xorazm"
            )
        )
        fromRegionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromRegionSpinner.adapter = fromRegionSpinnerAdapter

        /** Target region spinner */
        binding.toRegionSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = binding.toRegionSpinner.selectedItem.toString()
                    setTargetCities(selectedItem)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        val toRegionSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            getActivity,
            android.R.layout.simple_spinner_dropdown_item,
            arrayListOf(
                "Viloyatni Tanlang",
                "Andijon",
                "Buxoro",
                "Fargʻona",
                "Jizzax",
                "Namangan",
                "Navoiy",
                "Qashqadaryo",
                "Qoraqalpogʻiston Resp.",
                "Samarqand",
                "Sirdaryo",
                "Surxondaryo",
                "Toshkent",
                "Toshkent shahri",
                "Xorazm"
            )
        )
        toRegionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.toRegionSpinner.adapter = toRegionSpinnerAdapter

        binding.datePicker.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.datePicker.right - binding.datePicker.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    // your action here
                    val c = Calendar.getInstance()
                    val year = c.get(Calendar.YEAR)
                    val month = c.get(Calendar.MONTH)
                    val day = c.get(Calendar.DAY_OF_MONTH)

                    val dpd = DatePickerDialog(
                        requireActivity(),
                        { view, year, monthOfYear, dayOfMonth ->

                            // Display Selected date in textbox
                            binding.datePicker.setText("$dayOfMonth/${monthOfYear + 1}/$year")

                        }, year, month, day
                    )

                    dpd.show()

                    return@OnTouchListener true
                }
            }
            false
        })

        binding.timePicker.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.datePicker.right - binding.datePicker.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    // your action here
                    val c = Calendar.getInstance()
                    val hour = c.get(Calendar.HOUR_OF_DAY)
                    val min = c.get(Calendar.MINUTE)

                    val timePickerDialog = TimePickerDialog(
                        activity,
                        { view, hourOfDay, minute ->
                            val AM_PM = if (hourOfDay > 12) {
                                "PM"
                            } else {
                                "AM"
                            }
                            binding.timePicker.text = String.format("%02d:%02d %s", hourOfDay, minute, AM_PM)

                        }, hour, min,
                        DateFormat.is24HourFormat(activity)
                    )

                    timePickerDialog.show()

                    return@OnTouchListener true
                }
            }
            false
        })

        /** TODO: Button  */
        binding.addUserBtn.setOnClickListener {
            val name = binding.name.text.toString()
            val phoneNumber = binding.phoneNumberEt.text.toString()
            val type = binding.personType.selectedItem.toString()
            val fromRegion = binding.fromRegionSpinner.selectedItem.toString()
            val fromCity = binding.fromPlaceSpinner.selectedItem.toString()
            val toRegion = binding.toRegionSpinner.selectedItem.toString()
            val toCity = binding.toPlaceSpinner.selectedItem.toString()
            val seats = binding.seatNumber.text.toString()
            val taxiFare = binding.taxiFare.text.toString()
            val time = binding.timePicker.text.toString()
            val date = binding.datePicker.text.toString()

            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && type.isNotEmpty() && fromRegion.isNotEmpty() && fromCity.isNotEmpty() &&
                toRegion.isNotEmpty() && toCity.isNotEmpty() && seats.isNotEmpty() && taxiFare.isNotEmpty() && time.isNotEmpty() && date.isNotEmpty()
            ) {
                val user = User(
                    name,
                    phoneNumber,
                    type,
                    fromRegion,
                    fromCity,
                    toRegion,
                    toCity,
                    date,
                    time,
                    seats,
                    taxiFare
                )
                storeInDB(user)
            } else {
                Toast.makeText(
                    getActivity,
                    "To'liq Ma'lumot Kiriting!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    private fun setTargetCities(selectedItem: String) {
        val place = Place()
        val keys = place.cities.keys

        for (region in keys) {
            if (selectedItem == region) {
                targetCities = place.cities[region]
            }
        }

        /** city spinner */
        val citySpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            getActivity,
            android.R.layout.simple_spinner_dropdown_item,
            targetCities!!
        )
        citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.toPlaceSpinner.adapter = citySpinnerAdapter

    }

    private fun setBeginningCities(selectedItem: String) {
        val place = Place()
        val keys = place.cities.keys

        for (region in keys) {
            if (selectedItem == region) {
                beginningCities = place.cities[region]
            }
        }

        /** city spinner */
        val citySpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            getActivity,
            android.R.layout.simple_spinner_dropdown_item,
            beginningCities!!
        )
        citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromPlaceSpinner.adapter = citySpinnerAdapter

    }

    private fun storeInDB(user: User) {

        reference.child("${user.phone}/").setValue(user)
            .addOnSuccessListener {
                // Write was successful!
                Toast.makeText(getActivity, "Qo'shildi", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                // Write failed
                Toast.makeText(getActivity, "Xatolik", Toast.LENGTH_SHORT).show()
            }
        getActivity.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            getActivity.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}