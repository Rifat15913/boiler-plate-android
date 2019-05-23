package com.itechsoftsolutions.tree.main.ui.app.test.main

import com.itechsoftsolutions.tree.main.data.BaseRepository
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter : BasePresenter<MainMvpView>() {
    fun test() {
        ProgressDialogUtils.on().showProgressDialog(activity!!).also {
            compositeDisposable.add(
                    BaseRepository.on().getAllPhotosFromServer()
                            .map { it.subList(0, 9) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe({
                                Timber.d(it.size.toString())
                                mvpView?.onFetchingData(it)
                                ProgressDialogUtils.on().hideProgressDialog()
                            }, {
                                Timber.d(it)
                                ProgressDialogUtils.on().hideProgressDialog()
                            }))
        }
    }
}