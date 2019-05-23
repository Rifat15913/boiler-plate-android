package com.itechsoftsolutions.tree.main.data.remote.response

import com.google.gson.annotations.SerializedName
import com.itechsoftsolutions.tree.main.data.remote.model.AppointmentType
import com.itechsoftsolutions.tree.utils.helper.Constants

/**
 * This is model class for response of appointment types page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AppointmentTypesResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.TYPES) var data: List<AppointmentType>,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)