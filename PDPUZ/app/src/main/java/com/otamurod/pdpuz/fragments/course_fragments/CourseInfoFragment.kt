package com.otamurod.pdpuz.fragments.course_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.R
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentCourseInfoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CourseInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var courseInfoBinding: FragmentCourseInfoBinding
    lateinit var getActivity: AppCompatActivity
    lateinit var database: AppDatabase

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseInfoBinding = FragmentCourseInfoBinding.inflate(layoutInflater, container, false)

        database = AppDatabase.getInstance(container!!.context)

        if (arguments != null) {
            id = arguments?.getInt("course_id") as Int
        }
        val courseById = database.courseDao().getCourseById(id!!)

        //TODO: change toolbar title
        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.title = courseById.name //change toolbar title

        courseInfoBinding.text.text = courseById.description

        courseInfoBinding.addStudentBtn.setOnClickListener {
            val bundle = bundleOf("course_id" to id)

            findNavController().navigate(R.id.addStudentToCourseFragment, bundle)
        }

        var count = 1
        courseInfoBinding.deleteCourseBtn.setOnClickListener {
            if (count == 2) {
                val course = database.courseDao().getCourseById(id!!)
                database.courseDao().deleteCourse(course)
                findNavController().navigateUp()
                Toast.makeText(
                    container.context,
                    "Kurs o'chirildi",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (count < 2) {
                count += 1
                Toast.makeText(
                    container.context,
                    "Kursni o'chirish uchun yana bir marta bosing!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return courseInfoBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            param1: String
//                        , param2: String
        ) =
            CourseInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}