package org.govhack2019.historybox

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var imageView = itemView.findViewById<ImageView>(R.id.imageTileView)

    fun bindToUrl(url: String) {
        Glide.with(itemView)
            .load(url)
            .fitCenter()
            .into(imageView)
    }

}
