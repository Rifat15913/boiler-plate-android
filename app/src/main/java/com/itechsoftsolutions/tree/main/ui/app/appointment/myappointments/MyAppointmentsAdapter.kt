package com.itechsoftsolutions.tree.main.ui.app.appointment.myappointments

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.ItemMyAppointmentBinding
import com.itechsoftsolutions.tree.main.data.remote.model.Appointment
import com.itechsoftsolutions.tree.main.ui.base.component.BaseAdapter
import com.itechsoftsolutions.tree.main.ui.base.component.BaseViewHolder
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.TimeUtils

class MyAppointmentsAdapter(private val mIsUpcoming: Boolean) : BaseAdapter<Appointment>() {

    override fun isEqual(left: Appointment, right: Appointment): Boolean {
        return left.id == right.id
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Appointment> {
        return AppointmentViewHolder(inflate(parent, R.layout.item_my_appointment))
    }

    inner class AppointmentViewHolder(binding: ViewDataBinding) : BaseViewHolder<Appointment>(binding) {
        private val mBinding = binding as ItemMyAppointmentBinding

        override fun bind(item: Appointment) {
            mBinding.textViewContentStatus.text = DataUtils.toTitleCase(item.status)
            mBinding.textViewContentType.text = DataUtils.toTitleCase(item.service)
            mBinding.textViewContentDate.text = TimeUtils.getFormattedDateString(item.scheduledOn)

            if (mIsUpcoming) {
                setClickListener(mBinding.textViewCancel)
                mBinding.textViewCancel.visibility = View.VISIBLE

                if (item.status.contains(DataUtils.getString(R.string.my_appointments_confirmed),
                                true)) {
                    mBinding.textViewContentHour.text = TimeUtils.getFormattedTimeString(item.scheduledOn)
                } else {
                    mBinding.textViewContentHour.text = DataUtils.toTitleCase(item.session)
                    mBinding.textViewCancel.visibility = View.INVISIBLE
                }
            } else {
                mBinding.textViewCancel.visibility = View.INVISIBLE

                if (item.status.contains(DataUtils.getString(R.string.my_appointments_confirmed),
                                true)) {
                    mBinding.textViewContentHour.text = TimeUtils.getFormattedTimeString(item.scheduledOn)
                } else {
                    mBinding.textViewContentHour.text = DataUtils.toTitleCase(item.session)
                }
            }
        }

        override fun onClick(view: View) {
            if (getItem(adapterPosition) != null) {
                mItemClickListener?.onItemClick(view, getItem(adapterPosition)!!)
            }
        }
    }
}