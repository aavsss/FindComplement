package com.fronties.socialeventchat.databinding.scope

import androidx.databinding.InverseMethod
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.event.dependency.sorting.SortOrder

/*
    This class is not used currently. It was intended to enforce sort order and sort type using
    2 way data binding. Currently, all the logic is inside dialogfragment
 */
object BindingUtils {

    @InverseMethod("transformButtonIdToSortOrder")
    fun transformSortOrderToButtonId(sortOrder: SortOrder?): Int {
        var selectedButtonId = -1

        sortOrder?.run {
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
