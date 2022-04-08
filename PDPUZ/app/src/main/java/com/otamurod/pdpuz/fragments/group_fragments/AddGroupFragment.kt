package com.otamurod.pdpuz.fragments.group_fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentAddGroupBinding
import com.otamurod.pdpuz.entities.Group
import com.otamurod.pdpuz.entities.Mentor
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var courseId: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var addGroupBinding: FragmentAddGroupBinding
    lateinit var getActivity: AppCompatActivity
    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addGroupBinding = FragmentAddGroupBinding.inflate(layoutInflater, container, false)
        database = AppDatabase.getInstance(container!!.context)

        if (arguments != null) {
            courseId = arguments?.getInt("course_id") as Int
        }
        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.title = "Yangi guruh qo'shish" //change toolbar title

        val mentorsByCourseId =
            database.mentorDao().getMentorsByCourseId(courseId!!) as ArrayList<Mentor>

        //mentor spinner
        val mentorList = ArrayList<String>()
        for (mentor in mentorsByCourseId) {
            mentorList.add("${mentor.firstName} ${mentor.lastName}")
        }
        val mentorSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            mentorList
        )
        mentorSpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        addGroupBinding.mentorSpinner.adapter = mentorSpinnerAdapter

        //time spinner
        val timeSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            arrayListOf(
                "09:00-10:30",
                "10:30-12:00",
                "12:00-13:30",
                "13:30-15:00",
                "15:00-16:30",
                "16:30-18:00"
            )
        )
        timeSpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        addGroupBinding.timeSpinner.adapter = timeSpinnerAdapter


        //day spinner
        val daySpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            arrayListOf("Monday, Wednesday, Friday", "Tuesday, Thursday, Saturday")
        )
        daySpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        addGroupBinding.daySpinner.adapter = daySpinnerAdapter

        //get mentor id
        var mentorId: Int? = null
        if (addGroupBinding.mentorSpinner.selectedItem != null) {
            val mentorName = addGroupBinding.mentorSpinner.selectedItem.toString()
            val tokens = StringTokenizer(mentorName, " ")
            val firstName: String = tokens.nextToken()
            val lastName: String = tokens.nextToken()
            mentorId = database.mentorDao().getMentorIdByName(firstName, lastName)
        }

        //save group into database
        addGroupBinding.saveGroupBtn.setOnClickListener {

            val groupName = addGroupBinding.groupNameEt.text.toString()
            val time = addGroupBinding.timeSpinner.selectedItem.toString()
            val day = addGroupBinding.daySpinner.selectedItem.toString()

            if (mentorId != null
                && courseId != null &&
                groupName.isNotEmpty() &&
                time.isNotEmpty() &&
                day.isNotEmpty()
            ) {
                val group = Group(courseId, mentorId, groupName, time, day, false)
                database.groupDao().insertGroup(group)

                findNavController().navigateUp()
                Toast.makeText(context, "Guruh qo'shildi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "To'liq ma'lumot kiriting!", Toast.LENGTH_SHORT).show()
            }
        }

        return addGroupBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}