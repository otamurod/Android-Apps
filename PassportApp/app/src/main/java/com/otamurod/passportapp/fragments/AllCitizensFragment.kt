package com.otamurod.passportapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.otamurod.passportapp.R
import com.otamurod.passportapp.adapters.CitizenAdapter
import com.otamurod.passportapp.database.AppDatabase
import com.otamurod.passportapp.databinding.FragmentAllCitizensBinding
import com.otamurod.passportapp.entities.Citizen


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllCitizensFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllCitizensFragment : Fragment() {
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

    private lateinit var allCitizensBinding: FragmentAllCitizensBinding
    lateinit var citizenAdapter: CitizenAdapter
    lateinit var appDatabase: AppDatabase
    lateinit var citizens: ArrayList<Citizen>

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        allCitizensBinding = FragmentAllCitizensBinding.inflate(layoutInflater, container, false)

        appDatabase = AppDatabase.getInstance(container?.context!!)
        citizens = ArrayList()
        citizens = appDatabase.citizenDao().getAllCitizens() as ArrayList<Citizen>

        citizenAdapter = CitizenAdapter(citizens, object : CitizenAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                val citizenDataFragment = CitizenDataFragment.newInstance(id)

                fragmentManager?.beginTransaction() // ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                    ?.replace(
                        R.id.container,
                        citizenDataFragment
                    ) //replace fragment
                    ?.addToBackStack(citizenDataFragment.toString())
                    ?.commit()
            }

            /*override fun onItemDeleteClick(citizen: Citizen, position: Int) {
                val dialog = AlertDialog.Builder(context!!)
                val dialogBinding = DialogConfirmationBinding.inflate(layoutInflater)
                dialog.setView(dialogBinding.root)

                dialog.setPositiveButton("Ha", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        appDatabase.citizenDao().deleteCitizen(citizen)
                        citizens.remove(citizen)
                        citizenAdapter.notifyItemRemoved(position)
                        citizenAdapter.notifyItemRangeChanged(position, citizens.size)
                    }


                })
                dialog.setNegativeButton("Yo'q", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }

                })

                dialog.show()
            }*/
        })

        allCitizensBinding.rv.adapter = citizenAdapter

        /*allCitizensBinding.searchCitizenBtn.setOnSearchClickListener {
            allCitizensBinding.toolbarAllCitizen.title = ""
        }

        allCitizensBinding.searchCitizenBtn.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                allCitizensBinding.toolbarAllCitizen.title = ""
                citizenAdapter.filter.filter(newText)
                return false
            }
        })

        allCitizensBinding.searchCitizenBtn.setOnCloseListener {
            allCitizensBinding.toolbarAllCitizen.title = "Barcha Fuqarolar"
            false
        }*/

        allCitizensBinding.toolbarAllCitizen.setOnMenuItemClickListener(object :
            Toolbar.OnMenuItemClickListener {
            @SuppressLint("ResourceAsColor")
            override fun onMenuItemClick(item: MenuItem?): Boolean {

                val searchView = item?.actionView as SearchView
                searchView.setBackgroundResource(R.drawable.search_background)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        citizenAdapter.filter.filter(newText)
                        return false
                    }

                })
                return false
            }

        })

        allCitizensBinding.toolbarAllCitizen.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        return allCitizensBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllCitizensFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllCitizensFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}