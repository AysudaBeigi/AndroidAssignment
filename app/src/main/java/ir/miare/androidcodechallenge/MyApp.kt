package ir.miare.androidcodechallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ir.miare.androidcodechallenge.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(appModule, networkAccessTokenModule, githubModule,networkRepositoryModule))
        }
    }
}
