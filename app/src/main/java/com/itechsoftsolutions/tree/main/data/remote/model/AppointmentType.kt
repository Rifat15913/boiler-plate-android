package com.itechsoftsolutions.tree.main.data.remote.model

import com.google.gson.annotations.SerializedName
import com.itechsoftsolutions.tree.utils.helper.Constants

/**
 * This is model class for appointment types of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AppointmentType(
        @SerializedName(Constants.JsonKeys.ID) var id: Long,
        @SerializedName(Constants.JsonKeys.IMAGE) var image: String,
        @SerializedName(Constants.JsonKeys.NAME) var name: String)