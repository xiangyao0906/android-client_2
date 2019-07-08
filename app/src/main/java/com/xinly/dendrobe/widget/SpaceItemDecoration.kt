package com.xinly.dendrobe.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by zm on 2019-07-08.
 */
class SpaceItemDecoration(val leftRight:Int, val topBottom: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager as LinearLayoutManager
        //竖直方向
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL){
            //最后一项需要bottom
            if (parent.getChildAdapterPosition(view) == layoutManager.itemCount-1) {
                outRect.bottom = topBottom
            }
            outRect.top = topBottom
            outRect.left = leftRight
            outRect.right = leftRight
        }else {
            //最后一项需要right
            if (parent.getChildAdapterPosition(view) == layoutManager.itemCount-1){
                outRect.right = leftRight
            }
            outRect.top = topBottom
            outRect.left = leftRight
            outRect.bottom = topBottom
        }
    }
}