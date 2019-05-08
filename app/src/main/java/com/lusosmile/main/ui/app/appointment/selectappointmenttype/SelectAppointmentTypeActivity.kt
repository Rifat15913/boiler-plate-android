package com.lusosmile.main.ui.app.appointment.selectappointmenttype

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
import com.itechsoftsolutions.lusosmile.R
import com.lusosmile.main.data.remote.model.AppointmentType
import com.lusosmile.main.ui.app.appointment.selectdatesession.SelectDateSessionActivity
import com.lusosmile.main.ui.base.callback.SelectionListener
import com.lusosmile.main.ui.base.component.BaseActivity
import com.lusosmile.main.ui.base.component.BaseItemDetailsLookup
import com.lusosmile.main.ui.base.helper.GridSpacingItemDecoration
import com.lusosmile.main.ui.base.helper.ProgressDialogUtils
import com.lusosmile.utils.helper.Constants
import com.lusosmile.utils.helper.DataUtils
import com.lusosmile.utils.helper.ViewUtils
import com.lusosmile.utils.libs.ToastUtils

class SelectAppointmentTypeActivity : BaseActivity<SelectAppointmentTypeMvpView, SelectAppointmentTypePresenter>(),
        SelectAppointmentTypeMvpView, SelectionListener {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context, clinicId: Long) {
            val intent = Intent(context, SelectAppointmentTypeActivity::class.java)
            intent.putExtra(Constants.IntentKeys.CLINIC_ID, clinicId)
            runCurrentActivity(context, intent)
        }
    }

    private lateinit var mBinding: com.itechsoftsolutions.lusosmile.databinding.ActivitySelectAppointmentTypeBinding
    private var mTracker: SelectionTracker<Long>? = null
    private var mMonthLimit: Int? = null

    override val layoutResourceId: Int
        get() = R.layout.activity_select_appointment_type

    override fun getActivityPresenter(): SelectAppointmentTypePresenter {
        return SelectAppointmentTypePresenter()
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
        mBinding = viewDataBinding as com.itechsoftsolutions.lusosmile.databinding.ActivitySelectAppointmentTypeBinding
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
                mBinding.recyclerViewAppointmentTypes,
                AppointmentTypeAdapter(),
                null,
                null,
                GridLayoutManager(this, 3),
                GridSpacingItemDecoration(3, ViewUtils.getPixel(R.dimen.margin_8), true),
                null,
                null)

        mTracker = SelectionTracker.Builder<Long>(
                Constants.SelectionIds.APPOINTMENT_TYPE,
                mBinding.recyclerViewAppointmentTypes,
                StableIdKeyProvider(mBinding.recyclerViewAppointmentTypes),
                BaseItemDetailsLookup(mBinding.recyclerViewAppointmentTypes),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
                .build()
        getAdapter().tracker = mTracker
        getAdapter().selectionListener = this

        loadData()
    }

    private fun loadData() {
        presenter.getAppointmentTypes(this)
        presenter.getReservationTimeLimit(this)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.image_view_navigator -> {
                onBackPressed()
            }

            R.id.text_view_next_steps -> {
                var clinicId: Long = Constants.Default.DEFAULT_LONG
                val serviceId: Long = mTracker?.selection?.map {
                    getAdapter().findItemUsingKey(it)
                }?.first()?.id!!

                if (intent != null) {
                    val extras = intent.extras

                    if (extras != null) {
                        clinicId = extras.getLong(Constants.IntentKeys.CLINIC_ID)
                    }
                }

                if (clinicId != Constants.Default.DEFAULT_LONG) {
                    if (mMonthLimit == null) {
                        mMonthLimit = DataUtils.getInteger(R.integer.default_time_limit_in_months)
                    }

                    SelectDateSessionActivity.startActivity(this, clinicId, serviceId, mMonthLimit!!)
                }
            }
        }
    }

    private fun getAdapter(): AppointmentTypeAdapter {
        return mBinding.recyclerViewAppointmentTypes.adapter
    }

    override fun onSuccess(appointmentTypeList: List<AppointmentType>) {
        ProgressDialogUtils.on().hideProgressDialog()
        getAdapter().clear()
        getAdapter().addItems(appointmentTypeList)
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    override fun onSelect(size: Int) {
        mBinding.textViewNextSteps.isEnabled = size > 0
    }

    override fun onGettingReservationMonthLimit(monthLimit: Int) {
        mMonthLimit = monthLimit
    }
}