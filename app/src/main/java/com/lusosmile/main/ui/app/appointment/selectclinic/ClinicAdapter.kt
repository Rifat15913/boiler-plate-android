package com.lusosmile.main.ui.app.appointment.selectclinic

import android.text.TextUtils
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.itechsoftsolutions.lusosmile.R
import com.itechsoftsolutions.lusosmile.databinding.ItemClinicNameBinding
import com.lusosmile.main.data.remote.model.Clinic
import com.lusosmile.main.ui.base.component.BaseSelectableAdapter
import com.lusosmile.main.ui.base.component.BaseSelectableViewHolder
import com.lusosmile.main.ui.base.component.BaseViewHolder

class ClinicAdapter : BaseSelectableAdapter<Clinic>() {
    override fun getItemIdForPosition(position: Int): Long {
        return getItem(position)?.id!!
    }

    override fun isEqual(left: Clinic, right: Clinic): Boolean {
        return left.id == right.id
    }

    fun findItemUsingKey(key: Long): Clinic? {
        var desiredItem: Clinic? = null

        for (i in 0 until getItems().size) {
            if (getItems()[i].id == key) {
                desiredItem = getItems()[i]
                break
            }
        }

        return desiredItem
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Clinic> {
        return ClinicViewHolder(inflate(parent, R.layout.item_clinic_name))
    }

    class ClinicViewHolder(binding: ViewDataBinding) : BaseSelectableViewHolder<Clinic>(binding) {
        private val mBinding: ItemClinicNameBinding = binding as ItemClinicNameBinding

        override fun bind(item: Clinic) {
            if (!TextUtils.isEmpty(item.name)) {
                mBinding.textViewTitle.text = item.name
            }
        }
    }
}