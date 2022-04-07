package com.otamurod.pdpuz.fragments.group_fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.otamurod.pdpuz.R
import com.otamurod.pdpuz.adapters.ViewPagerAdapter
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.FragmentCourseGroupBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CourseGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var courseId: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_PARAM1)
//               param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    lateinit var courseGroupBinding: FragmentCourseGroupBinding
    lateinit var getActivity: AppCompatActivity
    var flag = false
    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseGroupBinding = FragmentCourseGroupBinding.inflate(layoutInflater, container, false)

        database = AppDatabase.getInstance(container!!.context)

        if (arguments != null) {
            courseId = arguments?.getInt("course_id") as Int
        }

        val courseById = database.courseDao().getCourseById(courseId!!)

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.title = courseById.name //change toolbar title

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle, courseId!!)

        courseGroupBinding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            courseGroupBinding.tabLayout,
            courseGroupBinding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Ochilgan guruhlar"
                }
                1 -> {
                    tab.text = "Ochilayotgan guruhlar"
                }
            }
        }.attach()

        courseGroupBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                flag = position == 1
                getActivity.invalidateOptionsMenu()
            }
        })

        return courseGroupBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (flag) {
            inflater.inflate(R.menu.menu_group, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_group -> {
                val bundle = bundleOf("course_id" to courseId)
                findNavController().navigate(R.id.addGroupFragment, bundle)
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
         * @return A new instance of fragment CourseGroupFragment.
        // TODO: Rename and change types and number of parameters*/
        @JvmStatic
        fun newInstance(
            param1: String
//        , param2: String
        ) =
            CourseGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

}