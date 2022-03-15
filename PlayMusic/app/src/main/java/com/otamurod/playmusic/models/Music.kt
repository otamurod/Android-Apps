package com.otamurod.playmusic.models

import android.graphics.Bitmap

class Music {

    var id: String? = null
    var title: String? = null
    var album: String? = null
    var artist: String? = null
    var duration: Long? = null
    var path: String? = null
    var artUri: String? = null

    constructor(
        id: String?,
        title: String?,
        album: String?,
        artist: String?,
        duration: Long?,
        pathC: String?,
        artUri: String?
    ) {
        this.id = id
        this.title = title
        this.album = album
        this.artist = artist
        this.duration = duration
        this.path = pathC
        this.artUri = artUri
    }
}
