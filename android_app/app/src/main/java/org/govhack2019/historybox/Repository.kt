package org.govhack2019.historybox

import java.util.regex.Pattern

object Repository {

    private val locations: ArrayList<Location> = ArrayList()
    private val imageUrls: ArrayList<PhotoData> = ArrayList()
    private val videoUrls: ArrayList<VideoData> = ArrayList()

    init {
        loadLocations()
        loadImageUrls()
        loadVideoUrls()
    }

    fun locations(): List<Location> = locations

    fun imageUrls(id: Long): List<PhotoData> {
        return imageUrls.filter { it.id == id }
    }

    fun videoData(id: Long): List<VideoData> {
        return videoUrls.filter { it.id == id }
    }

    private fun loadLocations() {
        OfflineData.locations
            .split(Pattern.compile("\n"))
            .forEach { it ->
                val values = it.split(Pattern.compile(","))
                locations.add(
                    Location(
                        id = values[0].toLong(),
                        name = values[1],
                        latitude = values[2].toDouble(),
                        longitude = values[3].toDouble(),
                        keywords = values[4]
                    )
                )
            }
    }

    private fun loadImageUrls() {
        OfflineData.imageUrls
            .split(Pattern.compile("\n"))
            .forEach { it ->
                val values = it.split(Pattern.compile(","))
                imageUrls.add(PhotoData(id = values[0].toLong(), photoUrl = values[1]))
            }
    }

    private fun loadVideoUrls() {
        OfflineData.videoUrls
            .split(Pattern.compile("\n"))
            .forEach { it ->
                val values = it.split(Pattern.compile(","))
                videoUrls.add(VideoData(
                    id = values[0].toLong(),
                    thumbnailUrl = values[1],
                    videoUrl = values[2]
                ))
            }
    }
}
