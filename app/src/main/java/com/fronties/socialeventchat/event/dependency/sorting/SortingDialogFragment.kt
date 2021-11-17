package com.fronties.socialeventchat.event.dependency.sorting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentSortBinding
import com.fronties.socialeventchat.event.EventViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingDialogFragment(
    private val eventViewModel: EventViewModel,
    private val sortCallback: (SortType, SortOrder) -> Unit,
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentSortBinding
    var sortType = SortType.NAME
    var sortOrder = SortOrder.ASC

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.eventViewModel = eventViewModel

        binding.btnSet.setOnClickListener {
            sortCallback(eventViewModel.sortType, eventViewModel.sortOrder)
            Log.i("sortType", eventViewModel.sortType.type)
            Log.i("sortOrder", eventViewModel.sortOrder.order)
            dismiss()
        }
    }
}
