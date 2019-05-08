package com.lusosmile.main.ui.app.authentication.login

import com.lusosmile.main.ui.base.callback.MvpView

interface LoginMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}