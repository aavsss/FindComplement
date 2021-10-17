package com.fronties.socialeventchat.event.addEvent

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.application.DatePickerFragment
import com.fronties.socialeventchat.databinding.FragmentAddEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEventFragment : Fragment(R.layout.fragment_add_event) {

    private lateinit var binding: FragmentAddEventBinding
    private lateinit var viewModel: AddEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddEventViewModel::class.java)
        viewModel.initialSetup()
        binding.viewModel = viewModel
        viewModel.listenerForStartDatePictureStyle.observe(viewLifecycleOwner) {
            // Show Date Picker
            val datePickerFragment = DatePickerFragment { year: Int, month: Int, day: Int ->
                viewModel.setStartDate(year, month, day)
            }
            datePickerFragment.show(
                childFragmentManager, "datepicker"
            )
        }

        viewModel.listenerForEndDatePictureStyle.observe(viewLifecycleOwner) {
            // Show Date Picker
            val datePickerFragment = DatePickerFragment { year: Int, month: Int, day: Int ->
                viewModel.setEndDate(year, month, day)
            }
            datePickerFragment.show(
                childFragmentManager, "datepicker"
            )
        }

        viewModel.listenerForAddedEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }
}
