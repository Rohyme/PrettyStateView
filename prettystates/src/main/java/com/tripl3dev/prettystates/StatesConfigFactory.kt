package com.tripl3dev.prettystates

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.SparseArray
import android.view.View


class StatesConfigFactory private constructor() {
    private var viewsMap: SparseArray<Int> = SparseArray()

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


    /**
     * Init default views
     */

    fun initDefaultViews() {
        viewsMap.put(StatesConstants.EMPTY_STATE, R.layout.prettystates_default_empty_view)
        viewsMap.put(StatesConstants.ERROR_STATE, R.layout.prettystates_default_error_view)
        viewsMap.put(StatesConstants.LOADING_STATE, R.layout.prettystates_default_loading_view)
        viewsMap.put(StatesConstants.NORMAL_STATE, R.id.state_view_layout)
    }

    /**
     * Init Normal view
     */
    fun initViews() {
        viewsMap.put(StatesConstants.NORMAL_STATE, R.id.state_view_layout)
    }


    /**
     * Adding new stateView
     *
     *@param viewStateType -> Constant for your new stateView
     * @param customLayout -> Your new stateView layout
     */
    fun addStateView(viewStateType: Int, @LayoutRes customLayout: Int) {
        if (viewsMap[viewStateType] == null)
            viewsMap.put(viewStateType, customLayout)
    }

    /**
     * Remove Existing stateView
     * @param viewStateType -> Constant of your StateView you wanna to remove
     */

    fun removeStateView(viewStateType: Int) {
        if (viewsMap[viewStateType] != null)
            viewsMap.remove(viewStateType)
        else
            throw Throwable("There isn't any stateView with that State Type")
    }

    /**
     * set Existing stateView with new layout
     * @param viewStateType -> Constant of new stateView
     * @param customLayout  -> Layout for your new stateview
     */
    fun setStateView(viewStateType: Int, @LayoutRes customLayout: Int) {
        viewsMap.setValueAt(viewStateType, customLayout)
    }

    /**
     * get existing stateView with State constant as
     * @return  stateView layout
     * @param viewStateType ->  Constant of stateView
     */
    fun getStateView(viewStateType: Int): Int {
        if (viewsMap.get(viewStateType) != null) {
            return viewsMap[viewStateType]!!
        } else {
            throw Throwable("There isn't any stateView with that State Type")
        }
    }


    /**
     * get existing stateView with State constant
     * @return  stateView View
     * @param viewStateType ->  Constant of stateView
     */
    fun getStateView(viewStateType: Int, context: Context): View {
        if (viewsMap.get(viewStateType) != null) {
            return viewsMap[viewStateType].inflateToView(context)
        } else {
            throw Throwable("There isn't any stateView with that State Type")
        }
    }


    /**
     * set default empty stateView layout
     * @param emptyLayout -> layout of empty stateView
     */
    fun setDefaultEmptyLayout(@LayoutRes emptyLayout: Int): StatesConfigFactory {
        viewsMap.put(StatesConstants.EMPTY_STATE, emptyLayout)
        return this
    }


    /**
     * set default error stateView layout
     * @param emptyLayout -> layout of empty stateView
     */
    fun setDefaultErrorLayout(@LayoutRes errorLayout: Int): StatesConfigFactory {
        viewsMap.put(StatesConstants.ERROR_STATE, errorLayout)
        return this
    }


    /**
     * set default loading stateView layout
     * @param emptyLayout -> layout of empty stateView
     */
    fun setDefaultLoadingLayout(@LayoutRes loadingLayout: Int): StatesConfigFactory {
        viewsMap.put(StatesConstants.LOADING_STATE, loadingLayout)
        return this
    }

}
