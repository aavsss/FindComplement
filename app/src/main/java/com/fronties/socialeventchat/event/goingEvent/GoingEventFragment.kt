package com.fronties.socialeventchat.event.goingEvent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.chat.ui.ChatActivity
import com.fronties.socialeventchat.databinding.FragmentGoingEventBinding
import com.fronties.socialeventchat.event.adapter.GoingEventsAdapter
import com.fronties.socialeventchat.event.dependency.sorting.SortingDialogFragment
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

        val adapter = GoingEventsAdapter(viewModel)

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
                    viewModel.sortAttendedEvents(sortType, sortOrder)
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

        viewModel.listenerForNavToChat.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { eid ->
                val intent = Intent(activity, ChatActivity::class.java)
                intent.putExtra("eid", eid)
                startActivity(intent)
            }
        }
    }
}
