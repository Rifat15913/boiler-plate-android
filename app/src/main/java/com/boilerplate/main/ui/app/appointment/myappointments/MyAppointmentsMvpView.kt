package com.boilerplate.main.ui.app.appointment.myappointments

import com.boilerplate.main.data.remote.model.Appointment
import com.boilerplate.main.ui.base.callback.MvpView

interface MyAppointmentsMvpView : MvpView {
    fun onSuccess(list: List<Appointment>)
    fun onError(message: String)
    fun onCancellingAppointment(message: String, item: Appointment)
}