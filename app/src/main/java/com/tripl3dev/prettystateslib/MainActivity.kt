package com.tripl3dev.prettystateslib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tripl3dev.prettystates.StatesConfigFactory
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var testingView :View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testingView = testingTextView
    }

    fun onLoadingClicked(view: View) {
        testingView.setState(StatesConstants.LOADING_STATE)
    }

    fun onErrorClicked(view: View) {
        testingView.setState(StatesConstants.ERROR_STATE)
    }

    fun onEmptyClicked(view: View) {
        testingView.setState(StatesConstants.EMPTY_STATE)

    }

    fun onNormalClicked(view: View) {
        testingView.setState(StatesConstants.NORMAL_STATE)

    }


}
