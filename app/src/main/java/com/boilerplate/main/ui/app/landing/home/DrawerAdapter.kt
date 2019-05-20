package com.boilerplate.main.ui.app.landing.home

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.boilerplate.main.data.local.model.NavigationDrawerItem
import com.boilerplate.main.ui.base.component.BaseAdapter
import com.boilerplate.main.ui.base.component.BaseViewHolder
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.libs.GlideUtils
import com.itechsoftsolutions.lusosmile.R

class DrawerAdapter : BaseAdapter<NavigationDrawerItem>() {
    override fun isEqual(left: NavigationDrawerItem, right: NavigationDrawerItem): Boolean {
        return left.title.toLowerCase() == right.title.toLowerCase()
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NavigationDrawerItem> {
        return DrawerItemViewHolder(inflate(parent, R.layout.item_navigation_drawer_menu))
    }

    inner class DrawerItemViewHolder(binding: ViewDataBinding) : BaseViewHolder<NavigationDrawerItem>(binding) {

        private val mBinding = binding as com.itechsoftsolutions.lusosmile.databinding.ItemNavigationDrawerMenuBinding

        override fun bind(item: NavigationDrawerItem) {
            mBinding.textViewTitle.text = DataUtils.toTitleCase(item.title)
            GlideUtils.normal(mBinding.imageViewIcon, item.resourceId)

            setClickListener(itemView)
        }

        override fun onClick(view: View) {
            if (getItem(adapterPosition) != null) {
                mItemClickListener?.onItemClick(view, getItem(adapterPosition)!!)
            }
        }
    }
}