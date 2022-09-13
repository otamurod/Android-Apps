package com.otamurod.yolgahamroh.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.otamurod.yolgahamroh.R
import com.otamurod.yolgahamroh.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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

    lateinit var binding: FragmentMainBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()

        navigate()

        return binding.root
    }

    private fun navigate() {
        if (auth.currentUser == null) {
            findNavController().popBackStack()
            findNavController().navigate(R.id.registerFragment)
        } else {
            findNavController().popBackStack()
//            Toast.makeText(this, "${auth.currentUser?.phoneNumber}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.realFragment)
        }
    }

    override fun onResume() {
        navigate()
        super.onResume()
    }
}