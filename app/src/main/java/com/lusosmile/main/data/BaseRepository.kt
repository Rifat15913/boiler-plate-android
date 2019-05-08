package com.lusosmile.main.data

import android.app.Activity
import android.content.Context
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.local.AppLocalDataSource
import com.lusosmile.main.data.local.user.UserEntity
import com.lusosmile.main.data.remote.AppRemoteDataSource
import com.lusosmile.main.data.remote.response.*
import com.lusosmile.main.data.remote.retrophoto.RetroPhoto
import com.lusosmile.main.ui.app.authentication.welcome.WelcomeActivity
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.libs.ToastUtils
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * This is the repository class of the project. This class contains all the basic methods needed
 * for the project purposes.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
class BaseRepository(context: Context) {
    private val mAppLocalDataSource = AppLocalDataSource(context)
    private val mAppRemoteDataSource = AppRemoteDataSource(context)

    companion object {
        private lateinit var sInstance: BaseRepository

        /**
         * This method returns an instance of the this class
         *
         * @return instance of the this class
         * */
        fun on(): BaseRepository {
            return sInstance
        }

        /**
         * This method initializes the class
         * @param context application context
         * */
        @Synchronized
        fun init(context: Context) {
            synchronized(BaseRepository::class.java) {
                sInstance = BaseRepository(context)
            }
        }
    }

    fun insertUserToDatabase(entity: UserEntity): Completable {
        return mAppLocalDataSource.insertCompletable(entity)
    }

    fun getAllPhotosFromServer(): Flowable<List<RetroPhoto>> {
        return mAppRemoteDataSource.getAllPhotos()
    }

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
    fun register(name: String, email: String, mobilePhone: String, password: String, token: String): Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.register(name, email, mobilePhone, password, token)
    }

    /**
     * This method logs in an user
     *
     * @param email email of the user
     * @param password password of the user
     * @param token FCM token of device in order to send push notifications in future
     * @return response consists of success status, data and message
     * */
    fun login(email: String, password: String, token: String): Flowable<retrofit2.Response<LoginResponse>> {
        return mAppRemoteDataSource.login(email, password, token)
    }

    /**
     * This method resets the password of the user account
     *
     * @param email email of the user
     * @return response consists of success status, data and message
     * */
    fun resetPassword(email: String): Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.resetPassword(email)
    }

    /**
     * This method gets all the clinics from the back-office
     *
     * @return response consists of clinics
     * */
    fun getClinics(): Flowable<retrofit2.Response<ClinicsResponse>> {
        return mAppRemoteDataSource.getClinics()
    }

    /**
     * This method gets all the appointment types from the back-office
     *
     * @return response consists of appointment types
     * */
    fun getAppointmentTypes(): Flowable<retrofit2.Response<AppointmentTypesResponse>> {
        return mAppRemoteDataSource.getAppointmentTypes()
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
        return mAppRemoteDataSource.bookAppointment(clinicId, sessionId, serviceId, date)
    }

    /**
     * This method gets reservation time limit for the user
     *
     * @return response consists of reservation time limit
     * */
    fun getReservationTimeLimit(): Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.getReservationTimeLimit()
    }

    /**
     * This method gets all the availabilities for the user
     *
     * @return response consists of availabilities
     * */
    fun getAvailabilities(): Flowable<retrofit2.Response<AvailabilitiesResponse>> {
        return mAppRemoteDataSource.getAvailabilities()
    }

    /**
     * This method logs out the user
     * @param activity current activity
     * @param eventFromDrawer if it's called from the navigation drawer or not
     * @return disposable operation
     * */
    fun logOut(activity: Activity, eventFromDrawer: Boolean = false): Disposable {
        ProgressDialogUtils.on().showProgressDialog(activity)
        return mAppRemoteDataSource.logOut()
                .flatMapCompletable {
                    mAppLocalDataSource.logOut()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onError(e: Throwable) {
                        ProgressDialogUtils.on().hideProgressDialog()
                        Timber.e(e)
                    }

                    override fun onComplete() {
                        ProgressDialogUtils.on().hideProgressDialog()

                        if (!eventFromDrawer) {
                            ToastUtils.warning(DataUtils.getString(R.string.session_expired))
                        }

                        WelcomeActivity.startActivity(activity)
                        activity.finish()
                    }
                })
    }

    /**
     * This method let the user to contact admin
     * @param name name of the user
     * @param email email of the user
     * @param message message from the user
     * @return response consists of success status
     * */
    fun contactAdmin(name: String, email: String, message: String):
            Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.contactAdmin(name, email, message)
    }

    /**
     * This method gets the about page content from back-office
     * @return response consists of about page content
     * */
    fun getAboutPageContent(): Flowable<retrofit2.Response<AboutResponse>> {
        return mAppRemoteDataSource.getAboutPageContent()
    }

    /**
     * This method gets the appointments of the user
     * @param isUpcoming if the upcoming appointments are needed
     * @return response consists of appointments
     * */
    fun getMyAppointments(isUpcoming: Boolean): Flowable<retrofit2.Response<AppointmentsResponse>> {
        return mAppRemoteDataSource.getMyAppointments(isUpcoming)
    }

    /**
     * This method cancels an appointment of the user
     * @param appointmentId id of the appointment
     * @return response consists of success status
     * */
    fun cancelAppointment(appointmentId: Long): Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.cancelAppointment(appointmentId)
    }

    /**
     * This method gets profile of the user
     * @return response consists of user profile
     * */
    fun getUserProfile(): Flowable<retrofit2.Response<UserResponse>> {
        return mAppRemoteDataSource.getUserProfile()
    }

    /**
     * This method updates password of the user
     * @param password current password of the user
     * @param newPassword new password of the user
     * @return response consists of success status
     * */
    fun updatePassword(password: String, newPassword: String): Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.updatePassword(password, newPassword)
    }

    /**
     * This method updates profile of the user
     * @param name name of the user
     * @param mobile mobile number of the user
     * @return response consists of success status
     * */
    fun updateUserProfile(name: String, mobile: String): Flowable<retrofit2.Response<BaseResponse>> {
        return mAppRemoteDataSource.updateUserProfile(name, mobile)
    }
}
