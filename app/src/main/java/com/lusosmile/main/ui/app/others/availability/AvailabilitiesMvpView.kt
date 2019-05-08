package com.lusosmile.main.ui.app.others.availability

import com.lusosmile.main.data.remote.model.Availability
import com.lusosmile.main.ui.base.callback.MvpView

interface AvailabilitiesMvpView : MvpView {
    fun onSuccess(list: List<Availability>)
    fun onError(message: String)
}