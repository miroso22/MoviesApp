package com.example.movies.ui.viewModel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.MovieItemBinding
import com.example.movies.ui.model.UIMovie

class MoviesAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MyListViewHolder>() {
    private var movies = mutableListOf<UIMovie>()

    class MyListViewHolder(itemView: View, val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private var _binding: MovieItemBinding? = null
        private val binding get() = _binding!!

        init {
            _binding = MovieItemBinding.bind(itemView)
            itemView.setOnClickListener {
                Log.v("cursor position", adapterPosition.toString())
                onClick(adapterPosition)
            }
        }

        fun bind(movie: UIMovie) {
            with(binding) {
                movieTtl.text = movie.title
                movieDesc.text = movie.description
                moviePoster.setImageBitmap(movie.bmp)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyListViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun submitList(newList: MutableList<UIMovie>) {
        MoviesListDiffCallback.configure(movies, newList)
        val calculateDiff = DiffUtil.calculateDiff(MoviesListDiffCallback, false)
        movies = newList
        calculateDiff.dispatchUpdatesTo(this)
    }
}

object MoviesListDiffCallback : DiffUtil.Callback() {
    private var oldList = emptyList<UIMovie>()
    private var newList = emptyList<UIMovie>()

    fun configure(oldList: List<UIMovie>, newList: List<UIMovie>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}