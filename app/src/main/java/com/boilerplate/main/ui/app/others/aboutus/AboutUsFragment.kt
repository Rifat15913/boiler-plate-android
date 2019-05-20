package com.boilerplate.main.ui.app.others.aboutus

import com.boilerplate.main.data.remote.model.About
import com.boilerplate.main.ui.app.landing.container.ContainerActivity
import com.boilerplate.main.ui.base.component.BaseFragment
import com.boilerplate.main.ui.base.helper.ProgressDialogUtils
import com.boilerplate.utils.helper.Constants
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.lusosmile.R
import com.itechsoftsolutions.lusosmile.databinding.FragmentAboutUsBinding
import java.util.*

class AboutUsFragment : BaseFragment<AboutUsMvpView, AboutUsPresenter>(),
        AboutUsMvpView {

    override val layoutId: Int
        get() = R.layout.fragment_about_us

    private lateinit var mBinding: com.itechsoftsolutions.lusosmile.databinding.FragmentAboutUsBinding

    override fun getFragmentPresenter(): AboutUsPresenter {
        return AboutUsPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentAboutUsBinding
        initialize()
    }

    private fun initialize() {
        (baseActivity as ContainerActivity).setPageTitle(getString(R.string.about_toolbar_title))

        if (mContext != null) {
            presenter.getAboutPageContent(mContext!!)
        }
    }

    override fun stopUI() {

    }

    override fun onSuccess(item: About) {
        // Load the about page header
        mBinding.textViewTopLeftQuantity.text = item.topLeftQuantity
        mBinding.textViewTopLeft.text = item.topLeft
        mBinding.textViewTopMiddleQuantity.text = item.topMiddleQuantity
        mBinding.textViewTopMiddle.text = item.topMiddle
        mBinding.textViewTopRightQuantity.text = item.topRightQuantity
        mBinding.textViewTopRight.text = item.topRight

        // Load the about page body
        val justifiedStyle = Constants.Common.HTML_JUSTIFIED_STYLE
        val styledBodyContent = String.format(Locale.getDefault(), justifiedStyle, item.bodyContent)
        mBinding.webViewBody.loadDataWithBaseURL(Constants.Default.DEFAULT_STRING,
                styledBodyContent,
                Constants.Common.HTML_MIME_TYPE,
                Constants.Common.HTML_ENCODING,
                Constants.Default.DEFAULT_STRING)

        ProgressDialogUtils.on().hideProgressDialog()
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }
}