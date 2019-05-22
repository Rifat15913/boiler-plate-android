package com.itechsoftsolutions.tree.main.data.remote.model

import com.google.gson.annotations.SerializedName
import com.itechsoftsolutions.tree.utils.helper.Constants

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