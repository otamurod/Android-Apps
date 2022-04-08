package com.otamurod.pdpuz.fragments.mentor_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentAddMentorBinding
import com.otamurod.pdpuz.entities.Mentor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddMentorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMentorFragment : Fragment() {
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

    lateinit var addMentorBinding: FragmentAddMentorBinding
    lateinit var getActivity: AppCompatActivity
    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        addMentorBinding = FragmentAddMentorBinding.inflate(layoutInflater, container, false)
        database = AppDatabase.getInstance(container!!.context)

        if (arguments != null) {
            courseId = arguments?.getInt("course_id") as Int
        }

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.title = "Yangi mentor qo'shish" //change toolbar title

        addMentorBinding.saveMentorBtn.setOnClickListener {
            val mentorFirstNameEt = addMentorBinding.mentorFirstNameEt.text.toString()
            val mentorLastNameEt = addMentorBinding.mentorLastNameEt.text.toString()
            val mentorMiddleNameEt = addMentorBinding.mentorMiddleNameEt.text.toString()

            if (mentorFirstNameEt.isNotEmpty() && mentorLastNameEt.isNotEmpty() && mentorMiddleNameEt.isNotEmpty()) {

                val mentor =
                    Mentor(courseId, mentorLastNameEt, mentorFirstNameEt, mentorMiddleNameEt)
                database.mentorDao().insertMentor(mentor)

                findNavController().navigateUp()
                Toast.makeText(context, "Bajarildi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Ma'lumotni to'liq kiriting", Toast.LENGTH_SHORT).show()
            }
        }

        return addMentorBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddMentorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}