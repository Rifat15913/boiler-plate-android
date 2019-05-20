package com.boilerplate.main.data.remote.model

import com.boilerplate.utils.helper.Constants
import com.google.gson.annotations.SerializedName

/**
 * This is model class for availability item of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class Availability(
        @SerializedName(Constants.JsonKeys.ID) var id: Long,
        @SerializedName(Constants.JsonKeys.DAY) var day: String,
        @SerializedName(Constants.JsonKeys.STARTING_TIME) var startingTime: String,
        @SerializedName(Constants.JsonKeys.ENDING_TIME) var endingTime: String,
        @SerializedName(Constants.JsonKeys.IS_HOLIDAY) var isHoliday: Int)