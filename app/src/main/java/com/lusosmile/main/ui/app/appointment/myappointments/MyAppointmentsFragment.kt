package com.lusosmile.main.ui.app.appointment.myappointments

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.remote.model.Appointment
import com.lusosmile.main.ui.base.callback.ItemClickListener
import com.lusosmile.main.ui.base.component.BaseFragment
import com.lusosmile.main.ui.base.helper.AlertDialogUtils
import com.lusosmile.main.ui.base.helper.LinearMarginItemDecoration
import com.lusosmile.utils.helper.ViewUtils
import com.lusosmile.utils.libs.ToastUtils

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
                                        AlertDialogUtils.on().showDialog(mContext!!,
                                                getString(R.string.my_appointments_are_you_sure),
                                                getString(R.string.my_appointments_yes),
                                                getString(R.string.my_appointments_no),
                                                DialogInterface.OnClickListener { dialog, _ ->
                                                    dialog.dismiss()
                                                    presenter.cancelAppointment(mContext!!, item)
                                                },
                                                DialogInterface.OnClickListener { dialog, _ ->
                                                    dialog.dismiss()
                                                })
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
        return mBinding.recyclerViewAppointments.adapter
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