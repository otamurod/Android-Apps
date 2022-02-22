package com.otamurod.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.otamurod.navigation.models.User
import kotlinx.android.synthetic.main.fragment_first.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
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

    lateinit var root:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_first, container, false)

        root.btn.setOnClickListener {
            val email = root.edit1.text.toString()
            val password = root.edit2.text.toString()

//            val bundle = Bundle()
            val user = User(email, password)
//            bundle.putSerializable("user", user)

//            bundle.putString("email", email)
//            bundle.putString("password", password)

            val bundle = bundleOf("user" to user, "address" to "Uzbekistan")

            val navOptions = NavOptions.Builder()
            navOptions.setEnterAnim(R.anim.exit_anim)
            navOptions.setExitAnim(R.anim.pop_enter_anim)
            navOptions.setPopEnterAnim(R.anim.enter_anim)
            navOptions.setPopExitAnim(R.anim.pop_exit_anim)
            navOptions.build()

            findNavController().navigate(R.id.thirdFragment, bundle, navOptions.build())

        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}