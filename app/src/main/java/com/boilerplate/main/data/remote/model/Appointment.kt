package com.boilerplate.main.data.remote.model

import com.boilerplate.utils.helper.Constants
import com.google.gson.annotations.SerializedName

/**
 * This is model class for appointment of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class Appointment(
        @SerializedName(Constants.JsonKeys.ID) var id: Long,
        @SerializedName(Constants.JsonKeys.SCHEDULED_ON) var scheduledOn: Long,
        @SerializedName(Constants.JsonKeys.CLINIC) var clinic: String,
        @SerializedName(Constants.JsonKeys.SERVICE) var service: String,
        @SerializedName(Constants.JsonKeys.SESSION) var session: String,
        @SerializedName(Constants.JsonKeys.STATUS) var status: String)