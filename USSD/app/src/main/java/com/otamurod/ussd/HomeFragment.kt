package com.otamurod.ussd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.findNavController
import com.otamurod.ussd.adapters.ImageAdapter
import com.otamurod.ussd.models.ImageData
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

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

    lateinit var root:View
    var list = ArrayList<ImageData>()
    lateinit var imageAdapter: ImageAdapter
    var count = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false)

        if (count == 1){
            loadData()
            count++
        }

        imageAdapter = ImageAdapter(root.context, list)
        root.grid.adapter = imageAdapter

        root.grid.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 ->{
                    root.findNavController().navigate(R.id.tariflar)
                }
                1 ->{
                    root.findNavController().navigate(R.id.minutes)
                }
                2 ->{
                    root.findNavController().navigate(R.id.internet)
                }
                3 ->{
                    root.findNavController().navigate(R.id.sms)
                }
                4 ->{
                    root.findNavController().navigate(R.id.xizmatlar)
                }
                5 ->{
                    root.findNavController().navigate(R.id.yangiliklar)
                }
            }
        }

        return root
    }

    private fun loadData() {
        list.add(ImageData(R.drawable.tariflar, "Tariflar"))
        list.add(ImageData(R.drawable.soat, "Daqiqalar"))
        list.add(ImageData(R.drawable.internet,"Internet"))
        list.add(ImageData(R.drawable.sms, "Sms"))
        list.add(ImageData(R.drawable.xizmatlar, "Xizmatlar"))
        list.add(ImageData(R.drawable.yangiliklar, "Yangiliklar"))
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