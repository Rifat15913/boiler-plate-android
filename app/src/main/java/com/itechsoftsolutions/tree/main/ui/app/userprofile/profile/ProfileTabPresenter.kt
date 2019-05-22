package com.itechsoftsolutions.tree.main.ui.app.userprofile.profile

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.BaseRepository
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.SharedPrefUtils
import com.itechsoftsolutions.tree.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ProfileTabPresenter : BasePresenter<ProfileTabMvpView>() {
    fun getUserProfile(context: Context) {
        ProgressDialogUtils.on().showProgressDialog(context)

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
                                    mvpView?.onError(DataUtils.getString(R.string.profile_error_of_getting_profile))
                                } else {
                                    if (response.isSuccessful) {
                                        SharedPrefUtils.write(Constants.PreferenceKeys.NAME, response.data.name)
                                        SharedPrefUtils.write(Constants.PreferenceKeys.MOBILE, response.data.mobileNumber)
                                        mvpView?.onGettingProfile(response.data)
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
                                mvpView?.onError(DataUtils.getString(R.string.profile_error_of_getting_profile))
                            }

                        }))
    }

    fun updatePassword(context: Context, password: String, newPassword: String) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().updatePassword(password, newPassword)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.profile_error_of_updating_password))
                                } else {
                                    if (response.isSuccessful) {
                                        mvpView?.onUpdatingPassword(response.message)
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
                                mvpView?.onError(DataUtils.getString(R.string.profile_error_of_updating_password))
                            }

                        }))
    }

    fun updateUserProfile(context: Context, name: String, mobile: String) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().updateUserProfile(name, mobile)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.profile_error_of_updating_profile))
                                } else {
                                    if (response.isSuccessful) {
                                        SharedPrefUtils.write(Constants.PreferenceKeys.NAME, name)
                                        SharedPrefUtils.write(Constants.PreferenceKeys.MOBILE, mobile)
                                        mvpView?.onUpdatingUserProfile(response.message)
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
                                mvpView?.onError(DataUtils.getString(R.string.profile_error_of_updating_profile))
                            }

                        }))
    }
}