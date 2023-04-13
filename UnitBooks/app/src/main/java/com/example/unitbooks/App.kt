package com.example.unitbooks

import android.app.Application
import com.example.unitbooks.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidLogger()
        androidContext(this@App)
        modules(appComponent)
    }
}