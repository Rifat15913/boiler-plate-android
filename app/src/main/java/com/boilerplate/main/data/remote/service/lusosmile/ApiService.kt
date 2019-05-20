package com.boilerplate.main.data.remote.service.lusosmile

import com.boilerplate.main.data.remote.response.*
import com.boilerplate.main.data.remote.service.ConnectivityInterceptor
import com.boilerplate.utils.helper.Constants
import com.boilerplate.utils.helper.DataUtils
import com.google.gson.GsonBuilder
import com.itechsoftsolutions.lusosmile.R
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * This is the API service interface of the project. This interface contains all the basic methods
 * needed for remote purposes.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
interface ApiService {
    /**
     * This method registers an user
     *
     * @param name name of the user
     * @param email email of the user
     * @param mobilePhone mobile number of the user
     * @param password password of the user
     * @param deviceType type of the device
     * @param token FCM token of device in order to send push notifications in future
     * @return base response consists of success status, data and message
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.REGISTER)
    fun register(@Query(Constants.API.Body.Field.NAME) name: String,
                 @Query(Constants.API.Body.Field.EMAIL) email: String,
                 @Query(Constants.API.Body.Field.MOBILE) mobilePhone: String,
                 @Query(Constants.API.Body.Field.PASSWORD) password: String,
                 @Query(Constants.API.Body.Field.DEVICE_TYPE) deviceType: Int,
                 @Query(Constants.API.Body.Field.FCM_TOKEN) token: String)
            : Flowable<retrofit2.Response<BaseResponse>>

    /**
     * This method logs in an user
     *
     * @param email email of the user
     * @param password password of the user
     * @param deviceType type of the device
     * @param token FCM token of device in order to send push notifications in future
     * @return response consists of success status, data and message
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.LOGIN)
    fun login(@Query(Constants.API.Body.Field.EMAIL) email: String,
              @Query(Constants.API.Body.Field.PASSWORD) password: String,
              @Query(Constants.API.Body.Field.DEVICE_TYPE) deviceType: Int,
              @Query(Constants.API.Body.Field.FCM_TOKEN) token: String)
            : Flowable<retrofit2.Response<LoginResponse>>

    /**
     * This method resets the password of the user account
     *
     * @param email email of the user
     * @return response consists of success status, data and message
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.RESET_PASSWORD)
    fun resetPassword(@Query(Constants.API.Body.Field.EMAIL) email: String)
            : Flowable<retrofit2.Response<BaseResponse>>

    /**
     * This method gets all the clinics from the back-office
     * @param accessToken access token of the user
     * @return response consists of clinics
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.CLINICS)
    fun getClinics(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String)
            : Flowable<retrofit2.Response<ClinicsResponse>>

    /**
     * This method gets all the appointment types from the back-office
     * @param accessToken access token of the user
     * @return response consists of appointment types
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.APPOINTMENT_TYPE)
    fun getAppointmentTypes(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String)
            : Flowable<retrofit2.Response<AppointmentTypesResponse>>

    /**
     * This method gets reservation time limit for the user
     * @param accessToken access token of the user
     * @return response consists of reservation time limit
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.RESERVATION_TIME_LIMIT)
    fun getReservationTimeLimit(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String)
            : Flowable<retrofit2.Response<BaseResponse>>

    /**
     * This method books an appointment for the user
     * @param accessToken access token of the user
     * @param clinicId id of the clinic
     * @param sessionId id of the clinic
     * @param serviceId id of the service or appointment type
     * @param date string of time in milliseconds
     * @return response consists of success status
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.BOOK_APPOINTMENT)
    fun bookAppointment(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
                        @Query(Constants.API.Body.Field.CLINIC_ID) clinicId: Long,
                        @Query(Constants.API.Body.Field.SESSION_ID) sessionId: Long,
                        @Query(Constants.API.Body.Field.SERVICE_ID) serviceId: Long,
                        @Query(Constants.API.Body.Field.DATE) date: String)
            : Flowable<retrofit2.Response<BaseResponse>>

    /**
     * This method gets all the availabilities for the user
     * @param accessToken access token of the user
     * @return response consists of availabilities
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.AVAILABILITIES)
    fun getAvailabilities(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String)
            : Flowable<retrofit2.Response<AvailabilitiesResponse>>

    /**
     * This method let the user to contact admin
     * @param accessToken access token of the user
     * @param name name of the user
     * @param email email of the user
     * @param message message from the user
     * @return response consists of success status
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.CONTACT_US)
    fun contactAdmin(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
                     @Query(Constants.API.Body.Field.NAME) name: String,
                     @Query(Constants.API.Body.Field.EMAIL) email: String,
                     @Query(Constants.API.Body.Field.MESSAGE) message: String)
            : Flowable<Response<BaseResponse>>

    /**
     * This method gets the about page content from back-office
     * @param accessToken access token of the user
     * @return response consists of about page content
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.ABOUT_US)
    fun getAboutPageContent(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String)
            : Flowable<retrofit2.Response<AboutResponse>>

    /**
     * This method gets the appointments of the user
     * @param accessToken access token of the user
     * @param isUpcoming if the upcoming appointments are needed
     * @param date current time
     * @return response consists of appointments
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.APPOINTMENTS)
    fun getMyAppointments(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
                          @Query(Constants.API.Body.Field.IS_UPCOMING) isUpcoming: Int,
                          @Query(Constants.API.Body.Field.DATE) date: Long)
            : Flowable<retrofit2.Response<AppointmentsResponse>>

    /**
     * This method cancels an appointment of the user
     * @param accessToken access token of the user
     * @param appointmentId id of the appointment
     * @return response consists of success status
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.CANCEL_APPOINTMENT)
    fun cancelAppointment(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
                          @Query(Constants.API.Body.Field.ID) appointmentId: Long)
            : Flowable<retrofit2.Response<BaseResponse>>

    /**
     * This method logs out the user
     * @param accessToken access token of the user
     * @param deviceType type of the device
     * @param token FCM token of device in order to send push notifications in future
     * @return response consists of success status
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.LOG_OUT)
    fun logOut(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
               @Query(Constants.API.Body.Field.DEVICE_TYPE) deviceType: Int,
               @Query(Constants.API.Body.Field.FCM_TOKEN) token: String)
            : Flowable<retrofit2.Response<BaseResponse>>

    /**
     * This method gets profile of the user
     * @param accessToken access token of the user
     * @return response consists of user profile
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @GET(Constants.API.PROFILE_DETAILS)
    fun getUserProfile(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String)
            : Flowable<retrofit2.Response<UserResponse>>

    /**
     * This method updates password of the user
     * @param accessToken access token of the user
     * @param password current password of the user
     * @param newPassword new password of the user
     * @return response consists of success status
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.UPDATE_PASSWORD)
    fun updatePassword(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
                       @Query(Constants.API.Body.Field.PASSWORD) password: String,
                       @Query(Constants.API.Body.Field.NEW_PASSWORD) newPassword: String)
            : Flowable<Response<BaseResponse>>

    /**
     * This method updates profile of the user
     * @param accessToken access token of the user
     * @param name name of the user
     * @param mobile mobile number of the user
     * @return response consists of success status
     * */
    @Headers(Constants.API.Header.RESPONSE_FORMAT)
    @POST(Constants.API.UPDATE_USER_PROFILE)
    fun updateUserProfile(@Header(Constants.API.Header.Field.AUTHORIZATION) accessToken: String,
                          @Query(Constants.API.Body.Field.NAME) name: String,
                          @Query(Constants.API.Body.Field.MOBILE) mobile: String)
            : Flowable<Response<BaseResponse>>

    companion object {
        private val gson = GsonBuilder().setLenient().create()

        private val customClient = OkHttpClient.Builder()
                .addInterceptor(ConnectivityInterceptor())
                .build()

        private val sRetrofitBuilder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(customClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(DataUtils.getString(R.string.api_base_url))
                .build()

        private var sInstance: ApiService? = null

        /**
         * This method returns an instance of the this service
         *
         * @return instance of the this service
         * */
        fun on(): ApiService {
            if (sInstance == null) {
                sInstance = sRetrofitBuilder.create(ApiService::class.java)
            }

            return sInstance!!
        }
    }
}