package com.boilerplate.main.ui.app.others.contactus

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import com.boilerplate.main.ui.app.landing.container.ContainerActivity
import com.boilerplate.main.ui.base.component.BaseFragment
import com.boilerplate.main.ui.base.helper.ProgressDialogUtils
import com.boilerplate.utils.helper.Constants
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.helper.KeyboardUtils
import com.boilerplate.utils.helper.PermissionUtils
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.lusosmile.R
import com.itechsoftsolutions.lusosmile.databinding.FragmentContactUsBinding


class ContactUsFragment : BaseFragment<ContactUsMvpView, ContactUsPresenter>(),
        ContactUsMvpView {

    override val layoutId: Int
        get() = R.layout.fragment_contact_us

    private lateinit var mBinding: FragmentContactUsBinding

    override fun getFragmentPresenter(): ContactUsPresenter {
        return ContactUsPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentContactUsBinding
        initialize()
        setListeners()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        setClickListener(mBinding.textViewSendMessage, mBinding.imageViewTelephone,
                mBinding.textViewSubtitle)

        mBinding.editTextYourMessage.setOnTouchListener { view, event ->
            if (view.hasFocus()) {
                view.parent.requestDisallowInterceptTouchEvent(true)

                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        view.parent.requestDisallowInterceptTouchEvent(false)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }

    private fun initialize() {
        (baseActivity as ContainerActivity).setPageTitle(getString(R.string.contact_us_toolbar_title))
    }

    override fun stopUI() {

    }

    private fun validateDataAndContact() {
        if (TextUtils.isEmpty(mBinding.editTextYourName.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextYourEmail.text.toString().trim())
                || TextUtils.isEmpty(mBinding.editTextYourMessage.text.toString().trim())) {
            onError(getString(R.string.contact_us_error_valid_fields))
        } else {
            if (mBinding.editTextYourName.text.toString().trim().length >
                    DataUtils.getInteger(R.integer.character_max_limit_user_name)) {
                onError(getString(R.string.contact_us_error_user_name_char_limit))
            } else if (!(Patterns.EMAIL_ADDRESS.matcher(mBinding.editTextYourEmail.text.toString().trim()).matches())) {
                onError(getString(R.string.contact_us_error_valid_email))
            } else {
                if (baseActivity != null) {
                    KeyboardUtils.hideKeyboard(baseActivity!!)
                }

                if (mContext != null) {
                    presenter.contactAdmin(mContext!!, mBinding.editTextYourName.text.toString().trim(),
                            mBinding.editTextYourEmail.text.toString().trim(),
                            mBinding.editTextYourMessage.text.toString().trim())
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_view_send_message -> {
                validateDataAndContact()
            }

            R.id.text_view_subtitle, R.id.image_view_telephone -> {
                if (PermissionUtils.requestPermission(this,
                                PermissionUtils.REQUEST_CODE_PERMISSION_CALL_PHONE,
                                Manifest.permission.CALL_PHONE)) {
                    callAuthority()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var isGranted = true

        if (requestCode == PermissionUtils.REQUEST_CODE_PERMISSION_CALL_PHONE) {
            for (i in 0 until permissions.size) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false
                }
            }

            if (isGranted) {
                callAuthority()
            }
        }
    }

    private fun callAuthority() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse(Constants.Common.TELEPHONE_URI_STARTING +
                getString(R.string.contact_us_calling_phone_number))
        mContext?.startActivity(intent)
    }

    override fun onSuccess(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.success(message, true)
        clearFields()
    }

    private fun clearFields() {
        mBinding.editTextYourName.text.clear()
        mBinding.editTextYourEmail.text.clear()
        mBinding.editTextYourMessage.text.clear()
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message, true)
    }
}