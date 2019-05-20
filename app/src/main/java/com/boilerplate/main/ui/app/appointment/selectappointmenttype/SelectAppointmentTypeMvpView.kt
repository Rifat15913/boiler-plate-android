package com.boilerplate.main.ui.app.appointment.selectappointmenttype

import com.boilerplate.main.data.remote.model.AppointmentType
import com.boilerplate.main.ui.base.callback.MvpView

interface SelectAppointmentTypeMvpView : MvpView {
    fun onSuccess(appointmentTypeList: List<AppointmentType>)
    fun onError(message: String)
    fun onGettingReservationMonthLimit(monthLimit: Int)
}