package com.itechsoftsolutions.tree.main.ui.app.others.reachus

import android.graphics.Rect
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import com.itechsoftsolutions.tree.BaseApplication
import com.itechsoftsolutions.tree.R
import com.itechsoftsolutions.tree.databinding.FragmentReachUsBinding
import com.itechsoftsolutions.tree.main.data.remote.model.Clinic
import com.itechsoftsolutions.tree.main.ui.app.appointment.selectappointmenttype.SelectAppointmentTypeActivity
import com.itechsoftsolutions.tree.main.ui.app.landing.container.ContainerActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseFragment
import com.itechsoftsolutions.tree.main.ui.base.helper.ProgressDialogUtils
import com.itechsoftsolutions.tree.utils.helper.ViewUtils
import com.itechsoftsolutions.tree.utils.libs.ToastUtils
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.Marker
import java.util.*


class ReachUsFragment : BaseFragment<ReachUsMvpView, ReachUsPresenter>(),
        ReachUsMvpView {

    override val layoutId: Int
        get() = R.layout.fragment_reach_us

    private lateinit var mBinding: FragmentReachUsBinding
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    private var mClinicList: List<Clinic>? = null
    private var mPickedClinic: Clinic? = null

    override fun getFragmentPresenter(): ReachUsPresenter {
        return ReachUsPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as FragmentReachUsBinding
        initialize()
        setListeners()
        loadData()
    }

    private fun setListeners() {
        setClickListener(mBinding.bottomSheet.textViewBookAppointment)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_view_book_appointment -> {
                if (mPickedClinic != null && mContext != null) {
                    SelectAppointmentTypeActivity.startActivity(mContext!!, mPickedClinic?.id!!)
                }
            }
        }
    }

    /**
     * This method initializes the views and loads data (if needed)
     * */
    private fun initialize() {
        (baseActivity as ContainerActivity).setPageTitle(getString(R.string.reach_us_toolbar_title))
        mBottomSheetBehavior =
                BottomSheetBehavior.from<MaterialCardView>(mBinding.bottomSheet.root as MaterialCardView)

        mBinding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        mBinding.mapView.setMultiTouchControls(true)
        mBinding.mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
    }

    private fun loadData() {
        if (mContext != null) {
            presenter.getClinics(mContext!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val appContext = BaseApplication.getBaseApplicationContext()
        Configuration.getInstance().load(appContext,
                PreferenceManager.getDefaultSharedPreferences(appContext))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mBinding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mBinding.mapView.onPause()
    }

    override fun stopUI() {

    }

    override fun onSuccess(clinicList: List<Clinic>) {
        mClinicList = clinicList
        setupMap(clinicList[0])
        addMarkersToTheMap(clinicList)
        ProgressDialogUtils.on().hideProgressDialog()
    }

    private fun setupMap(clinic: Clinic) {
        val mapController = mBinding.mapView.controller
        mapController.setZoom(14.0)
        mapController.animateTo(GeoPoint(clinic.latitude.toDouble(), clinic.longitude.toDouble()))
    }

    private fun addMarkersToTheMap(list: List<Clinic>) {
        for (i in 0 until list.size) {
            val startMarker = Marker(mBinding.mapView)
            startMarker.icon = ViewUtils.getDrawable(R.drawable.map_marker)
            startMarker.id = i.toString()
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            startMarker.position = GeoPoint(list[i].latitude.toDouble(), list[i].longitude.toDouble())

            startMarker.setOnMarkerClickListener { marker, _ ->
                var currentClinic: Clinic? = null

                if (mClinicList != null) {
                    currentClinic = mClinicList?.get(marker.id.toInt())!!
                    mPickedClinic = currentClinic
                }

                if (currentClinic != null) {
                    mBinding.bottomSheet.textViewClinicName.text = currentClinic.name
                    mBinding.bottomSheet.textViewAddress.text = String.format(Locale.getDefault(),
                            getString(R.string.reach_us_address),
                            currentClinic.address, currentClinic.zipCode, currentClinic.city)
                    mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }

                true
            }

            mBinding.mapView.overlays.add(startMarker)
        }
    }

    override fun onError(message: String) {
        ProgressDialogUtils.on().hideProgressDialog()
        ToastUtils.error(message)
    }

    fun hideBottomSheet(event: MotionEvent) {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            val outRect = Rect()
            (mBinding.bottomSheet.root).getGlobalVisibleRect(outRect)

            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }
}