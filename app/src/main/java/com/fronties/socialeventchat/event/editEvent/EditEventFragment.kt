package com.fronties.socialeventchat.event.editEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.application.dialogs.CustomDialog
import com.fronties.socialeventchat.application.dialogs.DatePickerFragment
import com.fronties.socialeventchat.application.dialogs.TimePickerFragment
import com.fronties.socialeventchat.databinding.FragmentAddEventBinding
import com.fronties.socialeventchat.databinding.FragmentEditEventBinding
import com.fronties.socialeventchat.event.addEvent.AddEventViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditEventFragment : Fragment(R.layout.fragment_edit_event) {

    private lateinit var binding: FragmentEditEventBinding
    private lateinit var viewModel: EditEventViewModel
    private val args: EditEventFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditEventViewModel::class.java)
        viewModel.initialSetup(args.eventId)
        binding.viewModel = viewModel
        viewModel.listenerForStartDatePictureStyle.observe(viewLifecycleOwner) {
            // Show Date Picker
            val datePickerFragment = DatePickerFragment { year: Int, month: Int, day: Int ->
                viewModel.setStartDate(year, month, day)
                binding.eventStartDate.text = "$year/$month/$day" // TODO: Rewrite this, I hate this
            }
            datePickerFragment.show(
                childFragmentManager, "datepicker"
            )
        }

        viewModel.listenerForStartTimePictureStyle.observe(viewLifecycleOwner) {
            val timePickerFragment = TimePickerFragment { hourOfDay: Int, minute: Int ->
                viewModel.setStartTime(hourOfDay, minute)
                binding.eventStartTime.text = "$hourOfDay:$minute"
            }
            timePickerFragment.show(
                childFragmentManager, "timepicker"
            )
        }

        viewModel.listenerForEndDatePictureStyle.observe(viewLifecycleOwner) {
            // Show Date Picker
            val datePickerFragment = DatePickerFragment { year: Int, month: Int, day: Int ->
                viewModel.setEndDate(year, month, day)
                binding.eventEndDate.text = "$year/$month/$day" // TODO: Can do this with 2 way data binding
            }
            datePickerFragment.show(
                childFragmentManager, "datepicker"
            )
        }

        viewModel.listenerForEndTimePictureStyle.observe(viewLifecycleOwner) {
            val timePickerFragment = TimePickerFragment { hourOfDay: Int, minute: Int ->
                viewModel.setEndTime(hourOfDay, minute)
                binding.eventEndTime.text = "$hourOfDay:$minute"
            }
            timePickerFragment.show(
                childFragmentManager, "timepicker"
            )
        }

        viewModel.listenerForAddedEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        viewModel.listenerForError.observe(viewLifecycleOwner) {
            val errorDialog = CustomDialog(it.getContentIfNotHandled() ?: "Error") // TODO string resource
            errorDialog.show(
                childFragmentManager, "Error Dialog"
            )
        }
    }
}
