package com.fronties.socialeventchat.event.dependency.sorting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fronties.socialeventchat.R
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

        binding.btnSet.setOnClickListener {
            when (binding.sortTypeRg.checkedRadioButtonId) {
                R.id.rb_name -> sortType = SortType.NAME
                R.id.rb_hostname -> sortType = SortType.HOSTNAME
                R.id.rb_location -> sortType = SortType.LOCATION
            }
            when (binding.sortOrderRg.checkedRadioButtonId) {
                R.id.rb_asc -> sortOrder = SortOrder.ASC
                R.id.rb_dsc -> sortOrder = SortOrder.DESC
            }
            sortCallback(sortType, sortOrder)
            dismiss()
        }
    }
}
