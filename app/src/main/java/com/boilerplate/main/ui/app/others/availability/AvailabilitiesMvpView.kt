package com.boilerplate.main.ui.app.others.availability

import com.boilerplate.main.data.remote.model.Availability
import com.boilerplate.main.ui.base.callback.MvpView

interface AvailabilitiesMvpView : MvpView {
    fun onSuccess(list: List<Availability>)
    fun onError(message: String)
}