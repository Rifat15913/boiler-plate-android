package com.boilerplate.main.data.remote.model

import com.boilerplate.utils.helper.Constants
import com.google.gson.annotations.SerializedName

/**
 * This is model class for users of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class User(
        @SerializedName(Constants.JsonKeys.ID) var id: Long,
        @SerializedName(Constants.JsonKeys.NAME) var name: String,
        @SerializedName(Constants.JsonKeys.EMAIL) var email: String,
        @SerializedName(Constants.JsonKeys.NUMBER) var mobileNumber: String)