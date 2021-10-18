package com.fronties.socialeventchat.application.dialogs


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.fronties.socialeventchat.R

class CustomDialog(message: String): DialogFragment() {

    val errorMessage = "$message error!"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(errorMessage)
            .setPositiveButton(getString(R.string.ok)) { _,_ -> }
            .create()

}