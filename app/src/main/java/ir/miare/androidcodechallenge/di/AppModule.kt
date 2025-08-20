package ir.miare.androidcodechallenge.di

import androidx.preference.PreferenceManager
import ir.miare.androidcodechallenge.data.RankingApiService
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    single<RankingApiService> { get<Retrofit>().create(RankingApiService::class.java) }
}
