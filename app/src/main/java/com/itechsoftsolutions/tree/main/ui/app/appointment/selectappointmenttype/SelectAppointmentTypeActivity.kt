package com.itechsoftsolutions.tree.main.ui.app.appointment.selectappointmenttype

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.GridLayoutManager
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.data.local.model.SelectionTrackerParameters
import com.itechsoftsolutions.tree.main.data.remote.model.AppointmentType
import com.itechsoftsolutions.tree.main.ui.app.appointment.selectdatesession.SelectDateSessionActivity
import com.itechsoftsolutions.tree.main.ui.base.callback.SelectionListener
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import com.itechsoftsolutions.tree.main.ui.base.helper.GridSpacingItemDecoration
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.utils.libs.ToastUtils

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

    private lateinit var mBinding: com.itechsoftsolutions.tree.databinding.ActivitySelectAppointmentTypeBinding
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
        mBinding = viewDataBinding as com.itechsoftsolutions.tree.databinding.ActivitySelectAppointmentTypeBinding
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
                null,
                SelectionTrackerParameters(
                        Constants.SelectionIds.APPOINTMENT_TYPE,
                        true,
                        mTracker,
                        this))

        val list: MutableList<AppointmentType> = ArrayList()
        list.add(AppointmentType(1, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "One"))

        list.add(AppointmentType(2, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Two"))

        list.add(AppointmentType(3, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Three"))

        list.add(AppointmentType(4, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Four"))

        list.add(AppointmentType(5, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Five"))

        list.add(AppointmentType(6, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Six"))

        list.add(AppointmentType(7, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Seven"))

        list.add(AppointmentType(8, "https://images.pexels.com/photos/754082/pexels-photo-754082.jpeg?cs=srgb&dl=blur-blurred-background-colors-754082.jpg&fm=jpg", "Eight"))

        onSuccess(list)

        //loadData()
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
        return mBinding.recyclerViewAppointmentTypes.adapter as AppointmentTypeAdapter
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