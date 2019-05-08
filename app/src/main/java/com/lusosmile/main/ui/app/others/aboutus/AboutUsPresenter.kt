package com.lusosmile.main.ui.app.others.aboutus

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.BaseRepository
import com.lusosmile.main.ui.base.component.BasePresenter
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.helper.network.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class AboutUsPresenter : BasePresenter<AboutUsMvpView>() {
    fun getAboutPageContent(context: Context) {
        ProgressDialogUtils.on().showProgressDialog(context)

        compositeDisposable.add(
                BaseRepository.on().getAboutPageContent()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it.code() == DataUtils.getInteger(R.integer.unauthenticated_code)) {
                                compositeDisposable.add(BaseRepository.on().logOut(context as Activity))
                            } else {
                                val response = it.body()

                                if (response == null) {
                                    mvpView?.onError(DataUtils.getString(R.string.about_error_of_process))
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
                                mvpView?.onError(DataUtils.getString(R.string.about_error_of_process))
                            }
                        }))
    }
}