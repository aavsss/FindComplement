package com.fronties.socialeventchat.event.eventDetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.chat.ui.ChatActivity
import com.fronties.socialeventchat.databinding.FragmentEventDetailBinding
import com.fronties.socialeventchat.event.model.EventType
import com.fronties.socialeventchat.helperClasses.Extensions.gone
import com.fronties.socialeventchat.helperClasses.Extensions.visible
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
        val isAttending = args.isAttending
        val isHost = args.isHost
        eventDetailViewModel = ViewModelProvider(requireActivity())
            .get(EventDetailViewModel::class.java)
        eventDetailViewModel.setIsHostTo(isHost)
        eventDetailViewModel.getEventDetails(eventId)

        binding.btnAttendEvent.setOnClickListener {
            eventDetailViewModel.attendEvent(eventId)
        }

        showChatButtonIfAttending(isAttending)
        subscribeToEventDetail()
        subscribeToNavToChat()
        subscribeToErrorView()
        subscribeToAttendEvent()
        subscribeToNavBack()
        subscribeToShowEdit()
        subscribeToNavToEditEvent()
    }

    private fun subscribeToErrorView() {
        eventDetailViewModel.errorViewListener.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                binding.clErrorView.visible()
                binding.clInfoView.gone()
            }
        }
    }

    private fun subscribeToEventDetail() {
        eventDetailViewModel.eventDetail.observe(viewLifecycleOwner) {
            binding.viewmodel = eventDetailViewModel
        }
    }

    private fun subscribeToNavToChat() {
        eventDetailViewModel.navToChat.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { eid ->
                // navigate to chat screen
                val intent = Intent(activity, ChatActivity::class.java)
                intent.putExtra("eid", eid)
                startActivity(intent)
            }
        }
    }

    private fun subscribeToAttendEvent() {
        eventDetailViewModel.attendSuccess.observe(viewLifecycleOwner) {
            if (it.data == true) {
                Toast.makeText(context, "Attending event!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Not attending event :(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showChatButtonIfAttending(isAttending: Int) {
//        if (isAttending == EventType.ATTENDED.value) {
        if (true) {
            binding.btnChat.visible()
            binding.btnAttendEvent.gone()
        }
    }

    private fun subscribeToNavBack() {
        eventDetailViewModel.navBack.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().popBackStack()
            }
        }
    }

    private fun subscribeToShowEdit() {
        eventDetailViewModel.isHost.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnEditEvent.visible()
            } else {
                binding.btnEditEvent.gone()
            }
        }
    }

    private fun subscribeToNavToEditEvent() {
        eventDetailViewModel.navToEditEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { eid ->
                val bundle = bundleOf(
                    "eventId" to eid
                )
                findNavController().navigate(
                    R.id.action_eventDetailFragment_to_editEventFragment,
                    bundle
                )
            }
        }
    }
}
