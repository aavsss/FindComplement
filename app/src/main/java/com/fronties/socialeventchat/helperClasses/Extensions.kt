package com.fronties.socialeventchat.helperClasses

import android.view.View

object Extensions {
    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.invisible() {
        this.visibility = View.INVISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}