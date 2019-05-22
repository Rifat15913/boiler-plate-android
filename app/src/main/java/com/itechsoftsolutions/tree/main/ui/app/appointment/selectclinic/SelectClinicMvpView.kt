package com.itechsoftsolutions.tree.main.ui.app.appointment.selectclinic

import com.itechsoftsolutions.tree.main.data.remote.model.Clinic
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface SelectClinicMvpView : MvpView {
    fun onSuccess(clinicList: List<Clinic>)
    fun onError(message: String)
}