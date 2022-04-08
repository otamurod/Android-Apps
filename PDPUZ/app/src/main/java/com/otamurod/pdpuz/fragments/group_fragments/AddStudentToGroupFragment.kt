package com.otamurod.pdpuz.fragments.group_fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentAddStudentToGroupBinding
import com.otamurod.pdpuz.entities.Student
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentToGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStudentToGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var studentId: Int? = null
    private var groupId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            studentId = it.getInt(ARG_PARAM1)
            groupId = it.getInt(ARG_PARAM2)
        }
    }

    lateinit var addStudentToGroupBinding: FragmentAddStudentToGroupBinding
    lateinit var getActivity: AppCompatActivity
    lateinit var database: AppDatabase

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addStudentToGroupBinding =
            FragmentAddStudentToGroupBinding.inflate(layoutInflater, container, false)

        if (arguments != null) {
            if (arguments?.getInt("student_id") != null) {
                studentId = arguments?.getInt("student_id") as Int
            }
            groupId = arguments?.getInt("group_id") as Int
        }

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar?.title = "Talaba qo'shish"

        database = AppDatabase.getInstance(requireContext())
        var studentById: Student? = null

        if (studentId != null) {
            studentById = database.studentDao().getStudentById(studentId!!)

            if (studentById != null) {
                addStudentToGroupBinding.studentLastNameEt.setText(studentById.lastName!!)
                addStudentToGroupBinding.studentFirstNameEt.setText(studentById.firstName!!)
                addStudentToGroupBinding.studentMiddleNameEt.setText(studentById.middleName!!)
                addStudentToGroupBinding.studentAddedDateEt.setText(studentById.date!!)
                addStudentToGroupBinding.saveStudentBtn.text = "O'zgartirish"
            }
        }

        addStudentToGroupBinding.studentAddedDateEt.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= addStudentToGroupBinding.studentAddedDateEt.right - addStudentToGroupBinding.studentAddedDateEt.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
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
                            addStudentToGroupBinding.studentAddedDateEt.setText("$dayOfMonth/${monthOfYear + 1}/$year")

                        }, year, month, day
                    )

                    dpd.show()

                    return@OnTouchListener true
                }
            }
            false
        })

        addStudentToGroupBinding.saveStudentBtn.setOnClickListener {

            val groupById = database.groupDao().getGroupById(groupId!!)

            val studentFirstName =
                addStudentToGroupBinding.studentFirstNameEt.text.toString()
            val studentLastName = addStudentToGroupBinding.studentLastNameEt.text.toString()
            val studentMiddleName =
                addStudentToGroupBinding.studentMiddleNameEt.text.toString()
            val registrationDate =
                addStudentToGroupBinding.studentAddedDateEt.text.toString()
            val mentor = database.mentorDao().getMentorById(groupById.mentorId!!)
            val mentorName = "${mentor.lastName} ${mentor.firstName}"

            if (studentById != null &&
                studentFirstName.isNotEmpty() &&
                studentLastName.isNotEmpty() &&
                studentMiddleName.isNotEmpty() &&
                registrationDate.isNotEmpty() &&
                groupId != null &&
                groupById.groupName != null &&
                groupById.courseId != null &&
                mentorName.isNotEmpty()
            ) { //update
                studentById.firstName = studentFirstName
                studentById.lastName = studentLastName
                studentById.middleName = studentMiddleName
                studentById.date = registrationDate
                studentById.groupId = groupId
                studentById.groupName = groupById.groupName
                studentById.courseId = groupById.courseId
                studentById.mentorName = mentorName

                database.studentDao().updateStudent(studentById)

            } else if (studentFirstName.isNotEmpty() &&
                studentLastName.isNotEmpty() &&
                studentMiddleName.isNotEmpty() &&
                registrationDate.isNotEmpty() &&
                groupId != null &&
                groupById.groupName != null &&
                groupById.courseId != null &&
                mentorName.isNotEmpty()
            ) {
                val student = Student(
                    groupById.courseId,
                    groupById.id,
                    studentLastName,
                    studentFirstName,
                    studentMiddleName,
                    registrationDate,
                    mentorName,
                    groupById.groupName
                )
                database.studentDao().insertStudent(student)
                Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "To'liq ma'lumot kiriting", Toast.LENGTH_SHORT).show()
            }
        }

        return addStudentToGroupBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentToGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(studentId: Int, groupId: Int) =
            AddStudentToGroupFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, studentId)
                    putInt(ARG_PARAM2, groupId)
                }
            }
    }
}