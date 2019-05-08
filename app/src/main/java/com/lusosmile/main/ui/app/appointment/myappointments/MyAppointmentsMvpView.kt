package com.lusosmile.main.ui.app.appointment.myappointments

import com.lusosmile.main.data.remote.model.Appointment
import com.lusosmile.main.ui.base.callback.MvpView

interface MyAppointmentsMvpView : MvpView {
    fun onSuccess(list: List<Appointment>)
    fun onError(message: String)
    fun onCancellingAppointment(message: String, item: Appointment)
}