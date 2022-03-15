package com.otamurod.cameragallery.db

import com.otamurod.cameragallery.models.ImageModel

interface DbHelper {

    fun insertImage(imageModel: ImageModel)
    fun getAllImages():List<ImageModel>
}