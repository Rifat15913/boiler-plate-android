package com.itechsoftsolutions.tree.main.ui.app.authentication.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.ActivityRegistrationBinding
import com.itechsoftsolutions.tree.main.ui.app.authentication.login.LoginActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.KeyboardUtils
import com.itechsoftsolutions.tree.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.utils.libs.ToastUtils

class RegistrationActivity : BaseActivity<RegistrationMvpView, RegistrationPresenter>(),
        RegistrationMvpView {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            runCurrentActivity(context, Intent(context, RegistrationActivity::class.java))
        }
    }

    private lateinit var mBinding: ActivityRegistrationBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_registration

    override fun getActivityPresenter(): RegistrationPresenter {
        return RegistrationPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ViewUtils.setStatusBarColor(this, R.color.darkBackground)
        super.onCreate(savedInstanceState)
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityRegistrationBinding
        setListeners()
        //initValidations()
    }

    private fun setListeners() {
        setClickListener(mBinding.textViewHaveAnAccount, mBinding.textViewRegister)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.text_view_have_an_account -> {
                LoginActivity.startActivity(this)
                finish()
            }

            R.id.text_view_register -> {
                goForRegistration()
            }
        }
    }

    private fun goForRegistration() {
        if (TextUtils.isEmpty(mBinding.editTextName.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextEmail.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextMobilePhone.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextPassword.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextConfirmPassword.text.toString().trim())) {
            showAlert(getString(R.string.registration_error_valid_fields))
        } else {
            if (mBinding.editTextName.text.toString().trim().length >
                    DataUtils.getInteger(R.integer.character_max_limit_user_name)) {
                showAlert(getString(R.string.registration_error_user_name_char_limit))
            } else if (!(Patterns.EMAIL_ADDRESS.matcher(mBinding.editTextEmail.text.toString().trim()).matches())) {
                showAlert(getString(R.string.registration_error_valid_email))
            } else if (!(Patterns.PHONE.matcher(mBinding.editTextMobilePhone.text.toString().trim()).matches())) {
                showAlert(getString(R.string.registration_error_valid_mobile_phone))
            } else if (mBinding.editTextPassword.text.toString().trim() !=
                    mBinding.editTextConfirmPassword.text.toString().trim()) {
                showAlert(getString(R.string.registration_error_not_same_password))
            } else if (mBinding.editTextPassword.text.toString().trim().length <
                    DataUtils.getInteger(R.integer.character_min_limit_password)
                    && mBinding.editTextPassword.text.toString().trim().length >
                    DataUtils.getInteger(R.integer.character_max_limit_password)) {
                showAlert(getString(R.string.registration_error_valid_password))
            } else if (mBinding.editTextConfirmPassword.text.toString().trim().length <
                    DataUtils.getInteger(R.integer.character_min_limit_password)
                    && mBinding.editTextConfirmPassword.text.toString().trim().length >
                    DataUtils.getInteger(R.integer.character_max_limit_password)) {
                showAlert(getString(R.string.registration_error_valid_password))
            } else {
                KeyboardUtils.hideKeyboard(this)
                presenter.registerTheUser(this, mBinding.editTextName.text.toString().trim(),
                        mBinding.editTextEmail.text.toString().trim(),
                        mBinding.editTextMobilePhone.text.toString().trim(),
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
        ToastUtils.success(message, true)
        finish()
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }
}