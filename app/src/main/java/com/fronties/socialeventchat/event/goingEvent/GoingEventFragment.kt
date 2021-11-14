package com.fronties.socialeventchat.event.goingEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentGoingEventBinding
import com.fronties.socialeventchat.event.adapter.AttendingEventsAdapter
import com.fronties.socialeventchat.event.adapter.EventListAdapter

class GoingEventFragment : Fragment(R.layout.fragment_going_event) {

    private lateinit var binding: FragmentGoingEventBinding
    private lateinit var viewModel: GoingEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoingEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(GoingEventViewModel::class.java)
        viewModel.loadProfilePic()

        val adapter = AttendingEventsAdapter()

        binding.viewModel = viewModel
        binding.rvEventList.adapter = adapter

        viewModel.eventList.observe(viewLifecycleOwner) {
            it.data?.getContentIfNotHandled()?.let { list ->
                adapter.submitList(list)
            }
        }

        viewModel.profilePic.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { uri ->
                binding.imgProfile.setImageURI(uri)
            }
        }

        viewModel.getGoingEventList()
    }
}
