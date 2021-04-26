package com.iebayirli.cryptomania.utils

import android.R
import android.app.Dialog
import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.Window
import com.iebayirli.cryptomania.databinding.ItemDescLayoutBinding

class DescDialog(
        private val context: Context,
) {

    private val dialog = Dialog(context, R.style.Theme_Black)
    private val view = ItemDescLayoutBinding.inflate(LayoutInflater.from(context))

    init {
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawableResource(com.iebayirli.cryptomania.R.color.blackTransparent)
            setContentView(view.root)
            setCancelable(false)
        }
        view.tvCoinDesc.movementMethod = ScrollingMovementMethod()

        view.btnClose.setOnClickListener {
            dismissProgress()
        }
    }

    fun showProgress(message: String) {
        if (!dialog.isShowing) {
            view.tvCoinDesc.text = message
            dialog.show()
        }
    }

    fun dismissProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }


}