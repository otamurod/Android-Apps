package com.otamurod.pdpuz.fragments.mentor_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.R
import com.otamurod.pdpuz.adapters.CourseAdapter
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentCoursesInMentorBinding
import com.otamurod.pdpuz.entities.Course

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoursesInMentorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoursesInMentorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var courseAdapter: CourseAdapter
    lateinit var list: ArrayList<Course>
    lateinit var getActivity: AppCompatActivity
    lateinit var coursesInMentorBinding: FragmentCoursesInMentorBinding

    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        coursesInMentorBinding =
            FragmentCoursesInMentorBinding.inflate(layoutInflater, container, false)
        getActivity = (activity as AppCompatActivity?)!!

        getActivity.supportActionBar!!.show() //show appbar/toolbar
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)//set visible
        getActivity.supportActionBar!!.setHomeButtonEnabled(true)
        getActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back) //set navigation icon
        getActivity.supportActionBar!!.title = "Barcha Kurslar" //change toolbar title

        database = AppDatabase.getInstance(container!!.context)
        list = ArrayList()
        list = database.courseDao().getAllCourses() as ArrayList<Course>

        courseAdapter = CourseAdapter(list, object : CourseAdapter.OnItemClickListener {
            override fun onItemClick(course: Course) {

                val bundle= bundleOf("course_id" to course.id)
                findNavController().navigate(R.id.courseMentorFragment, bundle)
            }
        })

        coursesInMentorBinding.rvCourse.adapter = courseAdapter

        return coursesInMentorBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CoursesInMentorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CoursesInMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}