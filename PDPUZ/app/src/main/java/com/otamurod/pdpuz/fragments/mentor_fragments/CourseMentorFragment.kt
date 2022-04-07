package com.otamurod.pdpuz.fragments.mentor_fragments

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
import com.otamurod.pdpuz.adapters.MentorAdapter
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.EditMentorDialogBinding
import com.otamurod.pdpuz.databinding.FragmentCourseMentorBinding
import com.otamurod.pdpuz.entities.Mentor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CourseMentorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseMentorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var courseId: Int? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    lateinit var courseMentorBinding: FragmentCourseMentorBinding
    lateinit var getActivity: AppCompatActivity
    lateinit var mentorAdapter: MentorAdapter
    lateinit var list: ArrayList<Mentor>
    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseMentorBinding = FragmentCourseMentorBinding.inflate(layoutInflater, container, false)

        database = AppDatabase.getInstance(container!!.context)

        if (arguments != null) {
            courseId = arguments?.getInt("course_id") as Int
        }
        list = ArrayList()
        list = database.mentorDao().getMentorsByCourseId(courseId!!) as ArrayList<Mentor>
        val courseById = database.courseDao().getCourseById(courseId!!)

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.title = courseById.name //change toolbar title

        mentorAdapter = MentorAdapter(list, object : MentorAdapter.OnItemClickListener {

            override fun onDeleteClick(mentor: Mentor, position: Int) {
                database.mentorDao().deleteMentor(mentor)
                list.remove(mentor)
                mentorAdapter.notifyItemRemoved(position)
                mentorAdapter.notifyItemRangeChanged(position, list.size)
                Toast.makeText(context, "O'chirildi", Toast.LENGTH_SHORT).show()
            }

            override fun onEditClick(mentor: Mentor, position: Int) {
                Toast.makeText(context, "O'zgartirish", Toast.LENGTH_SHORT).show()

                val dialog = AlertDialog.Builder(requireContext())
                val dialogView = EditMentorDialogBinding.inflate(layoutInflater)
                dialogView.mentorLastName.setText(mentor.lastName)
                dialogView.mentorFirstName.setText(mentor.firstName)
                dialogView.mentorMiddleName.setText(mentor.middleName)

                dialog.setView(dialogView.root)

                dialog.setPositiveButton("O'zgartirish", object : DialogInterface.OnClickListener {

                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        val mentorLastName = dialogView.mentorLastName.text.toString()
                        val mentorFirstName = dialogView.mentorFirstName.text.toString()
                        val mentorMiddleName = dialogView.mentorMiddleName.text.toString()

                        mentor.lastName = mentorLastName
                        mentor.firstName = mentorFirstName
                        mentor.middleName = mentorMiddleName

                        database.mentorDao().updateMentor(mentor)
                        mentorAdapter.notifyItemChanged(position)

                        Toast.makeText(context, "Yangilandi", Toast.LENGTH_SHORT).show()
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
        })

        courseMentorBinding.rvMentor.adapter = mentorAdapter

        return courseMentorBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_mentor, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_mentor -> {
                val bundle = bundleOf("course_id" to courseId)
                findNavController().navigate(R.id.addMentorFragment, bundle)
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
        //         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseMentorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            param1: String
//        , param2: String
        ) =
            CourseMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}