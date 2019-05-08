package com.lusosmile.main.ui.app.appointment.myappointments

import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.ui.app.landing.container.ContainerActivity
import com.lusosmile.main.ui.base.component.BaseFragment

class MyAppointmentsContainerFragment : BaseFragment<MyAppointmentsContainerMvpView,
        MyAppointmentsContainerPresenter>() {

    override fun getFragmentPresenter(): MyAppointmentsContainerPresenter {
        return MyAppointmentsContainerPresenter()
    }

    override val layoutId: Int
        get() = R.layout.fragment_my_appointments_container

    private lateinit var mBinding: FragmentMyAppointmentsContainerBinding
    private var mAdapter: AppointmentsTabViewPagerAdapter? = null

    override fun startUI() {
        mBinding = viewDataBinding as FragmentMyAppointmentsContainerBinding
        initialize()
    }

    /**
     * This method initializes the views and loads data (if needed)
     * */
    private fun initialize() {
        (baseActivity as ContainerActivity).setPageTitle(getString(R.string.my_appointments_toolbar_title))
        setupViewPager()
    }

    /**
     * This method sets the view pager with tab layout and adapter
     * */
    private fun setupViewPager() {
        if (mContext != null) {
            mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
            mAdapter = AppointmentsTabViewPagerAdapter(childFragmentManager, mContext!!)
            //mBinding.viewPager.offscreenPageLimit = Constants.Default.DEFAULT_INTEGER
            mBinding.viewPager.adapter = mAdapter
        }
    }

    override fun stopUI() {

    }
}