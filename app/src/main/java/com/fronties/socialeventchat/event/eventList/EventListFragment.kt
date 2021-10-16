package com.fronties.socialeventchat.event.eventList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentEventListBinding

class EventListFragment : Fragment(R.layout.fragment_event_detail) {

    private lateinit var binding: FragmentEventListBinding
    private lateinit var viewModel: EventListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(EventListViewModel::class.java)


        val adapter = EventListAdapter()
        viewModel.getEventList()        // setup event list in viewModel

        binding.rvEventList.adapter = adapter

        viewModel.eventList.observe(viewLifecycleOwner, {
            adapter.submitList(it.data)
        })

        // TODO: this is throwing lifecycle owner problems
//        subscribeToErrorView()
    }

    private fun subscribeToErrorView() {
        viewModel.errorViewListener.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                // handle error views
            }
        }
    }
}