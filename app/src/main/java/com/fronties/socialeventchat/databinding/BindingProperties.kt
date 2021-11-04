package com.fronties.socialeventchat.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BindingProperties @Inject constructor(
    private val glide: RequestManager
) {
    @BindingAdapter("app:displayImage")
    fun displayImage(view: ImageView, urlThumbnail: String?) {
        urlThumbnail?.let {
            glide.load(it)
                .centerCrop()
                .into(view)
        }
    }
}
