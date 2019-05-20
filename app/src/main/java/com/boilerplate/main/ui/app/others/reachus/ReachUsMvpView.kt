package com.boilerplate.main.ui.app.others.reachus

import com.boilerplate.main.data.remote.model.Clinic
import com.boilerplate.main.ui.base.callback.MvpView

interface ReachUsMvpView : MvpView {
    fun onSuccess(clinicList: List<Clinic>)
    fun onError(message: String)
}