package com.itechsoftsolutions.tree.main.ui.app.authentication.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.ActivityLoginBinding
import com.itechsoftsolutions.tree.main.ui.app.authentication.forgotpassword.ForgotPasswordActivity
import com.itechsoftsolutions.tree.main.ui.app.authentication.registration.RegistrationActivity
import com.itechsoftsolutions.tree.main.ui.app.landing.container.ContainerActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.KeyboardUtils
import com.itechsoftsolutions.tree.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.utils.libs.ToastUtils

class LoginActivity : BaseActivity<LoginMvpView, LoginPresenter>(),
        LoginMvpView {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            runCurrentActivity(context, Intent(context, LoginActivity::class.java))
        }
    }

    private lateinit var mBinding: ActivityLoginBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_login

    override fun getActivityPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ViewUtils.setStatusBarColor(this, R.color.darkBackground)
        super.onCreate(savedInstanceState)
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityLoginBinding
        setListeners()
    }

    private fun setListeners() {
        setClickListener(mBinding.textViewForgotPassword, mBinding.textViewHaveAnAccount,
                mBinding.textViewLogin)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.text_view_forgot_password -> {
                ForgotPasswordActivity.startActivity(this)
            }

            R.id.text_view_have_an_account -> {
                RegistrationActivity.startActivity(this)
                finish()
            }

            R.id.text_view_login -> {
                goForLogin()
            }
        }
    }

    private fun goForLogin() {
        if (TextUtils.isEmpty(mBinding.editTextEmail.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextPassword.text.toString().trim())) {
            showAlert(getString(R.string.login_error_valid_fields))
        } else {
            if (!(Patterns.EMAIL_ADDRESS.matcher(mBinding.editTextEmail.text.toString().trim()).matches())) {
                showAlert(getString(R.string.login_error_valid_email))
            } else if (mBinding.editTextPassword.text.toString().trim().length <
                    DataUtils.getInteger(R.integer.character_min_limit_password)
                    && mBinding.editTextPassword.text.toString().trim().length >
                    DataUtils.getInteger(R.integer.character_max_limit_password)) {
                showAlert(getString(R.string.login_error_valid_password))
            } else {
                KeyboardUtils.hideKeyboard(this)
                presenter.login(this, mBinding.editTextEmail.text.toString().trim(),
                        mBinding.editTextPassword.text.toString().trim())
            }
        }
    }

    private fun showAlert(message: String) {
        ToastUtils.error(message, true)
    }

    override fun stopUI() {

    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onSuccess(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ContainerActivity.startActivity(this)
        finish()
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }
}