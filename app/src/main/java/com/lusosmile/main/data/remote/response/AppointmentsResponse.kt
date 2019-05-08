package com.lusosmile.main.data.remote.response

import com.google.gson.annotations.SerializedName
import com.lusosmile.main.data.remote.model.Appointment
import com.lusosmile.utils.helper.Constants

/**
 * This is model class for response of my appointments page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AppointmentsResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: List<Appointment>,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)