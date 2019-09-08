package org.govhack2019.historybox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.setLogo(R.drawable.logo)
        actionBar?.setDisplayUseLogoEnabled(true)
    }
}
