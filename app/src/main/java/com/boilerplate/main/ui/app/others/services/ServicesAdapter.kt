package com.boilerplate.main.ui.app.others.services

import android.text.TextUtils
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.boilerplate.main.data.remote.model.AppointmentType
import com.boilerplate.main.ui.base.component.BaseAdapter
import com.boilerplate.main.ui.base.component.BaseViewHolder
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.libs.GlideUtils
import com.itechsoftsolutions.lusosmile.R

class ServicesAdapter : BaseAdapter<AppointmentType>() {

    override fun isEqual(left: AppointmentType, right: AppointmentType): Boolean {
        return left.id == right.id
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AppointmentType> {
        return ServiceViewHolder(inflate(parent, R.layout.item_service))
    }

    class ServiceViewHolder(binding: ViewDataBinding) : BaseViewHolder<AppointmentType>(binding) {
        private val mBinding = binding as com.itechsoftsolutions.lusosmile.databinding.ItemServiceBinding

        override fun bind(item: AppointmentType) {
            if (!TextUtils.isEmpty(item.name)) {
                mBinding.textViewTitle.text = DataUtils.toTitleCase(item.name)
            }

            GlideUtils.normalWithCaching(mBinding.imageViewService, item.image)
        }
    }
}