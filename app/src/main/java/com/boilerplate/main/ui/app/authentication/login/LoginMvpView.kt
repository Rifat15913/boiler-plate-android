package com.boilerplate.main.ui.app.authentication.login

import com.boilerplate.main.ui.base.callback.MvpView

interface LoginMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}