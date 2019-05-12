package com.lusosmile.main.ui.app.authentication.login

import android.content.Context
import com.lusosmile.main.ui.base.component.BasePresenter
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils

class LoginPresenter : BasePresenter<LoginMvpView>() {
    private var mToken: String? = null

    fun login(context: Context, email: String, password: String) {
        ProgressDialogUtils.on().showProgressDialog(context)
        /*FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        mvpView?.onError(DataUtils.getString(R.string.login_error_login_failed))
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    mToken = task.result.token

                    compositeDisposable.add(
                            BaseRepository.on().login(email, password, mToken!!)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe({
                                        val response = it.body()

                                        if (response == null) {
                                            mvpView?.onError(DataUtils.getString(R.string.login_error_login_failed))
                                        } else {
                                            if (response.isSuccessful) {
                                                if (response.data.asJsonObject.has(Constants.JsonKeys.ACCESS_TOKEN)) {
                                                    val accessToken = response.data.asJsonObject.get(Constants.JsonKeys.ACCESS_TOKEN).asString!!
                                                    SharedPrefUtils.write(Constants.PreferenceKeys.ACCESS_TOKEN, accessToken)

                                                    if (response.data.asJsonObject.has(Constants.JsonKeys.USER_INFO)) {
                                                        val userInfo = response.data.asJsonObject.get(Constants.JsonKeys.USER_INFO)!!

                                                        if (userInfo.asJsonObject.has(Constants.JsonKeys.NAME)) {
                                                            val userName = userInfo.asJsonObject.get(Constants.JsonKeys.NAME).asString!!
                                                            SharedPrefUtils.write(Constants.PreferenceKeys.NAME, userName)
                                                        }

                                                        if (userInfo.asJsonObject.has(Constants.JsonKeys.EMAIL)) {
                                                            val userEmail = userInfo.asJsonObject.get(Constants.JsonKeys.EMAIL).asString!!
                                                            SharedPrefUtils.write(Constants.PreferenceKeys.EMAIL, userEmail)
                                                        }

                                                        if (userInfo.asJsonObject.has(Constants.JsonKeys.MOBILE)) {
                                                            val userMobile = userInfo.asJsonObject.get(Constants.JsonKeys.MOBILE).asString!!
                                                            SharedPrefUtils.write(Constants.PreferenceKeys.MOBILE, userMobile)
                                                        }

                                                        if (userInfo.asJsonObject.has(Constants.JsonKeys.FCM_TOKEN)) {
                                                            val fcmToken = userInfo.asJsonObject.get(Constants.JsonKeys.FCM_TOKEN).asString!!
                                                            SharedPrefUtils.write(Constants.PreferenceKeys.FCM_TOKEN, fcmToken)
                                                        }

                                                        SharedPrefUtils.write(Constants.PreferenceKeys.LOGGED_IN, true)
                                                        mvpView?.onSuccess(response.message)
                                                    } else {
                                                        mvpView?.onError(DataUtils.getString(R.string.login_error_login_failed))
                                                    }
                                                } else {
                                                    SharedPrefUtils.delete(Constants.PreferenceKeys.ACCESS_TOKEN)
                                                    mvpView?.onError(DataUtils.getString(R.string.login_error_login_failed))
                                                }
                                            } else {
                                                mvpView?.onError(response.message)
                                            }
                                        }
                                    }, {
                                        Timber.d(it)

                                        if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                            mvpView?.onError(it.message)
                                        } else {
                                            mvpView?.onError(DataUtils.getString(R.string.login_error_login_failed))
                                        }
                                    }))
                })*/
    }
}