package com.lusosmile.main.ui.app.others.aboutus

import com.lusosmile.main.data.remote.model.About
import com.lusosmile.main.ui.base.callback.MvpView

interface AboutUsMvpView : MvpView {
    fun onSuccess(item: About)
    fun onError(message: String)
}