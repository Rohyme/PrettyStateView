package com.tripl3dev.prettystates

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


fun View.setState(stateType: Int): View {
    Log.e("STATE_CHANGED", "State Type  =  $stateType")
    val stateConfig = StatesConfigFactory.instance
    lateinit var stateView: View
    stateView = if (stateType == StatesConstants.NORMAL_STATE) {
        View(this.context)
    } else {
        stateConfig!!.getStateView(stateType).inflateToView(this.context)
    }
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
                if (stateView.parent != null)
                    (stateView.parent as ViewGroup).removeView(stateView) // <- fix

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

fun Int.inflateToView(context: Context): View {
    return LayoutInflater.from(context).inflate(this, null, false)
}