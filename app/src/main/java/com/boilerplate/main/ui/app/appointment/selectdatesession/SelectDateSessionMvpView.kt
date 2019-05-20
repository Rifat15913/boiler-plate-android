package com.boilerplate.main.ui.app.appointment.selectdatesession

import com.boilerplate.main.ui.base.callback.MvpView

interface SelectDateSessionMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}