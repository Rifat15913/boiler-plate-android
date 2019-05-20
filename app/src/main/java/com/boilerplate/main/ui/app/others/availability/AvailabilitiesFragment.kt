package com.boilerplate.main.ui.app.others.availability

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.boilerplate.main.data.remote.model.Availability
import com.boilerplate.main.ui.app.appointment.selectclinic.SelectClinicActivity
import com.boilerplate.main.ui.app.landing.container.ContainerActivity
import com.boilerplate.main.ui.base.component.BaseFragment
import com.boilerplate.main.ui.base.helper.ProgressDialogUtils
import com.boilerplate.utils.helper.ViewUtils
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.lusosmile.R

class AvailabilitiesFragment : BaseFragment<AvailabilitiesMvpView, AvailabilitiesPresenter>(), AvailabilitiesMvpView {

    override val layoutId: Int
        get() = R.layout.fragment_availability

    private lateinit var mBinding: com.itechsoftsolutions.lusosmile.databinding.FragmentAvailabilityBinding

    override fun getFragmentPresenter(): AvailabilitiesPresenter {
        return AvailabilitiesPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as com.itechsoftsolutions.lusosmile.databinding.FragmentAvailabilityBinding
        setListeners()
        initialize()
    }

    private fun setListeners() {
        setClickListener(mBinding.textViewBookAppointment)
    }

    override fun stopUI() {

    }

    private fun initialize() {
        (baseActivity as ContainerActivity).setPageTitle(getString(R.string.availability_toolbar_title))

        ViewUtils.initializeRecyclerView(
                mBinding.recyclerViewAvailabilities,
                AvailabilitiesAdapter(),
                null,
                null,
                LinearLayoutManager(mContext),
                null,
                null,
                null)

        presenter.getAvailabilities(mContext!!)
    }

    private fun getAdapter(): AvailabilitiesAdapter {
        return mBinding.recyclerViewAvailabilities.adapter as AvailabilitiesAdapter
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    override fun onSuccess(list: List<Availability>) {
        ProgressDialogUtils.on().hideProgressDialog()
        getAdapter().clear()
        getAdapter().addItems(list)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_view_book_appointment -> {
                if (mContext != null) {
                    SelectClinicActivity.startActivity(mContext!!)
                }
            }
        }
    }
}