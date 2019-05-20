package com.boilerplate.main.ui.app.others.contactus

import com.boilerplate.main.ui.base.callback.MvpView

interface ContactUsMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}