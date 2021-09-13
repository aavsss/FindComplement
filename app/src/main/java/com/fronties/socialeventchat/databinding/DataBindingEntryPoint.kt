package com.fronties.socialeventchat.databinding

import com.fronties.socialeventchat.databinding.scope.BindingScope
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@BindingScope
@InstallIn(BindingComponent::class)
interface DataBindingEntryPoint {

    @BindingScope
    fun getBindingProperties(): BindingProperties
}
