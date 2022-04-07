package com.otamurod.pdpuz.fragments.group_fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.otamurod.pdpuz.R
import com.otamurod.pdpuz.adapters.GroupAdapter
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.EditGroupDialogBinding
import com.otamurod.pdpuz.databinding.FragmentOpenGroupBinding
import com.otamurod.pdpuz.entities.Group
import com.otamurod.pdpuz.entities.Mentor
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var courseId: Int? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        arguments?.let {
            courseId = it.getInt(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var openGroupBinding: FragmentOpenGroupBinding
    lateinit var groupAdapter: GroupAdapter
    lateinit var list: ArrayList<Group>
    lateinit var nestedNavController: NavController
    lateinit var getActivity: AppCompatActivity
    lateinit var mainNavController: NavController
    lateinit var database: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // we can get the innermost NavController using this view,
        // because we are inside its subtree:
        nestedNavController = NavHostFragment.findNavController(this)

        // we can find the outer NavController passing the owning Activity
        // and the id of a view associated to that NavController,
        // for example the NavHostFragment id:
        mainNavController = Navigation.findNavController(requireActivity(), R.id.container_group)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        openGroupBinding = FragmentOpenGroupBinding.inflate(layoutInflater, container, false)
        getActivity = (activity as AppCompatActivity?)!!

        database = AppDatabase.getInstance(requireContext())
        list = ArrayList()
        if (courseId != null) {
            list = database.groupDao().getGroupsByType(courseId!!, true) as ArrayList<Group>
        }

        groupAdapter =
            GroupAdapter(requireContext(), list, object : GroupAdapter.OnItemClickListener {

                override fun onViewClick(group: Group, position: Int) {
                    Toast.makeText(context, "Ko'rish", Toast.LENGTH_SHORT).show()

                    val bundle = bundleOf("course_id" to group.courseId, "group_id" to group.id)
                    mainNavController.navigate(R.id.viewGroupFragment, bundle)
                }

                override fun onEditClick(group: Group, position: Int) {
                    Toast.makeText(context, "O'zgartirish", Toast.LENGTH_SHORT).show()
                    val dialog = AlertDialog.Builder(requireContext())
                    val dialogView = EditGroupDialogBinding.inflate(layoutInflater)

                    val mentorsByCourseId =
                        database.mentorDao()
                            .getMentorsByCourseId(group.courseId!!) as ArrayList<Mentor>

                    //mentor spinner
                    val mentorList = ArrayList<String>()
                    for (mentor in mentorsByCourseId) {
                        mentorList.add("${mentor.firstName} ${mentor.lastName}")
                    }
                    val mentorSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        mentorList
                    )
                    mentorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    dialogView.mentorSpinner.adapter = mentorSpinnerAdapter

                    //time spinner
                    val timeSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayListOf(
                            "09:00-10:30",
                            "10:30-12:00",
                            "12:00-13:30",
                            "13:30-15:00",
                            "15:00-16:30",
                            "16:30-18:00"
                        )
                    )
                    timeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    dialogView.timeSpinner.adapter = timeSpinnerAdapter

                    //set group name
                    dialogView.groupNameEt.setText(group.groupName)

                    dialog.setView(dialogView.root)
                    dialog.setPositiveButton(
                        "O'zgartirish",
                        object : DialogInterface.OnClickListener {

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                //get mentor id
                                var mentorId: Int? = null
                                if (dialogView.mentorSpinner.selectedItem != null) {
                                    val mentorName =
                                        dialogView.mentorSpinner.selectedItem.toString()
                                    val tokens = StringTokenizer(mentorName, " ")
                                    val firstName: String = tokens.nextToken()
                                    val lastName: String = tokens.nextToken()
                                    mentorId =
                                        database.mentorDao().getMentorIdByName(firstName, lastName)
                                }
                                if (mentorId != null) {
                                    group.groupName = dialogView.groupNameEt.text.toString()
                                    group.mentorId = mentorId
                                    group.time = dialogView.timeSpinner.selectedItem.toString()

                                    database.groupDao().updateGroup(group)

                                    groupAdapter.notifyItemChanged(position)
                                    Toast.makeText(context, "Yangilandi", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "To'liq ma'lumot kiriting!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
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

                override fun onDeleteClick(group: Group, position: Int) {
                    database.groupDao().deleteGroup(group)
                    list.remove(group)
                    groupAdapter.notifyItemRemoved(position)
                    groupAdapter.notifyItemRangeChanged(position, list.size)
                    Toast.makeText(context, "O'chirildi", Toast.LENGTH_SHORT).show()
                }

            })

        openGroupBinding.rvGroup.adapter = groupAdapter

        return openGroupBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param courseId Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OpenGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            courseId: Int
//                        , param2: String
        ) =
            OpenGroupFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, courseId)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}