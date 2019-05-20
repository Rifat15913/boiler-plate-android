package com.boilerplate.main.data.remote.response

import com.boilerplate.main.data.remote.model.Availability
import com.boilerplate.utils.helper.Constants
import com.google.gson.annotations.SerializedName

/**
 * This is model class for response of availability page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AvailabilitiesResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: List<Availability>,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)