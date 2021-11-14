package com.fronties.socialeventchat.event.dependency.sorting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fronties.socialeventchat.databinding.FragmentSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingDialogFragment(
    private val sortType: (SortType) -> Unit
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentSortBinding

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
        binding.sortName.setOnClickListener {
            sortType(SortType.NAME)
            dismiss()
        }

        binding.sortHostname.setOnClickListener {
            sortType(SortType.HOSTNAME)
            dismiss()
        }
    }
}
