package com.lusosmile.main.ui.app.landing.dashboard

import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.ui.app.landing.container.ContainerActivity
import com.lusosmile.main.ui.base.component.BaseFragment
import com.lusosmile.utils.helper.Constants
import com.lusosmile.utils.helper.SharedPrefUtils
import com.lusosmile.utils.libs.GlideUtils
import java.util.*

class DashboardFragment : BaseFragment<DashboardMvpView, DashboardPresenter>(),
        DashboardMvpView {

    private lateinit var mBinding: FragmentDashboardBinding

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun getFragmentPresenter(): DashboardPresenter {
        return DashboardPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentDashboardBinding
        setListeners()
        initialize()
    }

    private fun loadData() {
        if (mContext != null) {
            presenter.getUserProfile(mContext!!)
        }
    }

    private fun setListeners() {
        mBinding.layoutServices.cardViewContainer.setOnClickListener {
            (baseActivity as ContainerActivity).visitServices()
        }

        mBinding.layoutAvailability.cardViewContainer.setOnClickListener {
            (baseActivity as ContainerActivity).visitAvailabilities()
        }

        mBinding.layoutReachUs.cardViewContainer.setOnClickListener {
            (baseActivity as ContainerActivity).visitReachUs()
        }

        mBinding.layoutContactUs.cardViewContainer.setOnClickListener {
            (baseActivity as ContainerActivity).visitContactUs()
        }

        mBinding.layoutAboutUs.cardViewContainer.setOnClickListener {
            (baseActivity as ContainerActivity).visitAboutUs()
        }
    }

    override fun onResume() {
        super.onResume()
        setPageTitle()
        (baseActivity as ContainerActivity).setNameAtNavigationDrawer()
        loadData()
    }

    private fun setPageTitle() {
        val name = SharedPrefUtils.readString(Constants.PreferenceKeys.NAME)
        (baseActivity as ContainerActivity).setPageTitle(String.format(Locale.getDefault(),
                getString(R.string.dashboard_toolbar_title),
                if (name.contains(Constants.Default.SPACE_STRING))
                    name.substring(0, name.indexOf(Constants.Default.SPACE_STRING))
                else
                    name.trim()))
    }

    private fun initialize() {
        GlideUtils.normalWithCaching(mBinding.layoutAboutUs.imageViewService, R.drawable.doctor_big)
        GlideUtils.normalWithCaching(mBinding.layoutServices.imageViewService, R.drawable.services_big)
        GlideUtils.normalWithCaching(mBinding.layoutAvailability.imageViewService, R.drawable.clock_big)
        GlideUtils.normalWithCaching(mBinding.layoutReachUs.imageViewService, R.drawable.reach_big)
        GlideUtils.normalWithCaching(mBinding.layoutContactUs.imageViewService, R.drawable.telephone_blue_big)

        mBinding.layoutAboutUs.textViewTitle.text = getString(R.string.dashboard_about_us)
        mBinding.layoutServices.textViewTitle.text = getString(R.string.dashboard_services)
        mBinding.layoutAvailability.textViewTitle.text = getString(R.string.dashboard_availability)
        mBinding.layoutReachUs.textViewTitle.text = getString(R.string.dashboard_reach_us)
        mBinding.layoutContactUs.textViewTitle.text = getString(R.string.dashboard_contact_us)
    }

    override fun stopUI() {

    }

    override fun onResult() {
        setPageTitle()
        (baseActivity as ContainerActivity).setNameAtNavigationDrawer()
    }
}