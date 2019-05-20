package com.boilerplate.main.ui.app.landing.container

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boilerplate.main.data.BaseRepository
import com.boilerplate.main.data.local.model.NavigationDrawerItem
import com.boilerplate.main.ui.app.landing.dashboard.DashboardFragment
import com.boilerplate.main.ui.app.landing.home.DrawerAdapter
import com.boilerplate.main.ui.app.landing.home.HomeFragment
import com.boilerplate.main.ui.app.others.aboutus.AboutUsFragment
import com.boilerplate.main.ui.app.others.availability.AvailabilitiesFragment
import com.boilerplate.main.ui.app.others.contactus.ContactUsFragment
import com.boilerplate.main.ui.app.others.reachus.ReachUsFragment
import com.boilerplate.main.ui.app.others.services.ServicesFragment
import com.boilerplate.main.ui.app.userprofile.profile.ProfileContainerActivity
import com.boilerplate.main.ui.base.callback.ItemClickListener
import com.boilerplate.main.ui.base.component.BaseActivity
import com.boilerplate.main.ui.base.helper.AlertDialogUtils
import com.boilerplate.main.ui.base.helper.LinearMarginItemDecoration
import com.boilerplate.utils.helper.Constants
import com.boilerplate.utils.helper.PermissionUtils
import com.boilerplate.utils.helper.SharedPrefUtils
import com.boilerplate.utils.helper.ViewUtils
import com.boilerplate.utils.libs.ToastUtils
import com.itechsoftsolutions.lusosmile.R


class ContainerActivity : BaseActivity<ContainerMvpView, ContainerPresenter>() {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            val intent = Intent(context, ContainerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            runCurrentActivity(context, intent)
        }
    }

    private lateinit var mBinding: com.itechsoftsolutions.lusosmile.databinding.ActivityContainerBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_container

    override fun getActivityPresenter(): ContainerPresenter {
        return ContainerPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as com.itechsoftsolutions.lusosmile.databinding.ActivityContainerBinding

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

    override fun stopUI() {

    }

    override fun onBackPressed() {
        if (currentFragment is HomeFragment
                && currentFragment?.currentChildFragment is DashboardFragment) {
            finish()
            super.onBackPressed()
        } else {
            visitHome()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_view_drawer_opener -> {
                mBinding.drawerLayoutWholeContainer.openDrawer(GravityCompat.START)
            }

            R.id.image_view_my_account -> {
                ProfileContainerActivity.startActivity(this)
            }
        }
    }

    private fun setListeners() {
        setClickListener(mBinding.imageViewDrawerOpener, mBinding.imageViewMyAccount)
    }

    fun setNameAtNavigationDrawer() {
        if (SharedPrefUtils.contains(Constants.PreferenceKeys.NAME)) {
            mBinding.navigationView.textViewName.text =
                    SharedPrefUtils.readString(Constants.PreferenceKeys.NAME)
        }
    }

    private fun initialize() {
        visitHome()

        if (SharedPrefUtils.contains(Constants.PreferenceKeys.EMAIL)) {
            mBinding.navigationView.textViewEmail.text =
                    SharedPrefUtils.readString(Constants.PreferenceKeys.EMAIL)
        }

        ViewUtils.initializeRecyclerView(getDrawerRecyclerView(), DrawerAdapter(),
                object : ItemClickListener<NavigationDrawerItem> {
                    override fun onItemClick(view: View, item: NavigationDrawerItem) {
                        mBinding.drawerLayoutWholeContainer.closeDrawers()

                        when (item.resourceId) {
                            R.drawable.home_blue -> {
                                visitHome()
                            }

                            R.drawable.doctor_small -> {
                                visitAboutUs()
                            }

                            R.drawable.services_small -> {
                                visitServices()
                            }

                            R.drawable.reach_small -> {
                                visitReachUs()
                            }

                            R.drawable.clock_small -> {
                                visitAvailabilities()
                            }

                            R.drawable.telephone_blue_small -> {
                                visitContactUs()
                            }

                            R.drawable.power_off -> {
                                AlertDialogUtils.on().showNativeDialog(this@ContainerActivity,
                                        true,
                                        getString(R.string.my_appointments_yes),
                                        DialogInterface.OnClickListener { dialog, _ ->
                                            dialog.dismiss()
                                            presenter.compositeDisposable.add(
                                                    BaseRepository.on().logOut(this@ContainerActivity, true))
                                        },
                                        getString(R.string.my_appointments_no),
                                        DialogInterface.OnClickListener { dialog, _ ->
                                            dialog.dismiss()
                                        },
                                        getString(R.string.my_appointments_are_you_sure),
                                        null,
                                        null)
                            }
                        }
                    }
                },
                null,
                LinearLayoutManager(this),
                LinearMarginItemDecoration(ViewUtils.getPixel(R.dimen.margin_32),
                        ViewUtils.getPixel(R.dimen.margin_32),
                        ViewUtils.getPixel(R.dimen.margin_32),
                        ViewUtils.getPixel(R.dimen.margin_32)),
                null,
                null)

        getAdapter().clear()
        getAdapter().addItems(presenter.getDrawerMenuList())
    }

    private fun getDrawerRecyclerView(): RecyclerView {
        return mBinding.navigationView.recyclerViewMenu
    }

    private fun getAdapter(): DrawerAdapter {
        return getDrawerRecyclerView().adapter as DrawerAdapter
    }

    fun setPageTitle(title: String) {
        mBinding.textViewTitle.text = title
    }

    fun visitHome() {
        val fragment = HomeFragment()
        val bundle = Bundle()

        if (intent != null) {
            if (intent?.extras != null) {
                if (intent?.extras?.containsKey(ContainerActivity::class.java.simpleName)!!) {
                    if (intent?.extras?.getBoolean(ContainerActivity::class.java.simpleName)!!) {
                        bundle.putBoolean(ContainerActivity::class.java.simpleName,
                                intent?.extras?.getBoolean(ContainerActivity::class.java.simpleName)!!)
                    }
                }
            }
        }

        fragment.arguments = bundle
        commitFragment(R.id.constraint_layout_full_fragment_container, fragment)
    }

    fun visitServices() {
        commitFragment(R.id.constraint_layout_full_fragment_container, ServicesFragment())
    }

    fun visitAvailabilities() {
        commitFragment(R.id.constraint_layout_full_fragment_container, AvailabilitiesFragment())
    }

    fun visitContactUs() {
        commitFragment(R.id.constraint_layout_full_fragment_container, ContactUsFragment())
    }

    fun visitAboutUs() {
        commitFragment(R.id.constraint_layout_full_fragment_container, AboutUsFragment())
    }

    fun visitReachUs() {
        if (PermissionUtils.requestPermission(this,
                        PermissionUtils.REQUEST_CODE_PERMISSION_LOCATION_AND_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
            commitFragment(R.id.constraint_layout_full_fragment_container, ReachUsFragment())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var isGranted = true
        if (requestCode == PermissionUtils.REQUEST_CODE_PERMISSION_LOCATION_AND_STORAGE) {
            for (i in 0 until permissions.size) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false
                }
            }

            if (isGranted) {
                visitReachUs()
            } else {
                ToastUtils.warning(getString(R.string.reach_us_error_loading))
            }
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && currentFragment is ReachUsFragment) {
            (currentFragment as ReachUsFragment).hideBottomSheet(event)
        }

        return super.dispatchTouchEvent(event)
    }
}