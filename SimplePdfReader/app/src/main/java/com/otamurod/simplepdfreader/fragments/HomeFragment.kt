package com.otamurod.simplepdfreader.fragments

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.otamurod.simplepdfreader.R
import com.otamurod.simplepdfreader.adapters.BookAdapter
import com.otamurod.simplepdfreader.databinding.FragmentHomeBinding
import java.io.File


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

    lateinit var binding: FragmentHomeBinding
    var pdf: ArrayList<File>? = null //declare globally or as per your need
    lateinit var bookAdapter: BookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // adding on click listener for button
        binding.idBtnExtract.setOnClickListener(View.OnClickListener { // calling method to extract
            // data from PDF file.
            extractPDF()
        })

        binding.showBtn.setOnClickListener {
            findNavController().navigate(R.id.viewPdfFragment)
        }

        return binding.root
    }

    private fun extractPDF() {
        try {
            // creating a string for
            // storing our extracted text.
            var extractedText = ""

            // creating a variable for pdf reader
            // and passing our PDF file in it.
            val reader = PdfReader("res/raw/cn.pdf")

            // below line is for getting number
            // of pages of PDF file.
            val n = reader.numberOfPages

            // running a for loop to get the data from PDF
            // we are storing that data inside our string.
            for (i in 0 until n) {
                extractedText = """
                $extractedText${
                    PdfTextExtractor.getTextFromPage(reader, i + 1).trim { it <= ' ' }
                }
                
                """.trimIndent()
                // to extract the PDF content from the different pages
            }

            // after extracting all the data we are
            // setting that string value to our text view.

            //TODO: Translate extracted text and then set

            binding.idPDFTV.text = extractedText

            // below line is used for closing reader.
            reader.close()
        } catch (e: Exception) {
            // for handling error while extracting the text file.
            binding.idPDFTV.text = "Error found is : \n$e"
        }
    }

/*

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun getPdfList(): List<File?>? {
        if (pdf == null) { //check for null
            pdf = ArrayList() //initialise it on your onCreate method
        }

        val collection: Uri
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.MIME_TYPE
        )
        val sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        val selection = MediaStore.Files.FileColumns.MIME_TYPE + " = ?"
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")
        val selectionArgs = arrayOf(mimeType)
        collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }
        context!!.applicationContext.contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        ).use { cursor ->
            assert(cursor != null)
            if (cursor!!.moveToFirst()) {
                val columnData = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                val columnName = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
                do {
                    val path = cursor.getString(columnData)
                    val file = File(path)
                    if (file.exists()) {
                        //you can get your pdf files
                        pdf!!.add(File(cursor.getString(columnData)))
                        Log.d(TAG, "getPdf: " + cursor.getString(columnData))
                    }
                } while (cursor.moveToNext())
            }
        }
        return pdf
    }
*/

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