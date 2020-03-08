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
    lateinit var testingView: View
    val CUSTOM_STATE = 23232

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testingView = testingTextView
    }

    fun onLoadingClicked(view: View) {
        measureTimeMillis {
            testingView.setState(StatesConstants.LOADING_STATE,false)
        }.let {
            Log.e("takes time ","$it")
        }
    }

    fun onErrorClicked(view: View) {
        measureTimeMillis {
            testingView.setState(StatesConstants.ERROR_STATE,false)
        }.let {
            Log.e("takes time ","$it")
        }
    }

    fun onEmptyClicked(view: View) {
        measureTimeMillis {
            testingView.setState(StatesConstants.EMPTY_STATE,false).let {
                it.setOnClickListener {
                    onNormalClicked(it)
                }
            }
        }.let {
            Log.e("takes time ","$it")
        }
    }

    fun onNormalClicked(view: View) {
        measureTimeMillis {
            testingView.setState(StatesConstants.NORMAL_STATE,false)
        }.let {
            Log.e("takes time ","$it")
        }
    }

    fun onCustomClicked(view: View) {
        measureTimeMillis {
            testingView.setLayoutState( R.layout.custom_state_view,false)
        }.let {
            print("takes $it")
        }
    }


}
