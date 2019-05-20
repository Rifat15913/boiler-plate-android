package com.boilerplate.main.ui.app.appointment.myappointments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.boilerplate.utils.helper.DataUtils
import com.itechsoftsolutions.lusosmile.R
import java.util.*

class AppointmentsTabViewPagerAdapter internal constructor(fragmentManager: FragmentManager, context: Context)
    : FragmentPagerAdapter(fragmentManager) {

    private var titleList: MutableList<String>? = null

    init {
        titleList = ArrayList()
        titleList!!.add(context.getString(R.string.my_appointments_upcoming))
        titleList!!.add(context.getString(R.string.my_appointments_past))
    }

    override fun getItem(position: Int): Fragment {
        return MyAppointmentsFragment.newInstance(position ==
                DataUtils.getInteger(R.integer.initial_position))
    }

    override fun getCount(): Int {
        return titleList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList!![position]
    }
}


