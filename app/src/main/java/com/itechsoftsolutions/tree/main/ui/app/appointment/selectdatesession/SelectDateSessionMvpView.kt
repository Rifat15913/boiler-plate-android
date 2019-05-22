package com.itechsoftsolutions.tree.main.ui.app.appointment.selectdatesession

import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface SelectDateSessionMvpView : MvpView {
    fun onSuccess(message: String)
    fun onError(message: String)
}