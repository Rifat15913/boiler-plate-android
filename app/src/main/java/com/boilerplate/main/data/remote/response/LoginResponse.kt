package com.boilerplate.main.data.remote.response

import com.boilerplate.utils.helper.Constants
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

/**
 * This is model class for response of login page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class LoginResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: JsonElement,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)