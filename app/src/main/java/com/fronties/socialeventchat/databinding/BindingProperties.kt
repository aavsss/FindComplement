package com.fronties.socialeventchat.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import com.bumptech.glide.RequestManager
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.helperClasses.dateTime.DateTimeUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BindingProperties @Inject constructor(
    private val glide: RequestManager,
    private val dateTimeUtils: DateTimeUtils
) {
    @BindingAdapter("app:displayImage")
    fun displayImage(view: ImageView, urlThumbnail: String?) {
        urlThumbnail?.let {
            glide.load(it)
                .centerCrop()
                .into(view)
        }
    }

    @BindingAdapter("app:displayFormattedDate")
    fun displayFormattedDate(view: TextView, isoDate: String?) {
        isoDate?.let {
            view.text = dateTimeUtils.getDateString(isoDate)
        }
    }

    @BindingAdapter("app:displayFormattedTime")
    fun displayFormattedTime(view: TextView, isoDate: String?) {
        isoDate?.let {
            view.text = dateTimeUtils.getTimeString(isoDate)
        }
    }

    @BindingAdapter("app:displayFormattedDateAndTime")
    fun displayFormattedDateAndTime(view: TextView, isoDate: String?) {
        isoDate?.let {
            view.text = dateTimeUtils.getDateAndTimeString(isoDate)
        }
    }
}
