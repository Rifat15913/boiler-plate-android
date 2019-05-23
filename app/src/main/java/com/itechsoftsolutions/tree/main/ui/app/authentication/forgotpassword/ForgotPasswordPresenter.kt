package com.itechsoftsolutions.tree.main.ui.app.authentication.forgotpassword

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

class ForgotPasswordPresenter : BasePresenter<ForgotPasswordMvpView>() {
    fun resetPassword(context: Context, email: String) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().resetPassword(email)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            val baseResponse = it.body()

                            if (baseResponse == null) {
                                mvpView?.onError(DataUtils.getString(R.string.forgot_password_error_of_process))
                            } else {
                                if (baseResponse.isSuccessful) {
                                    mvpView?.onSuccess(DataUtils.getString(R.string.forgot_password_confirmation))
                                } else {
                                    mvpView?.onError(baseResponse.message)
                                }
                            }
                        }, {
                            Timber.d(it)

                            if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                mvpView?.onError(it.message)
                            } else {
                                mvpView?.onError(DataUtils.getString(R.string.forgot_password_error_of_process))
                            }
                        }))
    }
}