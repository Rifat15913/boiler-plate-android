package com.itechsoftsolutions.tree.main.ui.app.authentication.welcome

import android.content.Context
import android.content.Intent
import android.view.View
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.ui.app.authentication.login.LoginActivity
import com.itechsoftsolutions.tree.main.ui.app.authentication.registration.RegistrationActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import java.util.*

class WelcomeActivity : BaseActivity<WelcomeMvpView, WelcomePresenter>() {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            runCurrentActivity(context, intent)
        }
    }

    lateinit var mBinding: com.itechsoftsolutions.tree.databinding.ActivityWelcomeBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_welcome

    override fun getActivityPresenter(): WelcomePresenter {
        return WelcomePresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as com.itechsoftsolutions.tree.databinding.ActivityWelcomeBinding
        setClickListener(mBinding.textViewLogin, mBinding.textViewRegister)
        setAppLocale(this)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.text_view_register -> RegistrationActivity.startActivity(this)
            R.id.text_view_login -> LoginActivity.startActivity(this)
        }
    }

    override fun stopUI() {

    }

    private fun setAppLocale(context: Context) {
        val locale = Locale("pt")
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        context.createConfigurationContext(config)

        //resources.updateConfiguration(config, resources.displayMetrics)
    }
}