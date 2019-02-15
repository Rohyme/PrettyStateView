package com.tripl3dev.prettystates

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout

/**
 * Written By Mohamed Rohyme
 *
 * Modified By Mahmoud Abd-Elaal
 */


fun View.setState(stateType: Int): View {
    Log.i("STATE_CHANGED", "State Type  =  $stateType")
    val stateConfig = StatesConfigFactory.instance
    lateinit var stateView: View
    val currentViewId = this.id
    val stateViewId = currentViewId * 3
    stateView = if (stateType == StatesConstants.NORMAL_STATE) {
        this
    } else {
        stateConfig!!.getStateView(stateType).inflateToView(this.context)
    }
    var parentView: ViewGroup? = this.parent as ViewGroup
    while (!parentView?.isLayoutContainer()!!) {
        parentView = parentView.parent as ViewGroup?
    }


    val view: View? = parentView.findViewById(stateViewId)
    if (view != null) {
        parentView.removeViewInLayout(view)
    }
    if (stateType != StatesConstants.NORMAL_STATE) {
        stateView.id = stateViewId
        when (parentView) {
            is ConstraintLayout -> {
                parentView.addView(stateView)
                val set = ConstraintSet()
                set.clone(parentView)
                set.connect(stateView.id, ConstraintSet.LEFT, this.id, ConstraintSet.LEFT, 4)
                set.connect(stateView.id, ConstraintSet.RIGHT, this.id, ConstraintSet.RIGHT, 4)
                set.connect(stateView.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP, 4)
                set.connect(stateView.id, ConstraintSet.BOTTOM, this.id, ConstraintSet.BOTTOM, 4)
                set.applyTo(parentView)
            }
            is LinearLayout -> {
                parentView.addView(stateView, parentView.indexOfChild(this))
                stateView.layoutParams = this.layoutParams
            }
            else -> {
                parentView.addView(stateView)
                stateView.layoutParams = this.layoutParams
            }
        }


        if (this.visibility == View.VISIBLE) {
            this.visibility = View.INVISIBLE
        }
    } else {
        if (this.visibility == View.INVISIBLE) {
            this.visibility = View.VISIBLE
        }
    }

    return stateView
}

fun View.isLayoutContainer(): Boolean {
    return this is LinearLayout ||
            this is RelativeLayout ||
            this is FrameLayout ||
            this is ConstraintLayout ||
            this is TableLayout
}

fun Int.inflateToView(context: Context): View {
    return LayoutInflater.from(context).inflate(this, null, false)
}