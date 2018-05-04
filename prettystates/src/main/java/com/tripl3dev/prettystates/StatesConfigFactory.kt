package com.tripl3dev.prettystates

import android.content.Context
import android.view.LayoutInflater
import android.view.View

class StatesConfigFactory private constructor() {
    private var viewsMap: HashMap<Int, View> = HashMap()

    init {
    }


    companion object {
        internal var instance: StatesConfigFactory? = null
        fun intialize(): StatesConfigFactory {
            if (instance == null)
                instance = StatesConfigFactory()
            return instance as StatesConfigFactory
        }

        fun get(): StatesConfigFactory {
            if (instance == null) {
                throw Throwable("Please use intialize fun in App Class OnCreate Method or Before get Instance Method")
            } else {
                return instance as StatesConfigFactory
            }
        }

    }

    fun addStateView(viewStatus: Int, view: View) {
        viewsMap[viewStatus] = view
    }

    fun removeStateView(viewStatus: Int) {
        viewsMap.remove(viewStatus)
    }

    fun getStateView(stateType: Int): View {
        if (viewsMap.contains(stateType)) {
            return viewsMap[stateType]!!
        } else {
            throw Throwable("You haven't set stateView with that State Type")
        }
    }

    fun initDefaultViews(context: Context) {
        viewsMap[StatesConstants.EMPTY_STATE] = LayoutInflater.from(context).inflate(R.layout.prettystates_default_empty_view, null, false)
        viewsMap[StatesConstants.ERROR_STATE] = LayoutInflater.from(context).inflate(R.layout.prettystates_default_error_view, null, false)
        viewsMap[StatesConstants.LOADING_STATE] = LayoutInflater.from(context).inflate(R.layout.prettystates_default_loading_view, null, false)
        viewsMap[StatesConstants.NORMAL_STATE] = View(context)
    }

    fun setDefaultEmptyView(emptyView: View) {
        viewsMap[StatesConstants.EMPTY_STATE] = emptyView
    }

    fun setDefaultErrorView(errorView: View) {
        viewsMap[StatesConstants.ERROR_STATE] = errorView

    }

    fun setDefaultLoadingView(loadingView: View) {
        viewsMap[StatesConstants.LOADING_STATE] = loadingView
    }


}
