package com.itechsoftsolutions.tree.main.ui.app.appointment.selectappointmenttype

import com.itechsoftsolutions.tree.main.data.remote.model.AppointmentType
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface SelectAppointmentTypeMvpView : MvpView {
    fun onSuccess(appointmentTypeList: List<AppointmentType>)
    fun onError(message: String)
    fun onGettingReservationMonthLimit(monthLimit: Int)
}