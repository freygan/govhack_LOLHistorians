package org.govhack2019.historybox

import java.util.regex.Pattern

object Repository {

    private val locations: ArrayList<Location> = ArrayList()
    private val imageUrls: ArrayList<Pair<Long, String>> = ArrayList()

    init {
        loadLocations()
        loadImageUrls()
    }

    fun locations(): List<Location> = locations

    fun imageUrls(id: Long): List<String> {
        val result = ArrayList<String>()
        imageUrls.filter { it.first == id }
            .forEach { result.add(it.second) }
        return result
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
                imageUrls.add(Pair(values[0].toLong(), values[1]))
            }
    }
}
