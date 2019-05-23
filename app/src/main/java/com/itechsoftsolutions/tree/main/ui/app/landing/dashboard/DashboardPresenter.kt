package com.itechsoftsolutions.tree.main.ui.app.landing.dashboard

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.BaseRepository
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.SharedPrefUtils
import com.itechsoftsolutions.tree.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DashboardPresenter : BasePresenter<DashboardMvpView>() {
    fun getUserProfile(context: Context) {
        compositeDisposable.add(
                BaseRepository.on().getUserProfile()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onResult()
                                } else {
                                    if (response.isSuccessful) {
                                        SharedPrefUtils.write(Constants.PreferenceKeys.NAME, response.data.name)
                                        SharedPrefUtils.write(Constants.PreferenceKeys.MOBILE, response.data.mobileNumber)
                                        mvpView?.onResult()
                                    } else {
                                        mvpView?.onResult()
                                    }
                                }
                            }
                        }, {
                            Timber.d(it)

                            if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                mvpView?.onResult()
                            } else {
                                mvpView?.onResult()
                            }
                        }))
    }
}