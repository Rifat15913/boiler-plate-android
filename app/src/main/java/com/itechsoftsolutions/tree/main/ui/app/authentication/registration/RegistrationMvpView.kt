package com.itechsoftsolutions.tree.main.ui.app.authentication.registration

import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface RegistrationMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}