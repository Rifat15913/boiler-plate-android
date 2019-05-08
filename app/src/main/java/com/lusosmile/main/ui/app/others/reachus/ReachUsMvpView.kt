package com.lusosmile.main.ui.app.others.reachus

import com.lusosmile.main.data.remote.model.Clinic
import com.lusosmile.main.ui.base.callback.MvpView

interface ReachUsMvpView : MvpView {
    fun onSuccess(clinicList: List<Clinic>)
    fun onError(message: String)
}