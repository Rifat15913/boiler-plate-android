package com.boilerplate.main.ui.app.others.services

import androidx.recyclerview.widget.GridLayoutManager
import com.boilerplate.main.data.remote.model.AppointmentType
import com.boilerplate.main.ui.app.landing.container.ContainerActivity
import com.boilerplate.main.ui.base.component.BaseFragment
import com.boilerplate.main.ui.base.helper.GridSpacingItemDecoration
import com.boilerplate.main.ui.base.helper.ProgressDialogUtils
import com.boilerplate.utils.helper.ViewUtils
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.tree.R

class ServicesFragment : BaseFragment<ServicesMvpView, ServicesPresenter>(), ServicesMvpView {

    override val layoutId: Int
        get() = R.layout.fragment_services

    private lateinit var mBinding: com.itechsoftsolutions.tree.databinding.FragmentServicesBinding
    private lateinit var mItemDecoration: GridSpacingItemDecoration

    override fun getFragmentPresenter(): ServicesPresenter {
        return ServicesPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as com.itechsoftsolutions.tree.databinding.FragmentServicesBinding
        initialize()
    }

    override fun stopUI() {

    }

    private fun initialize() {
        (baseActivity as ContainerActivity).setPageTitle(getString(R.string.services_toolbar_title))
        mItemDecoration = GridSpacingItemDecoration(2, ViewUtils.getPixel(R.dimen.margin_16), true)

        ViewUtils.initializeRecyclerView(
                mBinding.recyclerViewServices,
                ServicesAdapter(),
                null,
                null,
                GridLayoutManager(mContext, 2),
                null,
                null,
                null)

        presenter.getServices(mContext!!)
    }

    override fun onStop() {
        super.onStop()
        mBinding.recyclerViewServices.removeItemDecoration(mItemDecoration)
    }

    override fun onResume() {
        super.onResume()
        mBinding.recyclerViewServices.addItemDecoration(mItemDecoration)
    }

    private fun getAdapter(): ServicesAdapter {
        return mBinding.recyclerViewServices.adapter as ServicesAdapter
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    override fun onSuccess(appointmentTypeList: List<AppointmentType>) {
        ProgressDialogUtils.on().hideProgressDialog()
        getAdapter().clear()
        getAdapter().addItems(appointmentTypeList)
    }
}