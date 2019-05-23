package com.itechsoftsolutions.tree.main.ui.app.test.main

import com.itechsoftsolutions.tree.main.data.remote.service.retrophoto.RetroPhoto
import com.itechsoftsolutions.tree.main.ui.base.callback.MvpView

interface MainMvpView : MvpView {
    fun onFetchingData(list: List<RetroPhoto>)
}