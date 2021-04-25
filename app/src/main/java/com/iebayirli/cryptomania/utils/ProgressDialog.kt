package com.iebayirli.cryptomania.utils

import android.R
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.iebayirli.cryptomania.databinding.ItemProgressDialogBinding

class ProgressDialog(
        private val context: Context,
) {

    private val dialog = Dialog(context, R.style.Theme_Black)
    private val view = ItemProgressDialogBinding.inflate(LayoutInflater.from(context))

    init {
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawableResource(com.iebayirli.cryptomania.R.color.blackTransparent)
            setContentView(view.root)
            setCancelable(false)
        }
    }

    fun showProgress() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismissProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }


}