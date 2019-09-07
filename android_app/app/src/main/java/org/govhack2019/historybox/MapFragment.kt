package org.govhack2019.historybox


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.MarkerManager
import kotlinx.android.synthetic.main.fragment_map.map_view
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment() {

    private lateinit var map: GoogleMap

    private lateinit var mapArea: LatLngBounds

    private lateinit var markerManager: MarkerManager

    private lateinit var markers: MarkerManager.Collection

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map_view.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.getMapAsync {

        }
    }
    private fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
//        markerManager = MarkerManager(googleMap)
//        markers = markerManager.newCollection()
        map.apply {
            setOnMapLoadedCallback { onMapLoaded() }
            setOnCameraIdleListener { onCameraIdle() }
            setOnMarkerClickListener { marker -> markerManager.onMarkerClick(marker) }
            setOnInfoWindowClickListener { marker -> markerManager.onInfoWindowClick(marker) }
        }
        markers.apply {
            setOnMarkerClickListener { marker -> onMarkerClick(marker = marker) }
            setOnInfoWindowClickListener { marker -> onInfoWindowClick(marker = marker) }
        }
        map.moveCamera(newLatLngBounds(mapArea, resources.getDimensionPixelSize(R.dimen.map_padding)))
    }

    private fun onMapLoaded() {
        Timber.d("onMapLoaded")
//        viewModel.observableViewState().observe(this, Observer { it -> render(viewState = it) })
    }

    private fun onCameraIdle() {
        Timber.d("onCameraIdle")
//        viewModel.onIntent(intent = SearchIntent.SetMapArea(bounds = map.projection.visibleRegion.latLngBounds))
    }

    private fun onMarkerClick(marker: Marker?): Boolean {
        Timber.d("onMarkerClick")
        marker?.also {
//            val drop = displayedDrops[it.tag]
//            analytics.logEvent(event = "marker_click", key = "drop_id", value = drop?.id?.value ?: "null")
//            drop?.also { }
        }
        return false
    }

    private fun onInfoWindowClick(marker: Marker?) {
        Timber.d("onInfoWindowClick")
        marker?.also {
//            val drop = displayedDrops[it.tag]
//            analytics.logEvent(event = "info_window_click", key = "drop_id", value = drop?.id?.value ?: "null")
//            drop?.also {
//                val navDirections = actionSearchMapFragmentToDropDetailsFragment(it.id.value)
//                Navigation.findNavController(map_view).navigate(navDirections)
//            }
        }
    }

    // NOTE: Calling lifecycle methods manually to get the map_view lifecycle events called
    // See https://stackoverflow.com/a/45785785/103421
    override fun onStart() {
        super.onStart()
        map_view.onStart()
    }

    override fun onResume() {
        super.onResume()
        map_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_view.onPause()
    }

    override fun onStop() {
        super.onStop()
        map_view.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_view.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map_view.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view.onLowMemory()
    }
}
