package com.itechsoftsolutions.tree.main.ui.app.others.contactus

import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface ContactUsMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}