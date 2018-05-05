package com.tripl3dev.prettystates

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

fun View.setState(stateType: Int): View {
    Log.e("STATE_CHANGED", "State Type  =  $stateType")
    val stateConfig = StatesConfigFactory.instance
    val stateView = stateConfig!!.getStateView(stateType)
    var parentView: ViewGroup? = null
    if (this.parent is ViewGroup && this.parent !is RecyclerView) {
        parentView = this.parent as ViewGroup
    }
    if (parentView != null) {
        val view: View? = parentView.findViewById(StatesConstants.STATE_VIEW_ID)
        if (view != null) {
            view.visibility = View.GONE
        }
        if (stateType != StatesConstants.NORMAL_STATE) {
            stateView.id = StatesConstants.STATE_VIEW_ID
            stateView.layoutParams = this.layoutParams
            if (view == null) {
                if (parentView is LinearLayout) {
                    parentView.addView(stateView, parentView.indexOfChild(this))
                } else {
                    parentView.addView(stateView)
                }
            } else {
                val index = parentView.indexOfChild(view)
                parentView.removeView(view)
                parentView.addView(stateView, index)
                stateView.visibility = View.VISIBLE
            }
            if (this.visibility == View.VISIBLE) {
                this.visibility = View.GONE
            }
        } else {
            if (this.visibility == View.GONE) {
                this.visibility = View.VISIBLE
            }
        }
    }
    return stateView
}