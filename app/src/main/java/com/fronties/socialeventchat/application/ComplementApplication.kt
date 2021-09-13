package com.fronties.socialeventchat.application

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.fronties.socialeventchat.databinding.BindingComponentBuilder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class ComplementApplication : Application() {

    @Inject
    lateinit var bindingComponentBuilder: Provider<BindingComponentBuilder>

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(bindingComponentBuilder.get().build())
    }
}
