package com.lusosmile.main.data.remote.response

import com.google.gson.annotations.SerializedName
import com.lusosmile.main.data.remote.model.Clinic
import com.lusosmile.utils.helper.Constants

/**
 * This is model class for response of clinics page
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class ClinicsResponse(
        @SerializedName(Constants.JsonKeys.SUCCESS) var isSuccessful: Boolean,
        @SerializedName(Constants.JsonKeys.DATA) var data: List<Clinic>,
        @SerializedName(Constants.JsonKeys.MESSAGE) var message: String)