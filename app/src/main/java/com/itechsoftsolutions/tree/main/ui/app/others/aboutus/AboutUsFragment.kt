package com.itechsoftsolutions.tree.main.ui.app.others.aboutus

import android.view.MotionEvent
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.FragmentAboutUsBinding
import com.itechsoftsolutions.tree.main.data.remote.model.About
import com.itechsoftsolutions.tree.main.ui.app.landing.container.ContainerActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseFragment
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.libs.ToastUtils
import java.util.*

class AboutUsFragment : BaseFragment<AboutUsMvpView, AboutUsPresenter>(),
        AboutUsMvpView {

    override val layoutId: Int
        get() = R.layout.fragment_about_us

    private lateinit var mBinding: com.itechsoftsolutions.tree.databinding.FragmentAboutUsBinding

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

        mBinding.webViewBody.setOnTouchListener { _, event ->
            event.action == MotionEvent.ACTION_MOVE
        }

        mBinding.webViewBody.loadDataWithBaseURL(Constants.Common.BASE_URL_APP_RESOURCES,
                styledBodyContent,
                Constants.Common.HTML_MIME_TYPE,
                Constants.Common.HTML_ENCODING,
                Constants.Default.DEFAULT_STRING)

        mBinding.webViewBody.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                ProgressDialogUtils.on().hideProgressDialog()
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                ProgressDialogUtils.on().hideProgressDialog()
            }
        }
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }
}