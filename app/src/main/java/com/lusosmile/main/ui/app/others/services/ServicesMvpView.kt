package com.lusosmile.main.ui.app.others.services

import com.lusosmile.main.data.remote.model.AppointmentType
import com.lusosmile.main.ui.base.callback.MvpView

interface ServicesMvpView : MvpView {
    fun onSuccess(appointmentTypeList: List<AppointmentType>)
    fun onError(message: String)
}