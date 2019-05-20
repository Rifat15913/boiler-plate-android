package com.boilerplate.main.data.remote

import android.content.Context
import com.boilerplate.main.data.remote.response.*
import com.boilerplate.main.data.remote.service.lusosmile.ApiService
import com.boilerplate.utils.helper.Constants
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.helper.SharedPrefUtils
import com.boilerplate.utils.helper.TimeUtils
import com.itechsoftsolutions.lusosmile.R
import io.reactivex.Flowable

/**
 * This is the remote data source class of the project. This class contains all the basic methods
 * needed for remote purposes.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
class AppRemoteDataSource(context: Context) {

    /**
     * This method registers an user
     *
     * @param name name of the user
     * @param email email of the user
     * @param mobilePhone mobile number of the user
     * @param password password of the user
     * @param token FCM token of device in order to send push notifications in future
     * @return base response consists of success status, data and message
     * */
    fun register(name: String, email: String, mobilePhone: String, password: String, token: String):
            Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().register(name, email, mobilePhone, password,
                DataUtils.getInteger(R.integer.device_type), token).onBackpressureLatest()
    }

    /**
     * This method logs in an user
     *
     * @param email email of the user
     * @param password password of the user
     * @param token FCM token of device in order to send push notifications in future
     * @return response consists of success status, data and message
     * */
    fun login(email: String, password: String, token: String):
            Flowable<retrofit2.Response<LoginResponse>> {
        return ApiService.on().login(email, password,
                DataUtils.getInteger(R.integer.device_type), token).onBackpressureLatest()
    }

    /**
     * This method resets the password of the user account
     *
     * @param email email of the user
     * @return response consists of success status, data and message
     * */
    fun resetPassword(email: String): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().resetPassword(email).onBackpressureLatest()
    }

    /**
     * This method gets all the clinics from the back-office
     *
     * @return response consists of clinics
     * */
    fun getClinics(): Flowable<retrofit2.Response<ClinicsResponse>> {
        return ApiService.on().getClinics(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method gets all the appointment types from the back-office
     *
     * @return response consists of appointment types
     * */
    fun getAppointmentTypes(): Flowable<retrofit2.Response<AppointmentTypesResponse>> {
        return ApiService.on().getAppointmentTypes(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method books an appointment for the user
     *
     * @param clinicId id of the clinic
     * @param sessionId id of the clinic
     * @param serviceId id of the service or appointment type
     * @param date string of time in milliseconds
     * @return response consists of success status
     * */
    fun bookAppointment(clinicId: Long, sessionId: Long, serviceId: Long, date: String)
            : Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().bookAppointment(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN),
                clinicId, sessionId, serviceId, date).onBackpressureLatest()
    }

    /**
     * This method gets reservation time limit for the user
     *
     * @return response consists of reservation time limit
     * */
    fun getReservationTimeLimit(): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().getReservationTimeLimit(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method gets all the availabilities for the user
     *
     * @return response consists of availabilities
     * */
    fun getAvailabilities(): Flowable<retrofit2.Response<AvailabilitiesResponse>> {
        return ApiService.on().getAvailabilities(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method let the user to contact admin
     * @param name name of the user
     * @param email email of the user
     * @param message message from the user
     * @return response consists of success status
     * */
    fun contactAdmin(name: String, email: String, message: String): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().contactAdmin(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN),
                name, email, message)
                .onBackpressureLatest()
    }

    /**
     * This method gets the about page content from back-office
     * @return response consists of about page content
     * */
    fun getAboutPageContent(): Flowable<retrofit2.Response<AboutResponse>> {
        return ApiService.on().getAboutPageContent(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method gets the appointments of the user
     * @param isUpcoming if the upcoming appointments are needed
     * @return response consists of appointments
     * */
    fun getMyAppointments(isUpcoming: Boolean): Flowable<retrofit2.Response<AppointmentsResponse>> {
        return ApiService.on().getMyAppointments((Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)),
                if (isUpcoming) Constants.Default.TRUE_INTEGER else Constants.Default.FALSE_INTEGER,
                TimeUtils.currentTime() / 1000).onBackpressureLatest()
    }

    /**
     * This method cancels an appointment of the user
     * @param appointmentId id of the appointment
     * @return response consists of success status
     * */
    fun cancelAppointment(appointmentId: Long): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().cancelAppointment((Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)),
                appointmentId).onBackpressureLatest()
    }

    /**
     * This method logs out the user
     * @return response consists of success status
     * */
    fun logOut(): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().logOut((Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)),
                DataUtils.getInteger(R.integer.device_type),
                SharedPrefUtils.readString(Constants.PreferenceKeys.FCM_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method gets profile of the user
     * @return response consists of user profile
     * */
    fun getUserProfile(): Flowable<retrofit2.Response<UserResponse>> {
        return ApiService.on().getUserProfile(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN)).onBackpressureLatest()
    }

    /**
     * This method updates password of the user
     * @param password current password of the user
     * @param newPassword new password of the user
     * @return response consists of success status
     * */
    fun updatePassword(password: String, newPassword: String): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().updatePassword(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN),
                password, newPassword)
                .onBackpressureLatest()
    }

    /**
     * This method updates profile of the user
     * @param name name of the user
     * @param mobile mobile number of the user
     * @return response consists of success status
     * */
    fun updateUserProfile(name: String, mobile: String): Flowable<retrofit2.Response<BaseResponse>> {
        return ApiService.on().updateUserProfile(Constants.Common.ROLE +
                SharedPrefUtils.readString(Constants.PreferenceKeys.ACCESS_TOKEN),
                name, mobile)
                .onBackpressureLatest()
    }
}