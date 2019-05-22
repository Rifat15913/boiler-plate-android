package com.itechsoftsolutions.tree.main.ui.app.appointment.selectclinic

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.GridLayoutManager
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.remote.model.Clinic
import com.itechsoftsolutions.tree.main.ui.app.appointment.selectappointmenttype.SelectAppointmentTypeActivity
import com.itechsoftsolutions.tree.main.ui.base.callback.SelectionListener
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseItemDetailsLookup
import com.itechsoftsolutions.tree.main.ui.base.helper.GridSpacingItemDecoration
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.utils.libs.ToastUtils

class SelectClinicActivity : BaseActivity<SelectClinicMvpView, SelectClinicPresenter>(),
        SelectClinicMvpView, SelectionListener {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            runCurrentActivity(context, Intent(context, SelectClinicActivity::class.java))
        }
    }

    private lateinit var mBinding: com.itechsoftsolutions.tree.databinding.ActivitySelectClinicBinding
    private var mTracker: SelectionTracker<Long>? = null

    override val layoutResourceId: Int
        get() = R.layout.activity_select_clinic

    override fun getActivityPresenter(): SelectClinicPresenter {
        return SelectClinicPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            ViewUtils.setStatusBarColor(this, R.color.white)
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewUtils.setStatusBarColor(this, R.color.darkBackground)
        } else {
            // Do nothing for Jelly bean and Kitkat devices
        }

        if (savedInstanceState != null) {
            mTracker?.onRestoreInstanceState(savedInstanceState)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if (outState != null) {
            mTracker?.onSaveInstanceState(outState)
        }
    }

    override fun startUI() {
        mBinding = viewDataBinding as com.itechsoftsolutions.tree.databinding.ActivitySelectClinicBinding
        setListeners()
        initialize()
    }

    override fun stopUI() {

    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    private fun setListeners() {
        setClickListener(mBinding.imageViewNavigator, mBinding.textViewNextSteps)
    }

    private fun initialize() {
        ViewUtils.initializeRecyclerView(
                mBinding.recyclerViewClinicNames,
                ClinicAdapter(),
                null,
                null,
                GridLayoutManager(this, 2),
                GridSpacingItemDecoration(2, ViewUtils.getPixel(R.dimen.margin_8), true),
                null,
                null)

        mTracker = SelectionTracker.Builder<Long>(
                Constants.SelectionIds.CLINIC,
                mBinding.recyclerViewClinicNames,
                StableIdKeyProvider(mBinding.recyclerViewClinicNames),
                BaseItemDetailsLookup(mBinding.recyclerViewClinicNames),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
                .build()

        getAdapter().tracker = mTracker
        getAdapter().selectionListener = this

        presenter.getClinics(this)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.image_view_navigator -> {
                onBackPressed()
            }

            R.id.text_view_next_steps -> {
                if (mTracker != null && mTracker?.selection != null) {
                    SelectAppointmentTypeActivity.startActivity(this,
                            mTracker?.selection?.map {
                                getAdapter().findItemUsingKey(it)
                            }?.first()?.id!!)
                }
            }
        }
    }

    private fun getAdapter(): ClinicAdapter {
        return mBinding.recyclerViewClinicNames.adapter as ClinicAdapter
    }

    override fun onSuccess(clinicList: List<Clinic>) {
        ProgressDialogUtils.on().hideProgressDialog()
        getAdapter().clear()
        getAdapter().addItems(clinicList)
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    override fun onSelect(size: Int) {
        mBinding.textViewNextSteps.isEnabled = size > 0
    }
}