package com.otamurod.passportapp.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.otamurod.passportapp.R
import com.otamurod.passportapp.databinding.FragmentHomeBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    lateinit var homeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        homeBinding.showAllCitizensBtn.setOnClickListener {

            val allCitizensFragment = AllCitizensFragment()
            fragmentManager?.beginTransaction() // ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                ?.replace(R.id.container, allCitizensFragment) //replace fragment
                ?.addToBackStack(allCitizensFragment.toString())
                ?.commit()
        }

        homeBinding.addCitizenBtn.setOnClickListener {

            val addCitizenFragment = AddCitizenFragment()
            fragmentManager?.beginTransaction() // ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                ?.replace(R.id.container, addCitizenFragment) //replace fragment
                ?.addToBackStack(addCitizenFragment.toString())
                ?.commit()
        }

        return homeBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)





    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}