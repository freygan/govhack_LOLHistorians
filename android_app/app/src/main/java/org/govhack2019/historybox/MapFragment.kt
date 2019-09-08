package org.govhack2019.historybox


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.MarkerManager
import kotlinx.android.synthetic.main.fragment_map.map_view
import org.govhack2019.historybox.MapFragmentDirections.Companion.actionMapFragmentToLocationFragment
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment() {

    private lateinit var map: GoogleMap

//    private var mapArea: LatLngBounds = LatLngBounds(LatLng(-43.75, 144.5), LatLng(-39.65, 148.6))
    private val defaultMapArea: LatLngBounds = LatLngBounds(LatLng(-41.460828, 147.097281), LatLng(-41.430178, 147.138681))
    private var mapArea: LatLngBounds = defaultMapArea
    private lateinit var markerManager: MarkerManager

    private lateinit var markers: MarkerManager.Collection

    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        map_view.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        map_view.getMapAsync { googleMap -> onMapReady(googleMap = googleMap) }
    }

    private fun onMapReady(googleMap: GoogleMap) {
        Timber.d("onMapReady")
        map = googleMap
        markerManager = MarkerManager(googleMap)
        markers = markerManager.newCollection()
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
        loadMarkers()
    }

    private fun onMapLoaded() {
        Timber.d("onMapLoaded")
        map.moveCamera(newLatLngBounds(defaultMapArea, resources.getDimensionPixelSize(R.dimen.map_padding)))
    }

    private fun onCameraIdle() {
        Timber.d("onCameraIdle")
        mapArea = map.projection.visibleRegion.latLngBounds
    }

    private fun loadMarkers() {
        Timber.d("loadMarkers")
        viewModel.locations()
            .forEach { loc: Location ->
                val options = MarkerOptions().run {
                    title(loc.name)
                    position(LatLng(loc.latitude, loc.longitude))
//                    snippet(loc.keywords)
                }
                val marker = markers.addMarker(options)
                marker.tag = loc.id
            }
    }

    private fun onMarkerClick(marker: Marker?): Boolean {
        Timber.d("onMarkerClick")
        marker?.also {}
        return false
    }

    private fun onInfoWindowClick(marker: Marker?) {
        Timber.d("onInfoWindowClick")
        marker?.also {
            val id = it.tag.toString().toLong()
            val navDirections = actionMapFragmentToLocationFragment(id)
            Navigation.findNavController(map_view).navigate(navDirections)
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
//        map_view.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        map_view.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view.onLowMemory()
    }
}
