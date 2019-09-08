package org.govhack2019.historybox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PhotosAdapter(var items: List<String>): RecyclerView.Adapter<PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_tile, parent, false)
        return PhotosViewHolder(itemView = view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindToUrl(items[position])
    }
}
