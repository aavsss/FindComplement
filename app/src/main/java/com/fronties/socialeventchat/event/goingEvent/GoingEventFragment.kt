package com.fronties.socialeventchat.event.goingEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentGoingEventBinding
import com.fronties.socialeventchat.event.adapter.AttendingEventsAdapter
import com.fronties.socialeventchat.event.adapter.EventListAdapter
import com.fronties.socialeventchat.event.dependency.sorting.SortingDialogFragment
import com.fronties.socialeventchat.event.model.EventType
import com.fronties.socialeventchat.helperClasses.Extensions.gone
import com.fronties.socialeventchat.helperClasses.Extensions.visible
import com.fronties.socialeventchat.helperClasses.Status

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

        val adapter = AttendingEventsAdapter(EventType.ATTENDED)

        binding.viewModel = viewModel
        binding.rvEventList.adapter = adapter

        viewModel.eventList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visible()
                }
                Status.SUCCESS -> {
                    binding.progressBar.gone()
                    adapter.submitList(it.data)
                }
                Status.ERROR -> {
                    binding.progressBar.gone()
                    binding.clErrorView.visible()
                    binding.eventListViewCl.gone()
                }
            }
        }

        viewModel.listenerForSort.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val sortDialog = SortingDialogFragment(viewModel) { sortType, sortOrder ->
                    viewModel.sortEvents(sortType, sortOrder)
                }
                sortDialog.show(childFragmentManager, "sortDialog")
            }
        }

        viewModel.profilePic.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { uri ->
                binding.imgProfile.setImageURI(uri)
            }
        }

        viewModel.getGoingEventList()

        binding.svSearchView.setOnQueryTextListener(object:
                SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        viewModel.filterEvents(p0)
                        return false
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        return false
                    }
                }
        )
    }
}

