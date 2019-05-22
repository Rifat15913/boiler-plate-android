package com.itechsoftsolutions.tree.main.ui.app.others.aboutus

import com.itechsoftsolutions.tree.main.data.remote.model.About
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface AboutUsMvpView : MvpView {
    fun onSuccess(item: About)
    fun onError(message: String)
}