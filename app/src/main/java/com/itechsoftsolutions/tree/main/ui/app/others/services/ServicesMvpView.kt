package com.itechsoftsolutions.tree.main.ui.app.others.services

import com.itechsoftsolutions.tree.main.data.remote.model.AppointmentType
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface ServicesMvpView : MvpView {
    fun onSuccess(appointmentTypeList: List<AppointmentType>)
    fun onError(message: String)
}