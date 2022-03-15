package com.otamurod.cameragallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider.getUriForFile
import com.otamurod.cameragallery.databinding.ActivityCameraBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    lateinit var binding: ActivityCameraBinding

    lateinit var currentImagePath: String
    val requestCode = 1
    lateinit var photoURI: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cameraOldBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.resolveActivity(packageManager)

            val photoFile = createImageFile()
            photoFile.also {
                val photoURI = getUriForFile(this, BuildConfig.APPLICATION_ID, it)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                startActivityForResult(intent, requestCode)

            }
        }

        binding.cameraNewBtn.setOnClickListener {
            val imageFile = createImageFile()
            photoURI = getUriForFile(this, BuildConfig.APPLICATION_ID, imageFile)

            getTakeImageContent.launch(photoURI)
        }

        binding.cameraDeleteBtn.setOnClickListener {

        }

    }


    private val getTakeImageContent =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {

            binding.cameraImage.setImageURI(photoURI)

            val openInputStream = contentResolver?.openInputStream(photoURI)
            val file = File(filesDir, "image.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileOutputStream)
            openInputStream?.close()
            fileOutputStream.close()
            val absolutePath = file.absolutePath

            Toast.makeText(this, absolutePath, Toast.LENGTH_SHORT).show()
        }

    @Throws(IOException::class)
    private fun createImageFile(): File {

        val format = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile("JPEG_$format", ".jpg", externalFilesDir).apply {
            currentImagePath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (::currentImagePath.isInitialized) {
            binding.cameraImage.setImageURI(Uri.fromFile(File(currentImagePath)))
        }
    }

}