package com.itechsoftsolutions.tree.main.ui.base.helper

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechsoftsolutions.tree.utils.helper.Constants

class RecyclerViewPaginator(
        private val listener: Listener,
        private val itemVisibilityThreshold: Int = Constants.Default.TRUE_INTEGER
) : RecyclerView.OnScrollListener() {

    var isRefreshing = false
    var currentPage = Constants.Invalid.INVALID_INTEGER
    var lastPage = Constants.Invalid.INVALID_INTEGER

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager ?: return

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem: Int =
                when (layoutManager) {
                    is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                    is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                    else -> Constants.Invalid.INVALID_INTEGER
                }

        if (lastVisibleItem != Constants.Invalid.INVALID_INTEGER
                && currentPage != Constants.Invalid.INVALID_INTEGER
                && lastPage != Constants.Invalid.INVALID_INTEGER
                && !isRefreshing
                && currentPage != lastPage
                && totalItemCount <= (lastVisibleItem + itemVisibilityThreshold)
        ) {
            listener.onPagination(currentPage, (currentPage + 1), lastPage)
            isRefreshing = true
        }
    }

    interface Listener {
        fun onPagination(currentPage: Int, nextPage: Int, lastPage: Int)
    }
}