package com.lusosmile.main.ui.app.authentication.forgotpassword

import com.lusosmile.main.ui.base.callback.MvpView

interface ForgotPasswordMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}