package com.itechsoftsolutions.tree.main.ui.app.appointment.selectclinic

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.BaseRepository
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SelectClinicPresenter : BasePresenter<SelectClinicMvpView>() {
    fun getClinics(context: Context) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().getClinics()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.select_clinic_error_of_process))
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
                                mvpView?.onError(DataUtils.getString(R.string.select_clinic_error_of_process))
                            }

                        }))
    }
}