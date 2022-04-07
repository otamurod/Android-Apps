package com.otamurod.passportapp.fragments

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.otamurod.passportapp.database.AppDatabase
import com.otamurod.passportapp.databinding.FragmentCitizenDataBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CitizenDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CitizenDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var citizenDataBinding: FragmentCitizenDataBinding
    lateinit var appDatabase: AppDatabase

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        citizenDataBinding = FragmentCitizenDataBinding.inflate(layoutInflater, container, false)
        appDatabase = AppDatabase.getInstance(container?.context!!)

        citizenDataBinding.toolbarCitizen.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        val citizenById = appDatabase.citizenDao().getCitizenById(param1!!)
        citizenDataBinding.toolbarCitizen.title = "${citizenById.firstName} ${citizenById.lastName}"

        val image = BitmapFactory.decodeByteArray(citizenById.image, 0, citizenById.image.size)

        Glide.with(container.context!!)
            .asBitmap()
            .load(image)
            .into(citizenDataBinding.citizenImageOut);

        citizenDataBinding.fullNameOut.text =
            "Ismi:      ${citizenById.firstName}\nFamiliyasi:      ${citizenById.lastName}\nSharifi:      ${citizenById.middleName}"
        citizenDataBinding.addressOut.text =
            "Viloyat:      ${citizenById.regionAddress}\nTuman/Shahar:      ${citizenById.cityAddress}"
        citizenDataBinding.homeAddressOut.text = "Uy Manzili:\n      ${citizenById.homeAddress}"
        citizenDataBinding.passportNumber.text = "Passport Raqami:      ${citizenById.passportNo}"
        citizenDataBinding.passportTakenTime.text =
            "Passport Berilgan Vaqt:\n      ${citizenById.passportTakenDate}"
        citizenDataBinding.passportValidTime.text =
            "Passport Muddati:\n      ${citizenById.passportValidTill}"
        citizenDataBinding.genderOut.text = "Jinsi:      ${citizenById.gender}"

        return citizenDataBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CitizenDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            CitizenDataFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}