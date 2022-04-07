package com.otamurod.pdpuz.fragments.course_fragments

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.pdpuz.R
import com.otamurod.pdpuz.adapters.CourseAdapter
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.AddCourseDialogBinding
import com.otamurod.pdpuz.databinding.FragmentAllCoursesListBinding
import com.otamurod.pdpuz.entities.Course


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllCoursesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllCoursesListFragment : Fragment() {
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

    lateinit var allCoursesListbinding: FragmentAllCoursesListBinding
    lateinit var courseAdapter: CourseAdapter
    lateinit var list: ArrayList<Course>
    lateinit var getActivity: AppCompatActivity

    lateinit var database: AppDatabase

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        allCoursesListbinding =
            FragmentAllCoursesListBinding.inflate(layoutInflater, container, false)

        getActivity = (activity as AppCompatActivity?)!!

        getActivity.supportActionBar!!.show() //show appbar/toolbar
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)//set visible
        getActivity.supportActionBar!!.setHomeButtonEnabled(true)
        getActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back) //set navigation icon
        getActivity.supportActionBar!!.title = "Barcha Kurslar Ro'yxati" //change toolbar title


        database = AppDatabase.getInstance(container!!.context)

        list = ArrayList()

        list = database.courseDao().getAllCourses() as ArrayList<Course>

        courseAdapter = CourseAdapter(list, object : CourseAdapter.OnItemClickListener {
            override fun onItemClick(course: Course) {
                val courseId = database.courseDao().getCourseId(course.name.toString())
                val bundle = bundleOf("course_id" to courseId)
                findNavController().navigate(R.id.courseInfoFragment, bundle)
            }
        })

        allCoursesListbinding.rvCourse.adapter = courseAdapter

        return allCoursesListbinding.root
    }

    //click events are handled in activity itself
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_course, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.add_course -> {

                val dialog = AlertDialog.Builder(requireContext())
                val dialogView = AddCourseDialogBinding.inflate(layoutInflater)

                dialog.setView(dialogView.root)

                dialog.setPositiveButton("Qo'shish", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val courseName = dialogView.addCourseNameEt.text.toString()
                        val courseInfo = dialogView.addCourseInfoEt.text.toString()
                        val course = Course(courseName, courseInfo)
                        database.courseDao().insertCourse(course)
                        list.add(course)

                        courseAdapter.notifyItemInserted(list.size)

                        Toast.makeText(context, "$courseName Qo'shildi", Toast.LENGTH_SHORT).show()
                    }

                })

                dialog.setNegativeButton("Yopish", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }
                })

                dialog.create()
                dialog.show()
            }
        }
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllCoursesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllCoursesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}