package com.lusosmile.main.ui.app.others.contactus

import com.lusosmile.main.ui.base.callback.MvpView

interface ContactUsMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}