package com.lusosmile.main.ui.app.authentication.splash

import android.os.Bundle
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.ui.app.authentication.welcome.WelcomeActivity
import com.lusosmile.main.ui.app.landing.container.ContainerActivity
import com.lusosmile.main.ui.base.component.BaseActivity
import com.lusosmile.utils.helper.Constants
import com.lusosmile.utils.helper.SharedPrefUtils

class SplashActivity : BaseActivity<SplashMvpView, SplashPresenter>(), SplashMvpView {
    override val layoutResourceId: Int
        get() = BaseActivity.INVALID_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun getActivityPresenter(): SplashPresenter {
        return SplashPresenter()
    }

    override fun startUI() {
        val isLoggedIn: Boolean = SharedPrefUtils.readBoolean(Constants.PreferenceKeys.LOGGED_IN)

        if (isLoggedIn) {
            ContainerActivity.startActivity(this)
        } else {
            WelcomeActivity.startActivity(this)
        }

        finish()
    }

    override fun stopUI() {

    }
}