package com.boilerplate.main.data.remote.model

import com.boilerplate.utils.helper.Constants
import com.google.gson.annotations.SerializedName

/**
 * This is model class for clinics of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class Clinic(
        @SerializedName(Constants.JsonKeys.ID) var id: Long,
        @SerializedName(Constants.JsonKeys.NAME) var name: String,
        @SerializedName(Constants.JsonKeys.LATITUDE) var latitude: String,
        @SerializedName(Constants.JsonKeys.LONGITUDE) var longitude: String,
        @SerializedName(Constants.JsonKeys.ADDRESS) var address: String,
        @SerializedName(Constants.JsonKeys.CITY) var city: String,
        @SerializedName(Constants.JsonKeys.ZIP_CODE) var zipCode: String)