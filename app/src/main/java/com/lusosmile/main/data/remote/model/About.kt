package com.lusosmile.main.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lusosmile.utils.helper.Constants

/**
 * This is model class for about page of our application.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class About(
        @SerializedName(Constants.JsonKeys.TOP_LEFT_QUANTITY) var topLeftQuantity: String,
        @SerializedName(Constants.JsonKeys.TOP_LEFT) var topLeft: String,
        @SerializedName(Constants.JsonKeys.TOP_MIDDLE_QUANTITY) var topMiddleQuantity: String,
        @SerializedName(Constants.JsonKeys.TOP_MIDDLE) var topMiddle: String,
        @SerializedName(Constants.JsonKeys.TOP_RIGHT_QUANTITY) var topRightQuantity: String,
        @SerializedName(Constants.JsonKeys.TOP_RIGHT) var topRight: String,
        @SerializedName(Constants.JsonKeys.BODY_CONTENT) var bodyContent: String)