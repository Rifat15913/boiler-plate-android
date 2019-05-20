package com.boilerplate.main.ui.app.appointment.selectclinic

import com.boilerplate.main.data.remote.model.Clinic
import com.boilerplate.main.ui.base.callback.MvpView

interface SelectClinicMvpView : MvpView {
    fun onSuccess(clinicList: List<Clinic>)
    fun onError(message: String)
}