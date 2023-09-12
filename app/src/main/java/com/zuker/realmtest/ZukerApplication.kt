package com.zuker.realmtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm

@HiltAndroidApp
class ZukerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
