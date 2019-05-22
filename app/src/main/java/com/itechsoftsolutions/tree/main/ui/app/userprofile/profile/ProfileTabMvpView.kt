package com.itechsoftsolutions.tree.main.ui.app.userprofile.profile

import com.itechsoftsolutions.tree.main.data.remote.model.User
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface ProfileTabMvpView : MvpView {
    fun onGettingProfile(user: User)
    fun onError(message: String)
    fun onUpdatingPassword(message: String)
    fun onUpdatingUserProfile(message: String)
}