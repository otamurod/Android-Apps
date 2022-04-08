package com.otamurod.pdpuz.fragments.group_fragments

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
import com.otamurod.pdpuz.adapters.StudentAdapter
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.DeleteStudentDialogBinding
import com.otamurod.pdpuz.databinding.FragmentViewGroupBinding
import com.otamurod.pdpuz.entities.Student

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var groupId: Int? = null
    private var courseId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            groupId = it.getInt(ARG_PARAM1)
            courseId = it.getInt(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    lateinit var viewGroupBinding: FragmentViewGroupBinding
    lateinit var getActivity: AppCompatActivity
    lateinit var studentAdapter: StudentAdapter
    lateinit var list: ArrayList<Student>
    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewGroupBinding = FragmentViewGroupBinding.inflate(layoutInflater, container, false)
        database = AppDatabase.getInstance(container!!.context)
        list = ArrayList()

        if (arguments != null) {
            groupId = arguments?.getInt("group_id") as Int
            courseId = arguments?.getInt("course_id") as Int
        }

        list =
            database.studentDao()
                .getStudentsByGroupCourseId(courseId!!, groupId!!) as ArrayList<Student>
        val groupById = database.groupDao().getGroupById(groupId!!)

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar?.title = groupById.groupName

        studentAdapter = StudentAdapter(list, object : StudentAdapter.OnItemClickListener {
            override fun onEditClick(student: Student, position: Int) {
                val bundle = bundleOf("student_id" to student.id, "group_id" to groupId)
                findNavController().navigate(R.id.addStudentToGroupFragment, bundle)
            }

            override fun onDeleteClick(student: Student, position: Int) {

                val dialog = AlertDialog.Builder(requireContext())
                val dialogView = DeleteStudentDialogBinding.inflate(layoutInflater)
                dialog.setView(dialogView.root)

                dialog.setPositiveButton("Ha", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        database.studentDao().deleteStudent(student)
                        list.remove(student)
                        studentAdapter.notifyItemRemoved(position)
                        studentAdapter.notifyItemRangeChanged(position, list.size)

                        val studentsCount = database.studentDao().getStudentsInGroup(groupById.id!!)
                        viewGroupBinding.numberOfStudents.text = "Talabalar soni: $studentsCount ta"

                        Toast.makeText(context, "Talaba o'chirildi", Toast.LENGTH_SHORT).show()
                    }

                })

                dialog.setNegativeButton("Yo'q", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }
                })
                dialog.create()
                dialog.show()
            }
        })

        val studentsCount = database.studentDao().getStudentsInGroup(groupById.id!!)

        viewGroupBinding.groupNameTv.text = groupById.groupName
        viewGroupBinding.numberOfStudents.text = "Talabalar soni: $studentsCount ta"
        viewGroupBinding.groupTimeInterval.text = "Vaqti: ${groupById.time}"

        if (groupById.open == true) {
            viewGroupBinding.startGroupBtn.visibility = View.GONE
        }

        viewGroupBinding.startGroupBtn.setOnClickListener {
            groupById.open = true
            database.groupDao().updateGroup(groupById)
            findNavController().navigateUp()
        }

        viewGroupBinding.rvView.adapter = studentAdapter

        return viewGroupBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_group, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_group -> {
                val bundle = bundleOf("group_id" to groupId)
                findNavController().navigate(R.id.addStudentToGroupFragment, bundle)
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
         * @return A new instance of fragment ViewGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}