package br.com.marvy

import android.app.Application
import br.com.marvy.injection.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvyApp)
            modules(koinModules)
        }
    }
}