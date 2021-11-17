package com.fronties.socialeventchat.databinding

import androidx.databinding.InverseMethod
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.event.dependency.sorting.SortType

object BindingUtils {

    @InverseMethod("transformButtonIdToSortType")
    fun transformSortTypeToButtonId(sortType: SortType): Int {
        var selectedButtonId = -1
        sortType.run {
            selectedButtonId = when (this) {
                SortType.NAME -> R.id.rb_name
                SortType.HOSTNAME -> R.id.rb_hostname
                SortType.LOCATION -> R.id.rb_location
            }
        }
        return selectedButtonId
    }

    fun transformButtonIdToSortType(selectedButtonId: Int): SortType {
        var sortType: SortType = SortType.NAME
        when (selectedButtonId) {
            R.id.rb_name -> sortType = SortType.NAME
            R.id.rb_hostname -> sortType = SortType.HOSTNAME
            R.id.rb_location -> sortType = SortType.LOCATION
        }
        return sortType
    }

    @InverseMethod("transformButtonIdToSortOrder")
    fun transformSortOrderToButtonId(sortOrder: SortOrder): Int {
        var selectedButtonId = -1

        sortOrder.run {
            selectedButtonId = when (this) {
                SortOrder.ASC -> R.id.rb_asc
                SortOrder.DESC -> R.id.rb_dsc
            }
        }

        return selectedButtonId
    }

    fun transformButtonIdToSortOrder(selectedButtonId: Int): SortOrder {
        var sortOrder: SortOrder = SortOrder.ASC
        when (selectedButtonId) {
            R.id.rb_asc -> sortOrder = SortOrder.ASC
            R.id.rb_dsc -> sortOrder = SortOrder.DESC
        }
        return sortOrder
    }
}
