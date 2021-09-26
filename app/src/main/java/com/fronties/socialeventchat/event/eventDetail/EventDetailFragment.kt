package com.fronties.socialeventchat.event.eventDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentEventDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment(R.layout.fragment_event_detail) {

    lateinit var binding: FragmentEventDetailBinding
    lateinit var eventDetailViewModel: EventDetailViewModel
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = args.eventId

        eventDetailViewModel = ViewModelProvider(requireActivity())
            .get(EventDetailViewModel::class.java)
        eventDetailViewModel.getEventDetails(eventId)

        subscribeToEventDetail()
    }

    fun subscribeToEventDetail() {
        eventDetailViewModel.eventDetail.observe(viewLifecycleOwner) {
            binding.viewmodel = eventDetailViewModel
        }
    }
}
