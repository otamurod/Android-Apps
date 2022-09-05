package com.otamurod.simplepdfreader.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.otamurod.simplepdfreader.databinding.FragmentViewPdfBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPdfFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPdfFragment : Fragment() {
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

    lateinit var binding: FragmentViewPdfBinding
    private val PDF_SELECTION_CODE = 100
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPdfBinding.inflate(layoutInflater, container, false)

        binding.pdfView.maxZoom = 5f
        binding.pdfView.midZoom = 3f
        binding.pdfView.minZoom = 1f

        selectPdfFromStorage()

        return binding.root
    }

    private fun selectPdfFromStorage() {

        Toast.makeText(context, "Select PDF File", Toast.LENGTH_SHORT).show()

        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(browseStorage, "Select PDF"),
            PDF_SELECTION_CODE
        )
    }

    private fun showPdfFromUri(uri: Uri) {
        binding.pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedPdf = data.data!!

            showPdfFromUri(selectedPdf)
        }
    }
}