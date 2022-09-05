package com.otamurod.retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.retrofit.databinding.ItemMovieBinding
import com.otamurod.retrofit.models.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(var list: List<Movie>) : RecyclerView.Adapter<MovieAdapter.VH>() {

    inner class VH(var itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun onBind(movie: Movie) {
            itemMovieBinding.tv.text = movie.name
            Picasso.get().load(movie.imageurl).into(itemMovieBinding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}