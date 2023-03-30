package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import co.com.monkeymobile.itunes_searcher.databinding.AlbumAdapterBinding
import co.com.monkeymobile.itunes_searcher.domain.model.Album
import com.bumptech.glide.Glide

class AlbumAdapter(private val listener: AlbumItemListener) :
    ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(
        DiffCallback()
    ) {

    private class DiffCallback : ItemCallback<Album>() {

        override fun areItemsTheSame(oldItem: Album, newItem: Album) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Album, newItem: Album) = oldItem == newItem
    }

    interface AlbumItemListener {

        fun onAlbumClicked(album: Album)
    }

    class AlbumViewHolder(private val binding: AlbumAdapterBinding) : ViewHolder(binding.root) {

        fun bind(album: Album, listener: AlbumItemListener) {
            val context = binding.root.context

            Glide.with(context).load(album.artWork).into(binding.artwork)
            binding.albumName.text = album.albumName
            binding.releaseDate.text = album.releaseDate

            binding.root.setOnClickListener { listener.onAlbumClicked(album) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumViewHolder(
        AlbumAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}