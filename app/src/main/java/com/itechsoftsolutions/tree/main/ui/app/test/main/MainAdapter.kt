package com.itechsoftsolutions.tree.main.ui.app.test.main

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.ItemRetroPhotoBinding
import com.itechsoftsolutions.tree.main.data.remote.service.retrophoto.RetroPhoto
import com.itechsoftsolutions.tree.main.ui.base.component.BaseAdapter
import com.itechsoftsolutions.tree.main.ui.base.component.BaseViewHolder
import com.itechsoftsolutions.tree.utils.libs.GlideUtils

class MainAdapter : BaseAdapter<RetroPhoto>() {
    override fun isEqual(left: RetroPhoto, right: RetroPhoto): Boolean {
        return left.id == right.id
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RetroPhoto> {
        return PhotoViewHolder(inflate(parent, R.layout.item_retro_photo))
    }

    inner class PhotoViewHolder(binding: ViewDataBinding) : BaseViewHolder<RetroPhoto>(binding) {
        private val mBinding: ItemRetroPhotoBinding = binding as ItemRetroPhotoBinding

        override fun bind(item: RetroPhoto) {
            mBinding.textViewTitle.text = item.title
            if (!TextUtils.isEmpty(item.url)) {
                GlideUtils.normalWithCaching(mBinding.imageViewPhoto, item.url!!)
            }

            itemView.setOnClickListener(this)

            mBinding.executePendingBindings()
        }

        override fun onClick(view: View) {
            getItem(adapterPosition)?.let {
                mItemClickListener?.onItemClick(view, it)
            }
        }
    }
}