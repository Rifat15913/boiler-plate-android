package com.lusosmile.main.ui.app.appointment.selectappointmenttype

import com.lusosmile.main.data.remote.model.AppointmentType
import com.lusosmile.main.ui.base.callback.MvpView

interface SelectAppointmentTypeMvpView : MvpView {
    fun onSuccess(appointmentTypeList: List<AppointmentType>)
    fun onError(message: String)
    fun onGettingReservationMonthLimit(monthLimit: Int)
}