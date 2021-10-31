package com.fronties.socialeventchat.event.eventList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentEventListBinding
import com.fronties.socialeventchat.helperClasses.Extensions.gone
import com.fronties.socialeventchat.helperClasses.Extensions.visible

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
        viewModel.getEventList() // setup event list in viewModel

        binding.viewModel = viewModel
        binding.rvEventList.adapter = adapter

        viewModel.eventList.observe(viewLifecycleOwner, {
            adapter.submitList(it.data)
        })

        viewModel.navToAddEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.action_eventListFragment_to_addEventFragment
                )
            }
        }

        subscribeToErrorView()
    }

    private fun subscribeToErrorView() {
        viewModel.errorViewListener.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled().let {
                binding.clErrorView.visible()
                binding.eventListViewCl.gone()
            }
        })
    }
}
