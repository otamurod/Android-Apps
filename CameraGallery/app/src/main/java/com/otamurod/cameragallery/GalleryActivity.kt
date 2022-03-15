package com.otamurod.cameragallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.cameragallery.databinding.ActivityGalleryBinding
import com.otamurod.cameragallery.db.MyDbHelper
import com.otamurod.cameragallery.models.ImageModel
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class GalleryActivity : AppCompatActivity() {

    private val TAG = "GalleryActivity"
    lateinit var binding: ActivityGalleryBinding
    val REQUEST_CODE = 1

    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        binding.galleryOldBtn.setOnClickListener {

            pickImageFromGalleryOld()
        }

        binding.galleryNewBtn.setOnClickListener {

            pickImageFromGalleryNew()
        }

        binding.galleryDeleteBtn.setOnClickListener {

            clearImages()
        }

    }

    private fun pickImageFromGalleryOld() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun pickImageFromGalleryNew() {
        getImageContent.launch("image/*")
    }

    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            binding.galleryImage.setImageURI(uri)

            val openInputStream = contentResolver?.openInputStream(uri)
            val file = File(filesDir, "image.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileOutputStream)
            openInputStream?.close()
            fileOutputStream.close()
            val absolutePath = file.absolutePath
            val fileInputStream = FileInputStream(file)
            val readBytes = fileInputStream.readBytes()
            val imageModel = ImageModel(absolutePath, readBytes)

            myDbHelper.insertImage(imageModel)
            myDbHelper.getAllImages()
            Toast.makeText(this, absolutePath, Toast.LENGTH_SHORT).show()
        }

    private fun clearImages() {

        val filesDir = filesDir

        if (filesDir.isDirectory) {
            val listFiles = filesDir.listFiles()
            for (listFile in listFiles) {
                Log.d(TAG, "clearImages: ${listFile.absolutePath}")
                listFile.delete()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val uri = data?.data ?: return
            binding.galleryImage.setImageURI(uri)

            val openInputStream = contentResolver?.openInputStream(uri)
            val file = File(filesDir, "image.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileOutputStream)
            openInputStream?.close()
            fileOutputStream.close()
            val absolutePath = file.absolutePath

            Toast.makeText(this, absolutePath, Toast.LENGTH_SHORT).show()
        }
    }

}
