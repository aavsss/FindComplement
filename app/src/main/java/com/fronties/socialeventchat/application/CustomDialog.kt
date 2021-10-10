package com.fronties.socialeventchat.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.fronties.socialeventchat.R

class CustomDialog(message: String): DialogFragment() {

    val errorMessage = "$message error!"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.error_screen_dialog,container,false)

        val errorTitleTv = rootView.findViewById<TextView>(R.id.error_title_tv)
        errorTitleTv.text = errorMessage

        val okBtn: Button = rootView.findViewById(R.id.error_ok_btn)
        okBtn.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        return rootView
    }
}