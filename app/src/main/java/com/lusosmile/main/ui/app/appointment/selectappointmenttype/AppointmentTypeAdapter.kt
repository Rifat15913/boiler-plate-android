package com.lusosmile.main.ui.app.appointment.selectappointmenttype

import android.graphics.Bitmap
import android.text.TextUtils
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.itechsoftsolutions.lusosmile.R
import com.itechsoftsolutions.lusosmile.databinding.ItemAppointmentTypeBinding
import com.lusosmile.main.data.remote.model.AppointmentType
import com.lusosmile.main.ui.base.component.BaseSelectableAdapter
import com.lusosmile.main.ui.base.component.BaseSelectableViewHolder
import com.lusosmile.main.ui.base.component.BaseViewHolder
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.helper.ViewUtils
import com.lusosmile.utils.libs.GlideUtils

class AppointmentTypeAdapter : BaseSelectableAdapter<AppointmentType>() {
    override fun getItemIdForPosition(position: Int): Long {
        return getItem(position)?.id!!
    }

    override fun isEqual(left: AppointmentType, right: AppointmentType): Boolean {
        return left.id == right.id
    }

    fun findItemUsingKey(key: Long): AppointmentType? {
        var desiredItem: AppointmentType? = null

        for (i in 0 until getItems().size) {
            if (getItems()[i].id == key) {
                desiredItem = getItems()[i]
                break
            }
        }

        return desiredItem
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AppointmentType> {
        return AppointmentTypeViewHolder(inflate(parent, R.layout.item_appointment_type))
    }

    class AppointmentTypeViewHolder(binding: ViewDataBinding) : BaseSelectableViewHolder<AppointmentType>(binding) {
        private val mBinding: ItemAppointmentTypeBinding = binding as ItemAppointmentTypeBinding

        override fun bind(item: AppointmentType) {
            if (!TextUtils.isEmpty(item.name)) {
                mBinding.textViewTitle.text = DataUtils.toTitleCase(item.name)
            }

            GlideUtils.normalWithCaching(mBinding.imageViewAppointmentType, item.image,
                    object : RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap?, model: Any?,
                                                     target: Target<Bitmap>?,
                                                     dataSource: DataSource?,
                                                     isFirstResource: Boolean): Boolean {

                            mBinding.imageViewAppointmentType.setImageBitmap(resource)

                            DrawableCompat.setTint(mBinding.imageViewAppointmentType.drawable,
                                    ViewUtils.getColor(if (itemView.isActivated) {
                                        R.color.white
                                    } else {
                                        R.color.colorPrimary
                                    }
                                    ))

                            return true
                        }
                    })
        }
    }
}