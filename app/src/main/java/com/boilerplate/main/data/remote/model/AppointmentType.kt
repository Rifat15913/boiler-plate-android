package com.boilerplate.main.data.remote.model

import com.boilerplate.utils.helper.Constants
import com.google.gson.annotations.SerializedName

/**
 * This is model class for appointment types of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AppointmentType(
        @SerializedName(Constants.JsonKeys.ID) var id: Long,
        @SerializedName(Constants.JsonKeys.IMAGE) var image: String,
        @SerializedName(Constants.JsonKeys.NAME) var name: String)