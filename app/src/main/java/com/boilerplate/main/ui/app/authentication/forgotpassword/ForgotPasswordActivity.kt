package com.boilerplate.main.ui.app.authentication.forgotpassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.boilerplate.main.ui.base.component.BaseActivity
import com.boilerplate.main.ui.base.helper.ProgressDialogUtils
import com.boilerplate.utils.helper.KeyboardUtils
import com.boilerplate.utils.helper.ViewUtils
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity<ForgotPasswordMvpView, ForgotPasswordPresenter>(),
        ForgotPasswordMvpView {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            runCurrentActivity(context, Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    private lateinit var mBinding: ActivityForgotPasswordBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_forgot_password

    override fun getActivityPresenter(): ForgotPasswordPresenter {
        return ForgotPasswordPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ViewUtils.setStatusBarColor(this, R.color.darkBackground)
        super.onCreate(savedInstanceState)
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityForgotPasswordBinding
        setListeners()
    }

    private fun setListeners() {
        setClickListener(mBinding.imageViewBack, mBinding.textViewRequest)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.image_view_back -> {
                finish()
            }

            R.id.text_view_request -> {
                goForResettingPassword()
            }
        }
    }

    private fun goForResettingPassword() {
        if (TextUtils.isEmpty(mBinding.editTextEmail.text.toString().trim())) {
            showAlert(getString(R.string.forgot_password_error_valid_field))
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(mBinding.editTextEmail.text.toString().trim()).matches())) {
            showAlert(getString(R.string.forgot_password_error_valid_email))
        } else {
            KeyboardUtils.hideKeyboard(this)
            presenter.resetPassword(this, mBinding.editTextEmail.text.toString().trim())
        }
    }

    private fun showAlert(message: String) {
        ToastUtils.error(message, true)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun stopUI() {

    }

    override fun onSuccess(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.success(message)
        finish()
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }
}