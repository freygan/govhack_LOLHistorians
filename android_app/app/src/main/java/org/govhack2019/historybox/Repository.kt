package org.govhack2019.historybox

import timber.log.Timber
import java.util.regex.Pattern

object Repository {

    private var locations: ArrayList<Location> = ArrayList()
    private val locationText = """1,City Park Monkey Enclosure,-41.433277,147.142251,launceston monkey city park
2,James Boags,-41.432573,147.138516,launceston brewery -cascade
3,Launceston Town Hall,-41.435558,147.13738,launceston town hall
4,Launceston Post Office,-41.435503,147.137774,"launceston, post office"
5,Lonnies Night Club,-41.437112,147.139251,"launceston, l"
6,City Park John Hart Conservatory,-41.432727,147.142734,"launceston, "
7,Royal Park,-41.437176,147.131785,"launceston, "
8,Princess Theatre,-41.435994,147.141053,"launceston, "
9,CH Smith Building,-41.43566,147.13495,"launceston, "
10,Government Offices,-41.436165,147.13807,"launceston, "
11,National Theatre (Foot and Playsted),-41.437044,147.136512,"launceston, "
12,Albert Hall,-41.433063,147.14128,"launceston, "
13,The Silos,-41.431903,147.128238,"launceston, "
14,Duncan House,-41.435455,147.141625,"launceston, "
15,Holy Trinity Church,-41.434374,147.139654,"launceston, "
16,Customs House,-41.432405,147.134791,"launceston, "
17,Fire Station,-41.437958,147.135249,"launceston, "
18,Macquarie House,-41.436254,147.136144,"launceston, "
19,The Royal Oak,-41.43452,147.142441,"launceston, "
"""

    init {
        loadLocations()
    }

    fun locations(): List<Location> = locations

    private fun loadLocations() {
        locationText.split(Pattern.compile("\n"))
            .forEach { it ->
                val values = it.split(Pattern.compile(","))
                try {
                    locations.add(
                        Location(
                            id = values[0].toLong(),
                            name = values[1],
                            latitude = values[2].toDouble(),
                            longitude = values[3].toDouble(),
                            keywords = values[4]
                        )
                    )
                } catch (e: Exception) {
                    Timber.e(e, "loadLocations")
                }
            }
    }
}
