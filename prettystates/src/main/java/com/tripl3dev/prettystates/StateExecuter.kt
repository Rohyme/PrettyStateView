package com.tripl3dev.prettystates

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat

/**
 * Written By Mohamed Rohyme
 *
 * Modified By Mahmoud Abd-Elaal
 */


fun View.setLayoutState(@LayoutRes stateLayout: Int, removeOldState: Boolean = true): View {
    return StatesConfigFactory.findLayout(stateLayout).let {
        setState(it, removeOldState)
    }
}

fun View.setState(stateType: Int, removeOldState: Boolean = true): View {
    Log.i("STATE_CHANGED", "State Type  =  $stateType")
    val stateTypeConfig = getTheStateTypeConfigIfExist(stateType)
    if (stateType == StatesConstants.NORMAL_STATE) {
        hideAllPrevStateViews(removeOldState)
        if (visibility == View.INVISIBLE || visibility == View.GONE) {
            visibility = View.VISIBLE
        }
        return this
    }
    getParentView().also { parentView ->
        if (stateTypeConfig != null) {
            parentView.findViewById<View>(stateTypeConfig.id).let { stateView ->
                if (!stateTypeConfig.isActive) {
                    hideContentAndRelatedStateViews(removeOldState)
                    addStatesConfig(stateTypeConfig.apply { isActive = true })
                    showStateView(stateView)
                }
                return@setState stateView
            }
        } else {
            createStateView(stateType).also { stateView ->
                hideContentAndRelatedStateViews(removeOldState)
                addStatesConfig(StateViewConfig(stateView.id, stateType, true))
                addStateView(stateView, parentView)
                return@setState stateView
            }
        }
    }

}

/**
 * create new state view
 */
private fun View.createStateView(stateType: Int): View {
    val newStateViewID = generateRandomId()
    return StatesConfigFactory.getStateView(stateType).inflateToView(context).apply {
        id = newStateViewID
    }
}

/**
 * add or show state (if exist)
 */
private fun View.addStateView(stateView: View, parentView: ViewGroup) {
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
}

fun showStateView(stateView: View?) {
    if (stateView?.visibility != View.VISIBLE)
        stateView?.visibility = View.VISIBLE
}

/**
 * hiding or removing all previous stateViews according to @param removeOldState
 * hiding content view
 */

private fun View.hideAllPrevStateViews(removeOldState: Boolean): List<StateViewConfig> {
    return (previousType() ?: listOf()).map {
        it.apply {
            getParentView().let { parentView ->
                parentView.findViewById<View>(it.id)?.also {stateView ->
                    if (removeOldState) {
                        parentView.removeViewInLayout(stateView)
                        return emptyList()
                    }
                    else {
                        isActive = false
                        stateView.hide()
                    }
                }
            }
        }
    }
}
private fun View.hideContentAndRelatedStateViews(removeOldState: Boolean) {
    // Hide the content view
    hide()
    // Change previous states visibility if there
    hideAllPrevStateViews(removeOldState)
}
private fun View.hide(){
    if (this.visibility == View.VISIBLE) {
        if (getParentView() is LinearLayout) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.INVISIBLE
        }
    }
}

/**
 * view extensions
 */
private fun View.getParentView(): ViewGroup {
    var parentView: ViewGroup? = this.parent as ViewGroup
    while (!parentView?.isLayoutContainer()!!) {
        parentView = parentView.parent as ViewGroup?
    }
    return parentView
}

private fun View.isLayoutContainer(): Boolean {
    return this is LinearLayout ||
            this is RelativeLayout ||
            this is FrameLayout ||
            this is ConstraintLayout ||
            this is TableLayout
}

fun Int.inflateToView(context: Context): View {
    return LayoutInflater.from(context).inflate(this, null, false)
}

internal fun generateRandomId(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        View.generateViewId()
    } else {
        ViewCompat.generateViewId()
    }
}

/**
 * Handling view states configs
 */
data class StateViewConfig(val id: Int, val type: Int, var isActive: Boolean)

private fun View.previousType() = getTag(R.id.related_state_view_config_key) as? List<StateViewConfig>?

private fun View.getTheStateTypeConfigIfExist(stateType: Int) = previousType()?.find { it.type == stateType }

private fun View.updateStatesConfig(stateConfig: List<StateViewConfig>?) {
    Log.e("stateViewConfigs", "${stateConfig?.size} --> ${stateConfig.toString()}")
    setTag(R.id.related_state_view_config_key, stateConfig?.distinctBy { it.type })
}

fun View.addStatesConfig(stateViewConfig: StateViewConfig) = updateStatesConfig((previousType()?: listOf()) + stateViewConfig)


