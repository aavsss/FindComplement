package com.fronties.socialeventchat.event.myEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentMyEventBinding
import com.fronties.socialeventchat.event.eventList.EventListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyEventFragment : Fragment(R.layout.fragment_my_event) {

    private lateinit var binding: FragmentMyEventBinding
    private lateinit var viewModel: MyEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(MyEventViewModel::class.java)

        val adapter = EventListAdapter()

        binding.viewModel = viewModel
        binding.rvEventList.adapter = adapter

        viewModel.eventList.observe(viewLifecycleOwner) {
            it.data?.getContentIfNotHandled()?.let { list ->
                adapter.submitList(list)
            }
        }

        viewModel.getMyEventList()
    }
}
