package com.fronties.socialeventchat.databinding

import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface BindingComponentBuilder {
    fun build(): BindingComponent
}
