package org.govhack2019.historybox

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    fun locations(): List<Location> = Repository.locations()

}
