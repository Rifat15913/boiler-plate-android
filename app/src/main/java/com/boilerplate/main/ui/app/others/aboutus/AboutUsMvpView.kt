package com.boilerplate.main.ui.app.others.aboutus

import com.boilerplate.main.data.remote.model.About
import com.boilerplate.main.ui.base.callback.MvpView

interface AboutUsMvpView : MvpView {
    fun onSuccess(item: About)
    fun onError(message: String)
}