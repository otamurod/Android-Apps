package com.otamurod.viewpagertablayout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otamurod.viewpagertablayout.adapters.OnItemClickListener
import com.otamurod.viewpagertablayout.adapters.RvAdapter
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.image_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment(), OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: ArrayList<String>? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getStringArrayList(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    lateinit var rvAdapter: RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_category, container, false)

        rvAdapter = RvAdapter(param1!!, this)

        root.rv.adapter = rvAdapter

        return root
    }

    override fun onItemClicked(string: String) {
        val intent = Intent("gallery")
        intent.putExtra("url", string)

        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<String>) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, param1)
                }
            }
    }
}