package com.itechsoftsolutions.tree.main.data.local.model

import com.itechsoftsolutions.tree.main.ui.base.callback.SelectionListener
import com.itechsoftsolutions.tree.main.ui.base.component.BaseActivity
import com.itechsoftsolutions.tree.main.ui.base.component.BaseFragment

/**
 * This is model class for passing parameters to build selection tracker of a selectable RecyclerView.
 * @property selectionId selection id of the tracker
 * @property willSelectSingleItem if single item is to be selected
 * @property activity current activity object (if fragment doesn't exist)
 * @property fragment current fragment object (if exists)
 * @property selectionListener listener to get the states of selection
 * @author Mohd. Asfaq-E-Azam Rifat
 */
data class SelectionTrackerParameters(
        val selectionId: String,
        val willSelectSingleItem: Boolean,
        val activity: BaseActivity<*, *>?,
        val fragment: BaseFragment<*, *>?,
        val selectionListener: SelectionListener?)