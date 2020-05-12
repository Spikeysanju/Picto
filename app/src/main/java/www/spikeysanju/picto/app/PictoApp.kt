package www.spikeysanju.picto.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import www.spikeysanju.picto.di.databaseModule
import www.spikeysanju.picto.di.networkModule
import www.spikeysanju.picto.di.viewModelModule


class PictoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // init Koin
        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(networkModule, databaseModule, viewModelModule)
        }
    }
}