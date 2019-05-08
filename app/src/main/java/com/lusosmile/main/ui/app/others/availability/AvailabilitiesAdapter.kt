package com.lusosmile.main.ui.app.others.availability

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.remote.model.Availability
import com.lusosmile.main.ui.base.component.BaseAdapter
import com.lusosmile.main.ui.base.component.BaseViewHolder
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.helper.ViewUtils
import java.util.*

class AvailabilitiesAdapter : BaseAdapter<Availability>() {

    override fun isEqual(left: Availability, right: Availability): Boolean {
        return left.id == right.id
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Availability> {
        return AvailabilityViewHolder(inflate(parent, R.layout.item_availability))
    }

    class AvailabilityViewHolder(binding: ViewDataBinding) : BaseViewHolder<Availability>(binding) {
        private val mBinding = binding as com.itechsoftsolutions.lusosmile.databinding.ItemAvailabilityBinding

        override fun bind(item: Availability) {
            if (item.isHoliday != 0) {
                mBinding.textViewSession.setTextColor(ViewUtils.getColor(R.color.negativeRed))
                mBinding.textViewSession.text = DataUtils.getString(R.string.availability_holiday)
            } else {
                val session = String.format(Locale.getDefault(),
                        DataUtils.getString(R.string.availability_session),
                        item.startingTime, item.endingTime)

                mBinding.textViewSession.text = session
            }

            mBinding.textViewDay.text = item.day
        }
    }
}