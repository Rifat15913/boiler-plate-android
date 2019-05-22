package com.itechsoftsolutions.tree.main.ui.app.appointment.myappointments

import com.itechsoftsolutions.tree.main.data.remote.model.Appointment
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface MyAppointmentsMvpView : MvpView {
    fun onSuccess(list: List<Appointment>)
    fun onError(message: String)
    fun onCancellingAppointment(message: String, item: Appointment)
}