package com.otamurod.playmusic.adapters

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.otamurod.playmusic.R
import com.otamurod.playmusic.databinding.ItemMusicBinding
import com.otamurod.playmusic.models.Music

class MusicAdapter(val context: Context, var list: List<Music>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<MusicAdapter.VH>() {

    inner class VH(var itemMusicBinding:ItemMusicBinding) :RecyclerView.ViewHolder(itemMusicBinding.root){

        @RequiresApi(Build.VERSION_CODES.Q)
        fun onBind(music: Music, clickListener: OnItemClickListener, position: Int) {

            val image = music.artUri
            Glide.with(context)
                .asBitmap()
                .load(image)
                .placeholder(R.drawable.music_bg)
                .into(itemMusicBinding.musicImage)

//            itemMusicBinding.musicImage.setImageURI(Uri.parse(image))

            itemMusicBinding.mediaArtist.text = music.artist
            itemMusicBinding.mediaTitle.text = music.title

            itemMusicBinding.root.setOnClickListener{
                clickListener.onItemClicked(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):VH {
        val itemMusicBinding = ItemMusicBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return VH(itemMusicBinding)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: MusicAdapter.VH, position: Int) {
        holder.onBind(list[position], itemClickListener, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface OnItemClickListener {
    fun onItemClicked(position: Int)
}