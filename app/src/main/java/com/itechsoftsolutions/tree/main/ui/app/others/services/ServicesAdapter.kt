package com.itechsoftsolutions.tree.main.ui.app.others.services

import android.text.TextUtils
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.remote.model.AppointmentType
import com.itechsoftsolutions.tree.main.ui.base.component.BaseAdapter
import com.itechsoftsolutions.tree.main.ui.base.component.BaseViewHolder
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.libs.GlideUtils

class ServicesAdapter : BaseAdapter<AppointmentType>() {

    override fun isEqual(left: AppointmentType, right: AppointmentType): Boolean {
        return left.id == right.id
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AppointmentType> {
        return ServiceViewHolder(inflate(parent, R.layout.item_service))
    }

    class ServiceViewHolder(binding: ViewDataBinding) : BaseViewHolder<AppointmentType>(binding) {
        private val mBinding = binding as com.itechsoftsolutions.tree.databinding.ItemServiceBinding

        override fun bind(item: AppointmentType) {
            if (!TextUtils.isEmpty(item.name)) {
                mBinding.textViewTitle.text = DataUtils.toTitleCase(item.name)
            }

            GlideUtils.normalWithCaching(mBinding.imageViewService, item.image)
        }
    }
}