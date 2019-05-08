package com.lusosmile.main.ui.app.others.services

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.BaseRepository
import com.lusosmile.main.data.remote.model.AppointmentType
import com.lusosmile.main.ui.base.component.BasePresenter
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils
import com.lusosmile.utils.helper.Constants
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ServicesPresenter : BasePresenter<ServicesMvpView>() {
    fun getServices(context: Context) {
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
                                    mvpView?.onError(DataUtils.getString(R.string.services_error_of_process))
                                } else {
                                    if (response.isSuccessful) {
                                        val list: MutableList<AppointmentType> = ArrayList()
                                        list.addAll(response.data)

                                        var removingIndex: Int = Constants.Invalid.INVALID_INTEGER

                                        for (i in 0 until list.size) {
                                            if (list[i].id == DataUtils.getInteger(R.integer.i_dont_know_id).toLong()) {
                                                removingIndex = i
                                                break
                                            }
                                        }

                                        if (removingIndex != Constants.Invalid.INVALID_INTEGER) {
                                            list.removeAt(removingIndex)
                                        }

                                        mvpView?.onSuccess(list)
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
                                mvpView?.onError(DataUtils.getString(R.string.services_error_of_process))
                            }

                        }))
    }
}