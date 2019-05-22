package com.itechsoftsolutions.tree.main.ui.app.authentication.login

import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface LoginMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}