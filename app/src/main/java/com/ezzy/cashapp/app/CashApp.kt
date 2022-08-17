package com.ezzy.cashapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CashApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}