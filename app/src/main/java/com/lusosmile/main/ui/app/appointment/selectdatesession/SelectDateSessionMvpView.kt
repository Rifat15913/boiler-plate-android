package com.lusosmile.main.ui.app.appointment.selectdatesession

import com.lusosmile.main.ui.base.callback.MvpView

interface SelectDateSessionMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}