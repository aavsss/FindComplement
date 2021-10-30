package com.fronties.socialeventchat.profile.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("profileImage")
fun loadProfileImage(iv: ImageView, uri: String?){
    Glide.with(iv)
        .load(uri)
        .into(iv)
}