package com.padcmyanmar.ttm.thelibraryapp.views.component

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverlapDecoration : RecyclerView.ItemDecoration() {

    private val vertOverlap = -150//-250
    private val vertOverlapTop =-100
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

       // outRect.set(0, 0, vertOverlap, 0)

        val position: Int =
           parent.getChildAdapterPosition(view)
        if (position != 0) {
           // outRect.top = -10
            outRect.right = vertOverlap
        }
    }

}