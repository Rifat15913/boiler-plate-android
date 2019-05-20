package com.boilerplate.main.ui.app.appointment.myappointments

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.boilerplate.main.data.remote.model.Appointment
import com.boilerplate.main.ui.base.callback.ItemClickListener
import com.boilerplate.main.ui.base.component.BaseFragment
import com.boilerplate.main.ui.base.helper.AlertDialogUtils
import com.boilerplate.main.ui.base.helper.LinearMarginItemDecoration
import com.boilerplate.utils.helper.ViewUtils
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.lusosmile.R
import com.itechsoftsolutions.lusosmile.databinding.FragmentMyAppointmentsBinding

class MyAppointmentsFragment : BaseFragment<MyAppointmentsMvpView, MyAppointmentsPresenter>(),
        MyAppointmentsMvpView {

    companion object {
        /**
         * This method provides a new instance of this fragment
         *
         * @return instance of this fragment
         */
        fun newInstance(isUpcoming: Boolean): MyAppointmentsFragment {
            val fragment = MyAppointmentsFragment()
            val args = Bundle()
            args.putBoolean(MyAppointmentsFragment::class.java.simpleName, isUpcoming)
            fragment.arguments = args
            return fragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_my_appointments

    private lateinit var mBinding: FragmentMyAppointmentsBinding
    private var mIsUpcoming: Boolean? = null

    override fun getFragmentPresenter(): MyAppointmentsPresenter {
        return MyAppointmentsPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentMyAppointmentsBinding

        extractIntentData()
        initialize()
        loadData()
    }

    private fun initialize() {
        if (mContext != null && mIsUpcoming != null) {
            ViewUtils.initializeRecyclerView(mBinding.recyclerViewAppointments,
                    MyAppointmentsAdapter(mIsUpcoming!!),
                    object : ItemClickListener<Appointment> {
                        override fun onItemClick(view: View, item: Appointment) {
                            when (view.id) {
                                R.id.text_view_cancel -> {
                                    if (mContext != null) {
                                        AlertDialogUtils.on().showNativeDialog(mContext!!,
                                                true,
                                                getString(R.string.my_appointments_yes),
                                                DialogInterface.OnClickListener { dialog, _ ->
                                                    dialog.dismiss()
                                                    presenter.cancelAppointment(mContext!!, item)
                                                },
                                                getString(R.string.my_appointments_no),
                                                DialogInterface.OnClickListener { dialog, _ ->
                                                    dialog.dismiss()
                                                },
                                                getString(R.string.my_appointments_are_you_sure),
                                                null,
                                                null)
                                    }
                                }
                            }
                        }
                    },
                    null,
                    LinearLayoutManager(mContext!!),
                    LinearMarginItemDecoration(ViewUtils.getPixel(R.dimen.margin_16)),
                    null,
                    null)
        }
    }

    private fun extractIntentData() {
        if (arguments != null) {
            if (arguments?.containsKey(MyAppointmentsFragment::class.java.simpleName)!!) {
                mIsUpcoming = arguments?.getBoolean(MyAppointmentsFragment::class.java.simpleName)!!
            }
        }
    }

    private fun getAdapter(): MyAppointmentsAdapter {
        return mBinding.recyclerViewAppointments.adapter as MyAppointmentsAdapter
    }

    private fun loadData() {
        if (mContext != null && mIsUpcoming != null) {
            presenter.getAppointments(mContext!!, mIsUpcoming!!)
        }
    }

    override fun stopUI() {

    }

    override fun onSuccess(list: List<Appointment>) {
        getAdapter().clear()
        getAdapter().addItems(list)

        mBinding.textViewEmptyPlaceholder.isVisible = list.isEmpty()
    }

    override fun onError(message: String) {
        ToastUtils.error(message)
    }

    override fun onCancellingAppointment(message: String, item: Appointment) {
        ToastUtils.warning(message)
        getAdapter().addItem(item)
    }
}