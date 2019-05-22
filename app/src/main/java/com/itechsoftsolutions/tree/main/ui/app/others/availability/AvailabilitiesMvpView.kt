package com.itechsoftsolutions.tree.main.ui.app.others.availability

import com.itechsoftsolutions.tree.main.data.remote.model.Availability
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface AvailabilitiesMvpView : MvpView {
    fun onSuccess(list: List<Availability>)
    fun onError(message: String)
}