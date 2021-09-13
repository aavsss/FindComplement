package com.fronties.socialeventchat.databinding

import com.bumptech.glide.RequestManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BindingProperties @Inject constructor(
    val glide: RequestManager
) {
    // TODO: Inject dependencies in bindingAdapters here
}