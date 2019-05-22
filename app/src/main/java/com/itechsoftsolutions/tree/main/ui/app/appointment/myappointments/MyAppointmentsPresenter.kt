package com.itechsoftsolutions.tree.main.ui.app.appointment.myappointments

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.BaseRepository
import com.itechsoftsolutions.tree.main.data.remote.model.Appointment
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MyAppointmentsPresenter : BasePresenter<MyAppointmentsMvpView>() {
    private var mGetAppointmentsLoader: AlertDialog? = null
    private var mCancelAppointmentLoader: AlertDialog? = null

    fun getAppointments(context: Context, isUpcoming: Boolean) {
        mGetAppointmentsLoader = ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().getMyAppointments(isUpcoming)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            mGetAppointmentsLoader?.dismiss()

                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.my_appointments_error_of_process))
                                } else {
                                    if (response.isSuccessful) {
                                        val list = response.data

                                        list.forEach { appointment ->
                                            appointment.scheduledOn *= 1000
                                        }

                                        mvpView?.onSuccess(list)
                                    } else {
                                        mvpView?.onError(response.message)
                                    }
                                }
                            }
                        }, {
                            Timber.d(it)
                            mGetAppointmentsLoader?.dismiss()

                            if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                mvpView?.onError(it.message)
                            } else {
                                mvpView?.onError(DataUtils.getString(R.string.my_appointments_error_of_process))
                            }

                        }))
    }

    override fun detachView() {
        super.detachView()
        mGetAppointmentsLoader?.dismiss()
        mCancelAppointmentLoader?.dismiss()
    }

    fun cancelAppointment(context: Context, item: Appointment) {
        mCancelAppointmentLoader = ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().cancelAppointment(item.id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            mCancelAppointmentLoader?.dismiss()

                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.my_appointments_error_of_cancelling_appointment))
                                } else {
                                    if (response.isSuccessful) {
                                        item.status = DataUtils.getString(R.string.my_appointments_pending_cancellation_state)
                                        mvpView?.onCancellingAppointment(response.message, item)
                                    } else {
                                        mvpView?.onError(response.message)
                                    }
                                }
                            }
                        }, {
                            Timber.d(it)
                            mCancelAppointmentLoader?.dismiss()

                            if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                mvpView?.onError(it.message)
                            } else {
                                mvpView?.onError(DataUtils.getString(R.string.my_appointments_error_of_cancelling_appointment))
                            }
                        }))
    }
}