package com.itechsoftsolutions.tree.main.ui.app.appointment.selectdatesession

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.main.ui.app.landing.container.ContainerActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.Constants
import com.itechsoftsolutions.tree.utils.helper.DataUtils
import com.itechsoftsolutions.tree.utils.helper.TimeUtils
import com.itechsoftsolutions.tree.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.utils.libs.ToastUtils
import java.util.*


class SelectDateSessionActivity : BaseActivity<SelectDateSessionMvpView, SelectDateSessionPresenter>(),
        SelectDateSessionMvpView {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context, clinicId: Long, serviceId: Long, monthLimit: Int) {
            val intent = Intent(context, SelectDateSessionActivity::class.java)
            intent.putExtra(Constants.IntentKeys.CLINIC_ID, clinicId)
            intent.putExtra(Constants.IntentKeys.SERVICE_ID, serviceId)
            intent.putExtra(Constants.IntentKeys.MONTH_LIMIT, monthLimit)
            runCurrentActivity(context, intent)
        }
    }

    private lateinit var mBinding: com.itechsoftsolutions.tree.databinding.ActivitySelectDateSessionBinding
    private var mPickedTime: Long? = null
    private var mMonthLimit: Int? = null

    override val layoutResourceId: Int
        get() = R.layout.activity_select_date_session

    override fun getActivityPresenter(): SelectDateSessionPresenter {
        return SelectDateSessionPresenter()
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

        super.onCreate(savedInstanceState)
    }

    override fun startUI() {
        mBinding = viewDataBinding as com.itechsoftsolutions.tree.databinding.ActivitySelectDateSessionBinding
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
        setClickListener(mBinding.imageViewNavigator, mBinding.textViewMorning,
                mBinding.textViewAfternoon, mBinding.textViewAnytime, mBinding.textViewBookAppointment)

        mBinding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            mPickedTime = calendar.timeInMillis
            mBinding.nestedScrollViewContainer.smoothScrollTo(0, mBinding.textViewBookAppointment.top)
        }
    }

    private fun initialize() {
        extractMonthLimit()
        setMonthLimitToCalendar()
    }

    private fun setMonthLimitToCalendar() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, mMonthLimit
                ?: DataUtils.getInteger(R.integer.default_time_limit_in_months))

        mBinding.calendarView.minDate = TimeUtils.currentTime()
        mBinding.calendarView.maxDate = calendar.timeInMillis
    }

    private fun extractMonthLimit() {
        if (intent != null && intent.extras != null) {
            if (intent.extras != null) {
                if (intent.extras?.containsKey(Constants.IntentKeys.MONTH_LIMIT)!!) {
                    mMonthLimit = intent.extras?.getInt(Constants.IntentKeys.MONTH_LIMIT)
                }
            }
        }
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.image_view_navigator -> {
                onBackPressed()
            }

            R.id.text_view_morning -> {
                view.isActivated = !view.isActivated
                mBinding.textViewAfternoon.isActivated = false
                mBinding.textViewAnytime.isActivated = false
                onSelect(view.isActivated)
            }

            R.id.text_view_afternoon -> {
                view.isActivated = !view.isActivated
                mBinding.textViewMorning.isActivated = false
                mBinding.textViewAnytime.isActivated = false
                onSelect(view.isActivated)
            }

            R.id.text_view_anytime -> {
                view.isActivated = !view.isActivated
                mBinding.textViewMorning.isActivated = false
                mBinding.textViewAfternoon.isActivated = false
                onSelect(view.isActivated)
            }

            R.id.text_view_book_appointment -> {
                var clinicId: Long = Constants.Default.DEFAULT_LONG
                var serviceId: Long = Constants.Default.DEFAULT_LONG
                val sessionId: Long = when {
                    mBinding.textViewMorning.isActivated -> DataUtils.getInteger(R.integer.session_morning).toLong()
                    mBinding.textViewAfternoon.isActivated -> DataUtils.getInteger(R.integer.session_afternoon).toLong()
                    else -> DataUtils.getInteger(R.integer.session_anytime).toLong()
                }

                if (intent != null) {
                    val extras = intent.extras

                    if (extras != null) {
                        clinicId = extras.getLong(Constants.IntentKeys.CLINIC_ID)
                        serviceId = extras.getLong(Constants.IntentKeys.SERVICE_ID)
                    }
                }

                if (clinicId != Constants.Default.DEFAULT_LONG
                        && sessionId != Constants.Default.DEFAULT_LONG
                        && serviceId != Constants.Default.DEFAULT_LONG) {
                    presenter.bookAppointment(this, clinicId, sessionId, serviceId,
                            mPickedTime ?: TimeUtils.currentTime())
                }
            }
        }
    }

    override fun onSuccess(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.success(message)
        ContainerActivity.startActivity(this)
        finish()
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    private fun onSelect(shouldEnable: Boolean) {
        mBinding.textViewBookAppointment.isEnabled = shouldEnable
    }
}