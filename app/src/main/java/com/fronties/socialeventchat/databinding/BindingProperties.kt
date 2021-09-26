package com.fronties.socialeventchat.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BindingProperties @Inject constructor(
    val glide: RequestManager
) {
    @BindingAdapter("app:displayImage")
    fun displayImage(view: ImageView, urlThumbnail: String) {
        glide.load(urlThumbnail)
            .into(view)
    }
}