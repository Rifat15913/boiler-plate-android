package com.boilerplate.main.ui.app.userprofile.profile

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.boilerplate.main.ui.base.component.BaseActivity
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.helper.ViewUtils
import com.itechsoftsolutions.lusosmile.R
import com.itechsoftsolutions.lusosmile.databinding.ActivityProfileContainerBinding


class ProfileContainerActivity : BaseActivity<ProfileContainerMvpView, ProfileContainerPresenter>() {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            val intent = Intent(context, ProfileContainerActivity::class.java)
            runCurrentActivity(context, intent)
        }
    }

    private lateinit var mBinding: ActivityProfileContainerBinding
    private var mAdapter: ProfileTabViewPagerAdapter? = null

    override val layoutResourceId: Int
        get() = R.layout.activity_profile_container

    override fun getActivityPresenter(): ProfileContainerPresenter {
        return ProfileContainerPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityProfileContainerBinding

        // Handle status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            mBinding.statusBar.setBackgroundColor(ViewUtils.getColor(R.color.white))
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBinding.statusBar.setBackgroundColor(ViewUtils.getColor(R.color.darkBackground))
        } else {
            // Do nothing for Jelly bean and Kitkat devices
        }

        setListeners()
        initialize()
    }

    private fun setListeners() {
        setClickListener(mBinding.imageViewNavigator, mBinding.textViewEdit)

        mBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                showEditButton(position == DataUtils.getInteger(R.integer.initial_position))

                if (position == DataUtils.getInteger(R.integer.initial_position)) {
                    val currentFragment: Fragment? = mAdapter?.getFragment(mBinding.viewPager.currentItem)
                    if (currentFragment != null) {
                        (currentFragment as ProfileTabFragment).onClickEditButton(false)
                    }
                }
            }
        })
    }

    private fun initialize() {
        setupViewPager()
    }

    /**
     * This method sets the view pager with tab layout and adapter
     * */
    private fun setupViewPager() {
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
        mAdapter = ProfileTabViewPagerAdapter(supportFragmentManager, this)
        mBinding.viewPager.adapter = mAdapter
    }

    fun showEditButton(shouldShow: Boolean) {
        mBinding.textViewEdit.visibility = if (shouldShow) View.VISIBLE else View.INVISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_view_navigator -> {
                onBackPressed()
            }

            R.id.text_view_edit -> {
                val currentFragment: Fragment? = mAdapter?.getFragment(mBinding.viewPager.currentItem)

                if (currentFragment != null) {
                    view.isVisible = false
                    (currentFragment as ProfileTabFragment).onClickEditButton(true)
                }
            }
        }
    }

    override fun stopUI() {

    }
}