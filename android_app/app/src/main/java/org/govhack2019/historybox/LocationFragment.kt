package org.govhack2019.historybox


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_location.locationNameTextView
import kotlinx.android.synthetic.main.fragment_location.photosRecyclerView
import kotlinx.android.synthetic.main.fragment_location.videosRecyclerView

/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment() {

    private val args: LocationFragmentArgs by navArgs()
    private var id: Long = -1
    private lateinit var location: Location
    private var imageUrls: List<String> = listOf()

    private var photosAdapter: PhotosAdapter? = null
    private var videosAdapter: VideosAdapter? = null

    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args.id
        location = viewModel.locations().first { it.id == id }
        imageUrls = viewModel.imageUrls(id = id)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerViews()
        locationNameTextView.text = location.name
    }

    private fun setupRecyclerViews() {
        val photosLayoutManager = LinearLayoutManager(context)
        photosLayoutManager.orientation = RecyclerView.HORIZONTAL
        photosRecyclerView.layoutManager = photosLayoutManager

        photosAdapter = PhotosAdapter(items = imageUrls)
        photosRecyclerView.adapter = photosAdapter

        val videosLayoutManager = LinearLayoutManager(context)
        videosLayoutManager.orientation = RecyclerView.HORIZONTAL
        videosRecyclerView.layoutManager = videosLayoutManager

        videosAdapter = VideosAdapter(items = imageUrls)
        videosRecyclerView.adapter = videosAdapter
    }
}
