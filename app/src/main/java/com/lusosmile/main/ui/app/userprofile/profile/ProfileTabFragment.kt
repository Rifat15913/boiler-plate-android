package com.lusosmile.main.ui.app.userprofile.profile

import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.remote.model.User
import com.lusosmile.main.ui.base.component.BaseFragment
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.libs.ToastUtils

class ProfileTabFragment : BaseFragment<ProfileTabMvpView, ProfileTabPresenter>(),
        ProfileTabMvpView {

    companion object {
        /**
         * This method provides a new instance of this fragment
         *
         * @return instance of this fragment
         */
        fun newInstance(isProfile: Boolean): ProfileTabFragment {
            val fragment = ProfileTabFragment()
            val args = Bundle()
            args.putBoolean(ProfileTabFragment::class.java.simpleName, isProfile)
            fragment.arguments = args
            return fragment
        }
    }

    private var mIsProfile: Boolean? = null
    private lateinit var mBinding: FragmentProfileBinding

    override val layoutId: Int
        get() = R.layout.fragment_profile

    override fun getFragmentPresenter(): ProfileTabPresenter {
        return ProfileTabPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentProfileBinding
        extractArguments()
        initialize()
        setListeners()
        loadData()
    }

    private fun setListeners() {
        setClickListener(mBinding.textViewSubmit)
    }

    private fun loadData() {
        if (mIsProfile != null && mContext != null) {
            if (mIsProfile!!) {
                presenter.getUserProfile(mContext!!)
            }
        }
    }

    private fun extractArguments() {
        if (arguments != null) {
            if (arguments?.containsKey(ProfileTabFragment::class.java.simpleName)!!) {
                mIsProfile = arguments?.getBoolean(ProfileTabFragment::class.java.simpleName)
            }
        }
    }

    private fun initialize() {
        if (mIsProfile != null) {
            if (mIsProfile!!) {
                mBinding.textViewFirstField.text = getString(R.string.profile_name)
                mBinding.textViewSecondField.text = getString(R.string.profile_email)
                mBinding.textViewThirdField.text = getString(R.string.profile_mobile_number)

                mBinding.editTextFirstField.hint = getString(R.string.profile_name)
                mBinding.editTextSecondField.hint = getString(R.string.profile_email)
                mBinding.editTextThirdField.hint = getString(R.string.profile_mobile_number)

                mBinding.editTextFirstField.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                mBinding.editTextSecondField.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                mBinding.editTextThirdField.inputType = InputType.TYPE_CLASS_PHONE

                mBinding.editTextFirstField.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.user_small, 0)
                mBinding.editTextSecondField.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.email, 0)
                mBinding.editTextThirdField.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.telephone_blue_small, 0)

                mBinding.textViewSubmit.text = getString(R.string.profile_update_profile)
                mBinding.textViewSubmit.isEnabled = false
                mBinding.editTextFirstField.isEnabled = false
                mBinding.editTextSecondField.isEnabled = false
                mBinding.editTextThirdField.isEnabled = false
            } else {
                mBinding.textViewFirstField.text = getString(R.string.profile_current_password)
                mBinding.textViewSecondField.text = getString(R.string.profile_new_password)
                mBinding.textViewThirdField.text = getString(R.string.profile_confirm_new_password)

                mBinding.editTextFirstField.hint = getString(R.string.profile_hint_password_field)
                mBinding.editTextSecondField.hint = getString(R.string.profile_hint_password_field)
                mBinding.editTextThirdField.hint = getString(R.string.profile_hint_password_field)

                mBinding.editTextFirstField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                mBinding.editTextSecondField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                mBinding.editTextThirdField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                mBinding.editTextFirstField.typeface = mBinding.textViewFirstField.typeface
                mBinding.editTextSecondField.typeface = mBinding.textViewSecondField.typeface
                mBinding.editTextThirdField.typeface = mBinding.textViewThirdField.typeface

                mBinding.editTextFirstField.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.lock, 0)
                mBinding.editTextSecondField.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.lock, 0)
                mBinding.editTextThirdField.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.lock, 0)

                mBinding.textViewSubmit.text = getString(R.string.profile_update_password)
            }
        }
    }

    fun onClickEditButton(isEditMode: Boolean) {
        mBinding.textViewSubmit.isEnabled = isEditMode
        mBinding.editTextFirstField.isEnabled = isEditMode
        mBinding.editTextThirdField.isEnabled = isEditMode
    }

    override fun stopUI() {

    }

    override fun onGettingProfile(user: User) {
        ProgressDialogUtils.on().hideProgressDialog()
        mBinding.editTextFirstField.setText(user.name)
        mBinding.editTextSecondField.setText(user.email)
        mBinding.editTextThirdField.setText(user.mobileNumber)
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    override fun onUpdatingPassword(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.success(message)
        mBinding.editTextFirstField.text.clear()
        mBinding.editTextSecondField.text.clear()
        mBinding.editTextThirdField.text.clear()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_view_submit -> {
                if (mIsProfile != null && mContext != null) {
                    if (mIsProfile!!) {
                        updateUserProfile()
                    } else {
                        updatePassword()
                    }
                }
            }
        }
    }

    private fun updateUserProfile() {
        if (mBinding.editTextFirstField.text != null
                && mBinding.editTextThirdField.text != null) {
            if (!TextUtils.isEmpty(mBinding.editTextFirstField.text.toString().trim())
                    && !TextUtils.isEmpty(mBinding.editTextThirdField.text.toString().trim())) {
                if (Patterns.PHONE.matcher(mBinding.editTextThirdField.text.toString().trim()).matches()) {
                    presenter.updateUserProfile(mContext!!,
                            mBinding.editTextFirstField.text.toString().trim(),
                            mBinding.editTextThirdField.text.toString().trim())
                } else {
                    onError(getString(R.string.profile_error_valid_email))
                }
            } else {
                onError(getString(R.string.profile_error_valid_fields))
            }
        } else {
            onError(getString(R.string.profile_error_valid_fields))
        }
    }

    private fun updatePassword() {
        if (mBinding.editTextFirstField.text != null
                && mBinding.editTextSecondField.text != null
                && mBinding.editTextThirdField.text != null) {
            if (!TextUtils.isEmpty(mBinding.editTextFirstField.text.toString().trim())
                    && !TextUtils.isEmpty(mBinding.editTextSecondField.text.toString().trim())
                    && !TextUtils.isEmpty(mBinding.editTextThirdField.text.toString().trim())) {
                if (mBinding.editTextSecondField.text.toString().trim() ==
                        mBinding.editTextThirdField.text.toString().trim()) {

                    if (((mBinding.editTextFirstField.text.toString().trim().length >=
                                    DataUtils.getInteger(R.integer.character_min_limit_password))
                                    && (mBinding.editTextFirstField.text.toString().trim().length
                                    <= DataUtils.getInteger(R.integer.character_max_limit_password)))
                            && (((mBinding.editTextSecondField.text.toString().trim().length >=
                                    DataUtils.getInteger(R.integer.character_min_limit_password))
                                    && (mBinding.editTextSecondField.text.toString().trim().length
                                    <= DataUtils.getInteger(R.integer.character_max_limit_password))))) {
                        presenter.updatePassword(mContext!!,
                                mBinding.editTextFirstField.text.toString().trim(),
                                mBinding.editTextSecondField.text.toString().trim())
                    } else {
                        onError(getString(R.string.profile_error_valid_password))
                    }
                } else {
                    onError(getString(R.string.profile_error_confirm_passwords_dont_match))
                }
            } else {
                onError(getString(R.string.profile_error_valid_fields))
            }
        } else {
            onError(getString(R.string.profile_error_valid_fields))
        }
    }

    override fun onUpdatingUserProfile(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.success(message)
        onClickEditButton(false)
        (baseActivity as ProfileContainerActivity).showEditButton(true)
    }
}