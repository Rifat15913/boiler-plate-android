package com.boilerplate.main.ui.app.authentication.registration

import com.boilerplate.main.ui.base.callback.MvpView

interface RegistrationMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}