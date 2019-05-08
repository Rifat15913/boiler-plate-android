package com.lusosmile.main.data.remote.response

import com.google.gson.annotations.SerializedName
import com.lusosmile.main.data.remote.model.About
import com.lusosmile.utils.helper.Constants

/**
 * This is model class for response of about page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class AboutResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: About,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)