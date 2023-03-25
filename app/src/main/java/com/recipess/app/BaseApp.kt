package com.recipess.app

import android.app.Application
import com.recipess.app.core.network.networkModule
import com.recipess.app.core.preferences.preferencesModule
import com.recipess.app.features.recipes.module.recipesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.INFO)
            androidContext(this@BaseApp)
            koin.loadModules(
                listOf(
                    preferencesModule,
                    networkModule,
                    recipesModule
                )
            )
        }
    }
}