package com.tripl3dev.prettystateslib

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tripl3dev.prettystates.StatesConfigFactory
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setLayoutState
import com.tripl3dev.prettystates.setState
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private  val viewsList by lazy {
        listOf(stateButton,imageView,testingTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeStatusBt.setOnClickListener {
            changeStatus()
        }
    }
var currentStatus  : Int = 0
    private fun changeStatus() {
        when (currentStatus){
            0 -> loadingStatus()
            1 -> errorStatus()
            2 -> customStatus()
            3 -> emptyStatus()
            4 -> normalStatus()
        }
        currentStatus++
    }

    private fun emptyStatus() {
        viewsList.forEach {
            it.setState(StatesConstants.EMPTY_STATE,false)
        }
        changeCurrentStateText("empty")
    }

    private fun normalStatus() {
        currentStatus = 0
        viewsList.forEach {
            it.setState(StatesConstants.NORMAL_STATE,false)
        }
        changeCurrentStateText("normal")
    }

    private fun customStatus() {
        viewsList.forEach {
            it.setLayoutState(R.layout.custom_state_view,false)
        }
        changeCurrentStateText("custom")
    }

    private fun errorStatus() {
        viewsList.forEach {
            it.setState(StatesConstants.ERROR_STATE,false).let {
                it.setOnClickListener {
                    changeStatus()
                }
            }
        }
        changeCurrentStateText("error")
    }

    private fun loadingStatus() {
        viewsList.forEach {
            it.setState(StatesConstants.LOADING_STATE,false)
        }
        changeCurrentStateText("loading")
    }

    private fun changeCurrentStateText(state : String){
        textView.text = "current state is $state state"
    }



}
