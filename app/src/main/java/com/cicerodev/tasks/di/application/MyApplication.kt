package com.cicerodev.tasks.di.application

// MyApplication.kt
import android.app.Application
import com.cicerodev.tasks.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // Modules
            modules(appModule)
        }
    }
}