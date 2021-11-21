package com.fronties.socialeventchat.event.eventList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentEventListBinding
import com.fronties.socialeventchat.event.adapter.EventListAdapter
import com.fronties.socialeventchat.event.dependency.sorting.SortingDialogFragment
import com.fronties.socialeventchat.helperClasses.Extensions.gone
import com.fronties.socialeventchat.helperClasses.Extensions.visible
import com.fronties.socialeventchat.helperClasses.Status

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

        val adapter = EventListAdapter(
            viewModel,
        )
        viewModel.getEventList() // setup event list in viewModel
        viewModel.loadProfilePic()

        binding.viewModel = viewModel
        binding.rvEventList.adapter = adapter

        viewModel.eventList.observe(viewLifecycleOwner, {
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
                    showErrorView()
                }
            }
        })

        viewModel.navToAddEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.action_eventListFragment_to_addEventFragment
                )
            }
        }

        viewModel.profilePic.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { uri ->
                binding.imgProfile.setImageURI(uri)
            }
        }

        viewModel.listenerForSort.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val sortDialog = SortingDialogFragment(viewModel) { sortType, sortOrder ->
                    viewModel.sortUnattendedEvents(sortType, sortOrder)
                }
                sortDialog.show(childFragmentManager, "sortDialog")
            }
        }

        binding.svSearchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })

        subscribeToErrorView()
    }

    private fun subscribeToErrorView() {
        viewModel.errorViewListener.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled().let {
                showErrorView()
            }
        })
    }

    private fun showErrorView() {
        binding.clErrorView.visible()
        binding.eventListViewCl.gone()
    }
}
