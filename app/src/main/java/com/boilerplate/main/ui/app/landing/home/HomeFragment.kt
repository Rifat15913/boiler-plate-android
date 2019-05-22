package com.boilerplate.main.ui.app.landing.home

import android.content.Context
import android.content.Intent
import android.view.View
import com.boilerplate.main.ui.app.appointment.myappointments.MyAppointmentsContainerFragment
import com.boilerplate.main.ui.app.appointment.selectclinic.SelectClinicActivity
import com.boilerplate.main.ui.app.landing.container.ContainerActivity
import com.boilerplate.main.ui.app.landing.dashboard.DashboardFragment
import com.boilerplate.main.ui.base.component.BaseActivity.Companion.runCurrentActivity
import com.boilerplate.main.ui.base.component.BaseFragment
import com.boilerplate.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeMvpView, HomePresenter>() {

    companion object {
        /**
         * This method starts current activity
         *
         * @param context UI context
         * */
        fun startActivity(context: Context) {
            val intent = Intent(context, HomeFragment::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            runCurrentActivity(context, intent)
        }
    }

    private lateinit var mBinding: FragmentHomeBinding

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun getFragmentPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentHomeBinding

        setListeners()
        initialize()
    }

    override fun stopUI() {

    }

    private fun setListeners() {
        setClickListener(mBinding.textViewHome, mBinding.textViewMyAppointments,
                mBinding.textViewBookAppointment, mBinding.fabBookAppointment)
    }

    private fun initialize() {
        if (arguments != null) {
            if (arguments?.containsKey(ContainerActivity::class.java.simpleName)!!) {
                if (arguments?.getBoolean(ContainerActivity::class.java.simpleName)!!) {
                    visitMyAppointments()
                } else {
                    visitDashboard()
                }
            } else {
                visitDashboard()
            }
        } else {
            visitDashboard()
        }
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.fab_book_appointment, R.id.text_view_book_appointment -> {
                bookAppointment()
            }

            R.id.text_view_home -> {
                visitDashboard()
            }

            R.id.text_view_my_appointments -> {
                visitMyAppointments()
            }
        }
    }

    fun visitDashboard() {
        mBinding.textViewHome.setTextColor(ViewUtils.getColor(R.color.colorPrimary))
        mBinding.textViewBookAppointment.setTextColor(ViewUtils.getColor(R.color.white))
        mBinding.textViewMyAppointments.setTextColor(ViewUtils.getColor(R.color.white))

        mBinding.textViewHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_blue, 0, 0)
        mBinding.textViewMyAppointments.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.clock_white, 0, 0)

        commitChildFragment(R.id.constraint_layout_fragment_container, DashboardFragment())
    }

    private fun bookAppointment() {
        if (mContext != null) {
            SelectClinicActivity.startActivity(mContext!!)
        }
    }

    fun visitMyAppointments() {
        mBinding.textViewHome.setTextColor(ViewUtils.getColor(R.color.white))
        mBinding.textViewBookAppointment.setTextColor(ViewUtils.getColor(R.color.white))
        mBinding.textViewMyAppointments.setTextColor(ViewUtils.getColor(R.color.colorPrimary))

        mBinding.textViewHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_white, 0, 0)
        mBinding.textViewMyAppointments.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.clock_blue, 0, 0)

        commitChildFragment(R.id.constraint_layout_fragment_container, MyAppointmentsContainerFragment())
    }
}