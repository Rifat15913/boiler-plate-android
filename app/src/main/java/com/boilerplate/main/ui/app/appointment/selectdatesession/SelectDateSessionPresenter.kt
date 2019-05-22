package com.boilerplate.main.ui.app.appointment.selectdatesession

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.boilerplate.main.data.BaseRepository
import com.boilerplate.main.ui.base.component.BasePresenter
import com.boilerplate.main.ui.base.helper.ProgressDialogUtils
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.helper.network.NoConnectivityException
import com.itechsoftsolutions.tree.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SelectDateSessionPresenter : BasePresenter<SelectDateSessionMvpView>() {

    fun bookAppointment(context: Context, clinicId: Long, sessionId: Long, serviceId: Long, date: Long) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().bookAppointment(clinicId, sessionId, serviceId,
                        (date / 1000).toString())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.select_date_session_error_of_process))
                                } else {
                                    if (response.isSuccessful) {
                                        mvpView?.onSuccess(response.message)
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
                                mvpView?.onError(DataUtils.getString(R.string.select_date_session_error_of_process))
                            }

                        }))
    }
}