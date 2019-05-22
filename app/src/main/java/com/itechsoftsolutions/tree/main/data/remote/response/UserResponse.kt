package com.itechsoftsolutions.tree.main.data.remote.response

import com.google.gson.annotations.SerializedName
import com.itechsoftsolutions.tree.main.data.remote.model.User
import com.itechsoftsolutions.tree.utils.helper.Constants

/**
 * This is model class for response of profile page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class UserResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: User,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)