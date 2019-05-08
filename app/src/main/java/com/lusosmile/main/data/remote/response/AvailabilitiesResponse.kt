package com.lusosmile.main.data.remote.response

import com.google.gson.annotations.SerializedName
import com.lusosmile.main.data.remote.model.Availability
import com.lusosmile.utils.helper.Constants

/**
 * This is model class for response of availability page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AvailabilitiesResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: List<Availability>,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)