package com.itechsoftsolutions.tree.main.ui.app.others.reachus

import com.itechsoftsolutions.tree.main.data.remote.model.Clinic
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface ReachUsMvpView : MvpView {
    fun onSuccess(clinicList: List<Clinic>)
    fun onError(message: String)
}