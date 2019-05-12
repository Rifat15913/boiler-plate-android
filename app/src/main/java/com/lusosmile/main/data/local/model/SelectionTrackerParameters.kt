package com.lusosmile.main.data.local.model

import androidx.recyclerview.selection.SelectionTracker
import com.lusosmile.main.ui.base.callback.SelectionListener

data class SelectionTrackerParameters(
        val selectionId: String,
        val willSelectSingleItem: Boolean,
        var trackerObjectFromActivityOrFragment: SelectionTracker<Long>?,
        val selectionListener: SelectionListener?)