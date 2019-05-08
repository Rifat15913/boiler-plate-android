package com.lusosmile.main.ui.app.userprofile.profile

import com.lusosmile.main.data.remote.model.User
import com.lusosmile.main.ui.base.callback.MvpView

interface ProfileTabMvpView : MvpView {
    fun onGettingProfile(user: User)
    fun onError(message: String)
    fun onUpdatingPassword(message: String)
    fun onUpdatingUserProfile(message: String)
}