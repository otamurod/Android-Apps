package com.otamurod.cameragallery

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.otamurod.cameragallery.databinding.ActivityMainBinding
import com.otamurod.cameragallery.db.MyDbHelper


class MainActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        val allImages = myDbHelper.getAllImages()

        binding.pathImage.setImageURI(Uri.parse(allImages[0].imagePath))

        val bitmap =
            BitmapFactory.decodeByteArray(allImages[0].image, 0, allImages[0].image?.size!!)
        binding.byteImage.setImageBitmap(bitmap)


        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {  //first time

                }

                override fun onPermissionRationaleShouldBeShown(  //second time
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) {
                }
            }).check()

        binding.galleryBtn.setOnClickListener {

            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        binding.cameraBtn.setOnClickListener {

            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

    }
}