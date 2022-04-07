package com.otamurod.passportapp.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.otamurod.passportapp.database.AppDatabase
import com.otamurod.passportapp.databinding.DialogBinding
import com.otamurod.passportapp.databinding.FragmentAddCitizenBinding
import com.otamurod.passportapp.entities.Citizen
import java.io.ByteArrayOutputStream


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCitizenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCitizenFragment : Fragment() {
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

    lateinit var addCitizenBinding: FragmentAddCitizenBinding
    private val IMAGE_CODE = 1
    private var selectedImageUri: Uri? = null

    //    private var citizenImage: String? = null
    private var citizenImage: ByteArray? = null
    lateinit var appDatabase: AppDatabase

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addCitizenBinding = FragmentAddCitizenBinding.inflate(layoutInflater, container, false)


        appDatabase = AppDatabase.getInstance(container?.context!!)
        val regions: Array<String> = arrayOf(
            "Andijon",
            "Buxoro",
            "Farg'ona",
            "Jizzax",
            "Namangan",
            "Navoiy",
            "Qashqadaryo",
            "Samarqand",
            "Sirdaryo",
            "Surxondaryo",
            "Toshkent",
            "Xorazm",
            "Qoraqalpog'iston Res."
        )

        addCitizenBinding.regionSpinner.adapter =
            ArrayAdapter(container.context, android.R.layout.simple_list_item_1, regions)

        val gender: Array<String> = arrayOf(
            "Erkak",
            "Ayol",
        )
        addCitizenBinding.genderSpinner.adapter =
            ArrayAdapter(container.context, android.R.layout.simple_list_item_1, gender)

        addCitizenBinding.citizenImageOut.setOnClickListener {
            //TODO: get image from device


            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Rasm Tanlang"), IMAGE_CODE
            )

        }

        addCitizenBinding.toolbarCitizen.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }
        addCitizenBinding.saveCitizenBtn.setOnClickListener {

            val dialog = AlertDialog.Builder(container.context)
            val dialogBinding = DialogBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)

            dialog.setPositiveButton("Ha", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    val firstName = addCitizenBinding.firstName.text.toString()
                    val lastName = addCitizenBinding.lastName.text.toString()
                    val middleName = addCitizenBinding.middleName.text.toString()
                    val region = addCitizenBinding.regionSpinner.selectedItem.toString()
                    val cityAddress = addCitizenBinding.cityAddress.text.toString()
                    val homeAddress = addCitizenBinding.homeAddress.text.toString()
                    val passportNo = addCitizenBinding.passportNoEt.text.toString()
                    val passportTakenOn = addCitizenBinding.passportTakenEt.text.toString()
                    val passportValidTill = addCitizenBinding.passportValidEt.text.toString()
                    val genderSelected = addCitizenBinding.genderSpinner.selectedItem.toString()

                    val citizen = Citizen(
                        firstName,
                        lastName,
                        middleName,
                        region,
                        cityAddress,
                        homeAddress,
                        passportNo,
                        passportTakenOn,
                        passportValidTill,
                        genderSelected,
                        citizenImage!!
                    )

                    //TODO: Insert citizen to database
                    appDatabase.citizenDao().addCitizen(citizen)

                    Toast.makeText(container.context, "Muvaffaqiyatli Saqlandi", Toast.LENGTH_SHORT)
                        .show()
                    fragmentManager?.popBackStack()

                }
            })
            dialog.show()
        }

        return addCitizenBinding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var bit: Bitmap? = null

        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK) {
            selectedImageUri = data?.data!!

            try {
                bit = MediaStore.Images.Media.getBitmap(
                    activity?.contentResolver,
                    selectedImageUri
                ) as Bitmap
                addCitizenBinding.citizenImageOut.setImageBitmap(bit)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            addCitizenBinding.citizenImageOut.setImageBitmap(bit)
        }

        val byteArrayOutputStream = ByteArrayOutputStream()
        bit?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        citizenImage = byteArrayOutputStream.toByteArray()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddCitizenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCitizenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}