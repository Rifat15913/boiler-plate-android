package com.itechsoftsolutions.tree.main.ui.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.network.NetworkUtils
import com.itechsoftsolutions.tree.utils.libs.GlideUtils

/**
 * This file is the collection of all the extension functions to be used in the UI
 * @author Mohd. Asfaq-E-Azam Rifat
 * */

fun View.isVisibile(): Boolean = visibility == View.VISIBLE

fun View.isGone(): Boolean = visibility == View.GONE

fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

fun View.makeItVisible() {
    visibility = View.VISIBLE
}

fun View.makeItInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeItGone() {
    visibility = View.GONE
}

fun RecyclerView.Adapter<*>.getString(stringResourceId: Int): String {
    return DataUtils.getString(stringResourceId)
}

fun BasePresenter<*>.getString(stringResourceId: Int): String {
    return DataUtils.getString(stringResourceId)
}

fun ImageView.loadImage(source: Any) {
    GlideUtils.normalWithCaching(this, source)
}

fun ViewGroup.inflate(layoutId: Int): View? {
    return if (layoutId > Constants.Invalid.INVALID_INTEGER) {
        LayoutInflater.from(context).inflate(layoutId, this, false)
    } else {
        null
    }
}

fun Activity.isInternetAvailable(): Boolean {
    return NetworkUtils.isOnline()
}

fun Fragment.isInternetAvailable(): Boolean {
    return NetworkUtils.isOnline()
}

fun RecyclerView.Adapter<*>.isInternetAvailable(): Boolean {
    return NetworkUtils.isOnline()
}

fun BasePresenter<*>.isInternetAvailable(): Boolean {
    return NetworkUtils.isOnline()
}