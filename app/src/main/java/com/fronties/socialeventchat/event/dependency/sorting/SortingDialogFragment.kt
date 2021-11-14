package com.fronties.socialeventchat.event.dependency.sorting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fronties.socialeventchat.databinding.FragmentSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingDialogFragment(
    private val sortCallback: (SortType, SortOrder) -> Unit
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

        // region temporary to see if it is working 
        binding.sortName.setOnClickListener {
            sortType = SortType.NAME
        }

        binding.sortHostname.setOnClickListener {
            sortType = SortType.HOSTNAME
        }

        binding.sortAsc.setOnClickListener {
            sortOrder = SortOrder.ASC
        }

        binding.sortDsc.setOnClickListener {
            sortOrder = SortOrder.DESC
        }
        // endregion

        binding.btnSet.setOnClickListener {
            sortCallback(sortType, sortOrder)
            dismiss()
        }
    }
}
