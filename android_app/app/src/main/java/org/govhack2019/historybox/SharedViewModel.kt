package org.govhack2019.historybox

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    fun locations(): List<Location> = Repository.locations()

    fun imageUrls(id: Long): List<PhotoData> = Repository.imageUrls(id = id)

    fun videoData(id: Long): List<VideoData> = Repository.videoData(id = id)

}
