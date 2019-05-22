package com.itechsoftsolutions.tree.main.ui.app.landing.container

import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.local.model.NavigationDrawerItem
import com.itechsoftsolutions.tree.main.ui.base.component.BasePresenter
import com.itechsoftsolutions.tree.utils.helper.DataUtils

class ContainerPresenter : BasePresenter<ContainerMvpView>() {
    fun getDrawerMenuList(): List<NavigationDrawerItem> {
        val list: MutableList<NavigationDrawerItem> = ArrayList()

        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_home),
                R.drawable.home_blue))
        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_drawer_about_us),
                R.drawable.doctor_small))
        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_drawer_our_services),
                R.drawable.services_small))
        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_availability),
                R.drawable.clock_small))
        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_drawer_reach_us),
                R.drawable.reach_small))
        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_drawer_contact_us),
                R.drawable.telephone_blue_small))
        list.add(NavigationDrawerItem(DataUtils.getString(R.string.dashboard_drawer_log_out),
                R.drawable.power_off))

        return list
    }
}