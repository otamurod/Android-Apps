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

package com.otamurod.quronikarim.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import com.otamurod.quronikarim.R
import com.otamurod.quronikarim.databinding.FragmentReadSurahBinding
import com.otamurod.quronikarim.models.Surahs
import com.otamurod.quronikarim.models.SurahsPageNumbers


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class ReadSurahFragment : Fragment() {
    private var position: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var getActivity: AppCompatActivity
    private lateinit var readSurahBinding: FragmentReadSurahBinding
    private lateinit var pdfView: PDFView

    @SuppressLint("ResourceType", "SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        readSurahBinding = FragmentReadSurahBinding.inflate(layoutInflater, container, false)

        getActivity = (activity as AppCompatActivity?)!!
        getActivity.supportActionBar!!.show() //show appbar/toolbar
        getActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)//set visible
        getActivity.supportActionBar!!.setHomeButtonEnabled(true)
        getActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back) //set navigation icon

        val surahs = Surahs()
        val listOfSurahs = surahs.getListOfSurahs()

        if (arguments != null) {
            position = arguments?.getInt("position") as Int
        }
        getActivity.supportActionBar!!.title = listOfSurahs[position!!] //change toolbar title

        pdfView = readSurahBinding.pdfView

        readSurahBinding.pdfView.maxZoom = 3f
        readSurahBinding.pdfView.midZoom = 2f
        readSurahBinding.pdfView.minZoom = 1f

        val surahsPageNumbers = SurahsPageNumbers()
        val pageNumbers = surahsPageNumbers.getPageNumbers()
        showPdfFromAssets(getPdfNameFromAssets(), pageNumbers[position!!])

        return readSurahBinding.root
    }

    private fun getPdfNameFromAssets(): String {
        return "full.pdf"
    }

    private fun showPdfFromAssets(pdfName: String, pageNumber: Int) {
        pdfView.fromAsset(pdfName)
            .password(null) // if password protected, then write password
            .defaultPage(pageNumber)// set the default page to open
            .onPageError { page, _ ->
                Toast.makeText(
                    context,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

}