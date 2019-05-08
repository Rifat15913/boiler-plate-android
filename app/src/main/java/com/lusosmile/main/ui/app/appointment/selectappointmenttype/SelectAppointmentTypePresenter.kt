package com.lusosmile.main.ui.app.appointment.selectappointmenttype

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.BaseRepository
import com.lusosmile.main.ui.base.component.BasePresenter
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils
import com.lusosmile.utils.helper.Constants
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class SelectAppointmentTypePresenter : BasePresenter<SelectAppointmentTypeMvpView>() {
    fun getAppointmentTypes(context: Context) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().getAppointmentTypes()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.select_appointment_type_error_of_process))
                                } else {
                                    if (response.isSuccessful) {
                                        mvpView?.onSuccess(response.data)
                                    } else {
                                        mvpView?.onError(response.message)
                                    }
                                }
                            }
                        }, {
                            Timber.d(it)

                            if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                mvpView?.onError(it.message)
                            } else {
                                mvpView?.onError(DataUtils.getString(R.string.select_appointment_type_error_of_process))
                            }

                        }))
    }

    fun getReservationTimeLimit(context: Context) {
        compositeDisposable.add(
                BaseRepository.on().getReservationTimeLimit()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                var limit = Constants.Default.DEFAULT_INTEGER

                                limit += if (response == null) {
                                    DataUtils.getInteger(R.integer.default_time_limit_in_months)
                                } else {
                                    if (response.isSuccessful) {
                                        if (response.data.asJsonObject.has(Constants.JsonKeys.RESERVATION_TIME)) {
                                            response.data.asJsonObject.get(Constants.JsonKeys.RESERVATION_TIME).asInt
                                        } else {
                                            DataUtils.getInteger(R.integer.default_time_limit_in_months)
                                        }
                                    } else {
                                        DataUtils.getInteger(R.integer.default_time_limit_in_months)
                                    }
                                }

                                mvpView?.onGettingReservationMonthLimit(limit)
                            }
                        }, {
                            Timber.d(it)
                            var limit = Constants.Default.DEFAULT_INTEGER
                            val calendar = Calendar.getInstance()
                            limit += DataUtils.getInteger(R.integer.default_time_limit_in_months)
                            calendar.add(Calendar.MONTH, limit)
                            mvpView?.onGettingReservationMonthLimit(limit)
                        }))
    }
}