package com.example.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter(val movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    ListAdapter<Movie, MyListAdapter.MyListViewHolder>(MovieDiffCallback) {

    class MyListViewHolder(itemView: View, val onClick: (Movie) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movieTtl)
        val description: TextView = itemView.findViewById(R.id.movieDesc)

        init {
            itemView.setOnClickListener {
                onClick(movies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MyListViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.title.text = movies[position].title
        holder.description.text = movies[position].description
    }

    override fun getItemCount() = movies.size
}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem.title == newItem.title
}