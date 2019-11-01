package com.itechsoftsolutions.tree.main.ui.base.helper

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.itechsoftsolutions.tree.R
import kotlinx.android.synthetic.main.progresss_dialog_layout.view.*

/**
 * This is a class that contains utils for progress dialogs
 * @author Mohd. Asfaq-E-Azam Rifat
 * */
class ProgressDialogUtils private constructor() {
    private var mAlertDialog: AlertDialog? = null

    /**
     * This method shows a progress dialog
     * @param context current UI context
     * @return created [AlertDialog]
     * */
    fun showProgressDialog(context: Context): AlertDialog? {
        val builder = AlertDialog.Builder(context)

        LayoutInflater.from(context)
                .inflate(
                        R.layout.progresss_dialog_layout,
                        null,
                        false
                )?.let {
                    it.text_view_message?.setTypeface(null, Typeface.NORMAL)
                    builder.setCancelable(true)
                    builder.setView(it)

                    mAlertDialog = builder.create()
                    mAlertDialog?.show()
                }

        return mAlertDialog
    }

    /**
     * This method hides the progress dialog (if any visible alert dialog is found)
     * */
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
