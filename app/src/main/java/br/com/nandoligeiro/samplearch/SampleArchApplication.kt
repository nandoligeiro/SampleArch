package br.com.nandoligeiro.samplearch

import android.app.Application
import androidx.multidex.MultiDexApplication
import br.com.nandoligeiro.samplearch.di.appModule
import br.com.nandoligeiro.samplearch.di.networkModule
import br.com.nandoligeiro.samplearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleArchApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@SampleArchApplication)
            modules(appModule, networkModule)
        }
    }
}
