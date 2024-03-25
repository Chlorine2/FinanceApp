package com.app.financeapp

import android.app.Application
import com.app.financeapp.Database.AppContainer
import com.app.financeapp.Database.AppDataClass

class FinanceApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AppDataClass(this)

    }
}