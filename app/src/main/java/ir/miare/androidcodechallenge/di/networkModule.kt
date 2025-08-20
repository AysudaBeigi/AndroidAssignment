package ir.miare.androidcodechallenge.di

import android.content.Context
import android.util.Log
import ir.logicbase.mockfit.MockFitConfig
import ir.logicbase.mockfit.MockFitInterceptor
import ir.miare.androidcodechallenge.data.RankingApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 20L
private const val WRITE_TIMEOUT = 20L
private const val READ_TIMEOUT = 20L

val networkModule = module {

    // Provide Http logger
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Provide OkHttpClient with MockFit
    single {
        val context: Context = get()
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>()) // real logger
            .addInterceptor(
                MockFitInterceptor(
                    bodyFactory = { input -> context.assets.open(input) },
                    logger = { tag, message -> Log.d(tag, message) },
                    baseUrl = "https://test_baseurl.com/v2/",
                    requestPathToJsonMap = MockFitConfig.REQUEST_TO_JSON,
                    mockFilesPath = "",
                    mockFitEnable = true,
                    apiEnableMock = true,
                    apiIncludeIntoMock = arrayOf(),
                    apiExcludeFromMock = arrayOf(),
                    apiResponseLatency = 1000L
                )
            )
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    // Provide Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://test_baseurl.com/v2/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(get())
            .build()
    }

    // Provide API service
    single<RankingApiService> { get<Retrofit>().create(RankingApiService::class.java) }
}
