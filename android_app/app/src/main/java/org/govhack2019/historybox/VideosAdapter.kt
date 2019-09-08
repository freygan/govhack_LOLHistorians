package org.govhack2019.historybox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VideosAdapter(var items: List<VideoData>): RecyclerView.Adapter<VideosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_tile, parent, false)
        return VideosViewHolder(itemView = view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bindToUrl(items[position].thumbnailUrl)
    }
}
