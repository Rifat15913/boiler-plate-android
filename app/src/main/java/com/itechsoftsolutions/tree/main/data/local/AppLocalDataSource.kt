package com.itechsoftsolutions.tree.main.data.local

import android.content.Context
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.helper.appdatabase.AppDatabase
import com.itechsoftsolutions.tree.main.data.local.user.UserDao
import com.itechsoftsolutions.tree.main.data.local.user.UserEntity
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.SharedPrefUtils
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This is the local data source class of the project. This class contains all the basic methods
 * needed for local purposes.
 * @author Mohd. Asfaq-E-Azam Rifat
 */
class AppLocalDataSource(context: Context) {
    private var mUserDao: UserDao? = null

    /**
     * This the initialization block that initializes the database and other local services
     * */
    init {
        AppDatabase.init(context)
        mUserDao = AppDatabase.on()?.userDao()
    }

    fun insertCompletable(entity: UserEntity): Completable {
        return mUserDao?.insert(entity) ?: Completable.error(Throwable(
                DataUtils.getString(R.string.error_dao_is_null)))
    }

    /**
     * This method logs out the user
     * @return stream of the states
     * */
    fun logOut(): Completable {
        return Completable.create {
            SharedPrefUtils.write(Constants.PreferenceKeys.LOGGED_IN, false)

            if (SharedPrefUtils.contains(Constants.PreferenceKeys.EMAIL)) {
                SharedPrefUtils.delete(Constants.PreferenceKeys.EMAIL)
            }

            if (SharedPrefUtils.contains(Constants.PreferenceKeys.NAME)) {
                SharedPrefUtils.delete(Constants.PreferenceKeys.NAME)
            }

            if (SharedPrefUtils.contains(Constants.PreferenceKeys.MOBILE)) {
                SharedPrefUtils.delete(Constants.PreferenceKeys.MOBILE)
            }

            if (SharedPrefUtils.contains(Constants.PreferenceKeys.ACCESS_TOKEN)) {
                SharedPrefUtils.delete(Constants.PreferenceKeys.ACCESS_TOKEN)
            } else {
                if (!it.isDisposed) {
                    it.onError(Throwable())
                }
            }

            if (SharedPrefUtils.contains(Constants.PreferenceKeys.FCM_TOKEN)) {
                SharedPrefUtils.delete(Constants.PreferenceKeys.FCM_TOKEN)
            } else {
                if (!it.isDisposed) {
                    it.onError(Throwable())
                }
            }

            it.onComplete()
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}