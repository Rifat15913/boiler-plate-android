package com.itechsoftsolutions.tree.main.ui.app.authentication.registration

import android.content.Context
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils

class RegistrationPresenter : BasePresenter<RegistrationMvpView>() {

    private var mToken: String? = null

    fun registerTheUser(context: Context, name: String, email: String, mobilePhone: String, password: String) {
        ProgressDialogUtils.on().showProgressDialog(context)
        /*FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        mvpView?.onError(DataUtils.getString(R.string.registration_error_of_registration))
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    mToken = task.result.token

                    compositeDisposable.add(
                            BaseRepository.on().register(name, email, mobilePhone, password, mToken!!)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe({
                                        val baseResponse = it.body()

                                        if (baseResponse == null) {
                                            mvpView?.onError(DataUtils.getString(R.string.registration_error_of_registration))
                                        } else {
                                            if (baseResponse.isSuccessful) {
                                                mvpView?.onSuccess(String.format(Locale.getDefault(),
                                                        DataUtils.getString(R.string.registration_confirmation_of_registration),
                                                        email))
                                            } else {
                                                mvpView?.onError(baseResponse.message)
                                            }
                                        }
                                    }, {
                                        Timber.d(it)

                                        if (it is NoConnectivityException && !TextUtils.isEmpty(it.message)) {
                                            mvpView?.onError(it.message)
                                        } else {
                                            mvpView?.onError(DataUtils.getString(R.string.registration_error_of_registration))
                                        }


                                    }))
                })*/
    }
}