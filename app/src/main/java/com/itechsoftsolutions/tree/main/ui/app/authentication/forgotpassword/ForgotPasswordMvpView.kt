package com.itechsoftsolutions.tree.main.ui.app.authentication.forgotpassword

import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface ForgotPasswordMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}