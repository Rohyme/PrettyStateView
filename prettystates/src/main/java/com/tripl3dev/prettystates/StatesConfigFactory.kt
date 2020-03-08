package com.tripl3dev.prettystates

import android.content.Context
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes


object StatesConfigFactory {
    private var viewsMap: SparseArray<Int> = SparseArray()

    init {
        initDefaultViews()
    }

    /**
     * Init default views
     */

    private fun initDefaultViews(): StatesConfigFactory {
        viewsMap.put(StatesConstants.EMPTY_STATE, R.layout.prettystates_default_empty_view)
        viewsMap.put(StatesConstants.ERROR_STATE, R.layout.prettystates_default_error_view)
        viewsMap.put(StatesConstants.LOADING_STATE, R.layout.prettystates_default_loading_view)
        viewsMap.put(StatesConstants.NORMAL_STATE, R.id.state_view_layout)
        return this
    }

    /**
     * Adding new stateView
     *
     *@param viewStateType -> Constant for your new stateView
     *@param customLayout -> Your new stateView layout
     */
    fun addStateView(viewStateType: Int, @LayoutRes customLayout: Int): StatesConfigFactory {
        if (viewsMap[viewStateType] == null)
            viewsMap.put(viewStateType, customLayout)
        return this
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
            return LayoutInflater.from(context).inflate(viewsMap[viewStateType], null, false)
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

    /**
     * find type with layout
     * if the type not exit it will be created then  added to the view map
     * @return StateType
     */

    fun findLayout(@LayoutRes layout: Int): Int {
        var keyIndex = getKeyOfValue(layout)
        if (keyIndex == null) {
            keyIndex = generateRandomId()
            viewsMap.put(keyIndex,layout)
            Log.e("keyIndex -> ","$keyIndex for $layout")
        }
        return  keyIndex
    }

    private fun getKeyOfValue(@LayoutRes layout: Int): Int? {
        for (i in 0 until  viewsMap.size()){
            if (viewsMap.valueAt(i) == layout){
                return viewsMap.keyAt(i)
            }
        }
        return null
    }

}
