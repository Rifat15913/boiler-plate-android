package com.lusosmile.main.ui.app.appointment.selectclinic

import com.lusosmile.main.data.remote.model.Clinic
import com.lusosmile.main.ui.base.callback.MvpView

interface SelectClinicMvpView : MvpView {
    fun onSuccess(clinicList: List<Clinic>)
    fun onError(message: String)
}