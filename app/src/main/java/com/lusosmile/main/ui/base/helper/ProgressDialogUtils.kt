package com.lusosmile.main.ui.base.helper

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.itechsoftsolutions.lusosmile.databinding.ProgresssDialogLayoutBinding
import timber.log.Timber

class ProgressDialogUtils private constructor() {
    private var mAlertDialog: AlertDialog? = null

    fun showProgressDialog(context: Context): AlertDialog? {
        Timber.d((context as Activity).javaClass.simpleName)
        val builder = AlertDialog.Builder(context)
        val binding = ProgresssDialogLayoutBinding.inflate(LayoutInflater.from(context), null, false)
        binding.textViewMessage.setTypeface(null, Typeface.NORMAL)
        builder.setCancelable(true)
        builder.setView(binding.root)

        mAlertDialog = builder.create()
        mAlertDialog?.show()

        return mAlertDialog
    }

    fun hideProgressDialog() {
        if (mAlertDialog != null) {
            mAlertDialog!!.dismiss()
            mAlertDialog = null
        }
    }

    companion object {
        private var sInstance: ProgressDialogUtils? = null

        fun on(): ProgressDialogUtils {
            if (sInstance == null) {
                sInstance = ProgressDialogUtils()
            }

            return sInstance as ProgressDialogUtils
        }
    }
}
