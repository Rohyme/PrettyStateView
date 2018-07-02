package com.tripl3dev.prettystateslib

import android.app.Application
import com.tripl3dev.prettystates.StatesConfigFactory

class PrettyStateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StatesConfigFactory.intialize().initDefaultViews()
    }
}