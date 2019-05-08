package com.lusosmile.main.ui.app.authentication.registration

import com.lusosmile.main.ui.base.callback.MvpView

interface RegistrationMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}