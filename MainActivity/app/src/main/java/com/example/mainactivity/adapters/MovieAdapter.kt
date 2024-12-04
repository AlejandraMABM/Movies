package com.example.mainactivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.data.MovieName
import com.example.mainactivity.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter (var items: List<MovieName>, val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.render(movie)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }


    override fun getItemCount(): Int {
        return items.size

    }

    // como la lista se creo vacia hay q indicar que actualice por nuevas modificaciones
    fun updateItems(items: List<MovieName>) {
        this.items = items
        notifyDataSetChanged()
    }
}


class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun render(movie: MovieName) {
        binding.nameMovieTextView.text = movie.title
        binding.anoMovieTextView.text = movie.year

        println(" darme los nombres ${movie}")
        Picasso.get().load(movie.image).into(binding.posterImageView)
    }

}
