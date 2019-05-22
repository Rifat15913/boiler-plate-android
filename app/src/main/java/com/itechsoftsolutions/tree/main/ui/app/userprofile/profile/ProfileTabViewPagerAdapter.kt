package com.itechsoftsolutions.tree.main.ui.app.userprofile.profile

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.utils.helper.DataUtils


class ProfileTabViewPagerAdapter internal constructor(fragmentManager: FragmentManager, context: Context)
    : FragmentPagerAdapter(fragmentManager) {

    private var mTitleList: MutableList<String>? = null
    private var mPageReferenceMap: MutableList<Fragment>? = null

    init {
        mTitleList = ArrayList()
        mPageReferenceMap = ArrayList()
        mTitleList!!.add(context.getString(R.string.profile_profile))
        mTitleList!!.add(context.getString(R.string.profile_change_password))
    }

    override fun getItem(position: Int): Fragment {
        val fragment = ProfileTabFragment.newInstance(position == DataUtils.getInteger(R.integer.initial_position))
        mPageReferenceMap?.add(position, fragment)
        return fragment
    }

    override fun getCount(): Int {
        return mTitleList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList!![position]
    }

    fun getFragment(key: Int): Fragment? {
        return mPageReferenceMap?.get(key)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        mPageReferenceMap?.removeAt(position)
    }
}


