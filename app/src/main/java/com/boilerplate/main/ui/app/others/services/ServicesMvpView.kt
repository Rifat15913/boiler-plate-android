package com.boilerplate.main.ui.app.others.services

import com.boilerplate.main.data.remote.model.AppointmentType
import com.boilerplate.main.ui.base.callback.MvpView

interface ServicesMvpView : MvpView {
    fun onSuccess(appointmentTypeList: List<AppointmentType>)
    fun onError(message: String)
}