package com.otamurod.pdpuz.fragments.course_fragments

import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentAddStudentToCourseBinding
import com.otamurod.pdpuz.entities.Mentor
import com.otamurod.pdpuz.entities.Student
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentToCourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStudentToCourseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var courseId: Int? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var getActivity: AppCompatActivity
    lateinit var addStudentToCourseBinding: FragmentAddStudentToCourseBinding
    lateinit var database: AppDatabase

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addStudentToCourseBinding =
            FragmentAddStudentToCourseBinding.inflate(layoutInflater, container, false)

        if (arguments != null) {
            courseId = arguments?.getInt("course_id") as Int
        }

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.title = "Talaba Qo'shish" //change toolbar title

        database = AppDatabase.getInstance(container!!.context)

        addStudentToCourseBinding.studentAddedDateEt.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= addStudentToCourseBinding.studentAddedDateEt.right - addStudentToCourseBinding.studentAddedDateEt.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
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
                            addStudentToCourseBinding.studentAddedDateEt.setText("$dayOfMonth/${monthOfYear + 1}/$year")

                        }, year, month, day
                    )

                    dpd.show()

                    return@OnTouchListener true
                }
            }
            false
        })

        val mentorsByCourseId =
            database.mentorDao().getMentorsByCourseId(courseId!!) as ArrayList<Mentor>
        val mentorList = ArrayList<String>()
        for (mentor in mentorsByCourseId) {
            mentorList.add("${mentor.firstName!!} ${mentor.lastName}")
        }
        val mentorSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            mentorList
        )
        mentorSpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        addStudentToCourseBinding.mentorSpinner.adapter = mentorSpinnerAdapter

        val groupsByCourseId = database.groupDao().getGroupsByCourseId(courseId!!)
        val groupList = ArrayList<String>()
        for (group in groupsByCourseId) {
            groupList.add(group.groupName!!)
        }
        val groupSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            groupList
        )
        groupSpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        addStudentToCourseBinding.groupSpinner.adapter = groupSpinnerAdapter

        addStudentToCourseBinding.saveStudentBtn.setOnClickListener {

            val studentFirstName = addStudentToCourseBinding.studentFirstNameEt.text.toString()
            val studentLastName = addStudentToCourseBinding.studentLastNameEt.text.toString()
            val studentMiddleName = addStudentToCourseBinding.studentMiddleNameEt.text.toString()
            val registrationDate = addStudentToCourseBinding.studentAddedDateEt.text.toString()

            var mentorName: String? = null
            if (addStudentToCourseBinding.mentorSpinner.selectedItem != null) {
                mentorName = addStudentToCourseBinding.mentorSpinner.selectedItem.toString()
            }
            var groupId: Int? = null
            var groupName: String? = null
            if (addStudentToCourseBinding.groupSpinner.selectedItem != null) {
                groupName = addStudentToCourseBinding.groupSpinner.selectedItem.toString()
                groupId = database.groupDao().getGroupByName(groupName)
            }

            //TODO: Save to database
            if (courseId != null &&
                groupId != null &&
                studentLastName.isNotEmpty() &&
                studentFirstName.isNotEmpty() &&
                studentMiddleName.isNotEmpty() &&
                registrationDate.isNotEmpty() &&
                mentorName != null &&
                groupName != null
            ) {

                val student = Student(
                    courseId,
                    groupId,
                    studentLastName,
                    studentFirstName,
                    studentMiddleName,
                    registrationDate,
                    mentorName,
                    groupName
                )

                database.studentDao().insertStudent(student)

                Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "To'liq ma'lumot kiriting", Toast.LENGTH_SHORT).show()
            }

        }

        return addStudentToCourseBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentToCourseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            param1: Int
//                        , param2: String
        ) =
            AddStudentToCourseFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}