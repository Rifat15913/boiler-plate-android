package com.boilerplate.main.ui.app.others.contactus

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

class ContactUsPresenter : BasePresenter<ContactUsMvpView>() {
    fun contactAdmin(context: Context, name: String, email: String, message: String) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().contactAdmin(name, email, message)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.contact_us_error_of_process))
                                } else {
                                    if (response.isSuccessful) {
                                        mvpView?.onSuccess(DataUtils.getString(R.string.contact_us_successful_process))
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
                                mvpView?.onError(DataUtils.getString(R.string.contact_us_error_of_process))
                            }

                        }))
    }
}