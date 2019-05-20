package com.boilerplate.main.ui.app.userprofile.profile

import com.boilerplate.main.data.remote.model.User
import com.boilerplate.main.ui.base.callback.MvpView

interface ProfileTabMvpView : MvpView {
    fun onGettingProfile(user: User)
    fun onError(message: String)
    fun onUpdatingPassword(message: String)
    fun onUpdatingUserProfile(message: String)
}