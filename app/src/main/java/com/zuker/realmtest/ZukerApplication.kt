package com.zuker.realmtest

import android.app.Application
import io.realm.Realm

class ZukerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}
