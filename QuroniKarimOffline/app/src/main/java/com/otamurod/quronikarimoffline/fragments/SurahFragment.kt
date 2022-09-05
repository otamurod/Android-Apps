/**
 * BISMILLAHIR ROHMANIR ROHIYM
 *
 * PROGRAM NAME: QUR'ONI KARIM
 * AUTHOR: SAFAROV OTAMUROD YUSUFALI O'G'LI
 * FINISHED ON: 08.08.2022, at 08:00 a.m
 *
 * DESCRIPTION: Tinglovchilar Muborak Qur'oni Karim suralari qiroatlarini to'liq eshitib bahramand bo'lish, shuningdek, o'zbek tilidagi tarjimasini o'qishlari mumkin. Alloh barchamizni o'zi iymonimizni mustahkam qilsin! Ushbu ilova orqali musulmonlarga foydam tegishidan umidvorman. Barcha insonlarga Allohning roziligini qo'lga kiritish nasib qilsin!
 *
 */

package com.otamurod.quronikarimoffline.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otamurod.quronikarimoffline.adapters.SurahAdapter
import com.otamurod.quronikarimoffline.models.Surahs
import com.otamurod.quronikarimoffline.R
import com.otamurod.quronikarimoffline.databinding.FragmentSurahBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class SurahFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    private lateinit var surahBinding: FragmentSurahBinding
    private lateinit var getActivity: AppCompatActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getActivity = (activity as AppCompatActivity?)!!

        getActivity.supportActionBar!!.show() //show appbar/toolbar
        getActivity.supportActionBar!!.title = "QUR'ONI KARIM OFFLINE"
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)//set visible
        getActivity.supportActionBar!!.setHomeButtonEnabled(false)

        // Inflate the layout for this fragment
        surahBinding = FragmentSurahBinding.inflate(layoutInflater, container, false)

        val surahs = Surahs()
        val listOfSurahs = surahs.getListOfSurahs()

        surahBinding.rvMain.adapter =
            SurahAdapter(requireContext(), listOfSurahs, object : SurahAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val bundle = bundleOf("position" to position)
                    findNavController().navigate(R.id.openSurahFragment, bundle)
                }

            })

        return surahBinding.root
    }
}