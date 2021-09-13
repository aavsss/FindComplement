package com.fronties.socialeventchat.databinding

import androidx.databinding.DataBindingComponent
import com.fronties.socialeventchat.databinding.scope.BindingScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BindingScope
@DefineComponent(parent = SingletonComponent::class)
interface BindingComponent : DataBindingComponent