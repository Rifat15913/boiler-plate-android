package com.boilerplate.main.ui.app.authentication.forgotpassword

import com.boilerplate.main.ui.base.callback.MvpView

interface ForgotPasswordMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}