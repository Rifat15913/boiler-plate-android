package com.lusosmile.main.ui.base.helper

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import timber.log.Timber

/**
 * This is a class that contains utils for alert dialogs
 * @author Mohd. Asfaq-E-Azam Rifat
 * */
class AlertDialogUtils private constructor() {
    private var mAlertDialog: AlertDialog? = null

    fun showDialog(context: Context, message: String, positiveButtonText: String,
                   negativeButtonText: String,
                   positiveButtonListener: DialogInterface.OnClickListener?,
                   negativeButtonListener: DialogInterface.OnClickListener?): AlertDialog? {

        Timber.d((context as Activity).javaClass.simpleName)
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButtonText, positiveButtonListener)
        builder.setNegativeButton(negativeButtonText, negativeButtonListener)
        mAlertDialog = builder.create()
        mAlertDialog?.show()

        return mAlertDialog
    }

    fun hideDialog() {
        if (mAlertDialog != null) {
            mAlertDialog!!.dismiss()
            mAlertDialog = null
        }
    }

    companion object {
        private var sInstance: AlertDialogUtils? = null

        fun on(): AlertDialogUtils {
            if (sInstance == null) {
                sInstance = AlertDialogUtils()
            }

            return sInstance as AlertDialogUtils
        }
    }
}
