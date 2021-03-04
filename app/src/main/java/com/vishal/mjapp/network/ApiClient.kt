package com.vishal.mjapp.network

import com.vishal.mjapp.BuildConfig
import com.vishal.mjapp.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { apiInterface(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .addCallAdapterFactory(LiveDataCallAdapterFactory())                                    // LiveDataCall adapter.
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())                                   // Kotlin coroutines adapter.
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())                              // RxJava Adapter
        .addConverterFactory(GsonConverterFactory.create())                                     // GSON builder
        .addConverterFactory(ScalarsConverterFactory.create())                                  // Scalars converter
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()

    val loggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        loggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY                                // Logging interceptor
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
//    okHttpClientBuilder.addInterceptor(authInterceptor)
    okHttpClientBuilder.connectTimeout(
        30,
        TimeUnit.SECONDS
    )                                // connectTimeout
    okHttpClientBuilder.readTimeout(
        50,
        TimeUnit.SECONDS
    )                                   // readTimeout
    okHttpClientBuilder.writeTimeout(
        60,
        TimeUnit.SECONDS
    )                                  // readTimeout
        .retryOnConnectionFailure(true)
    return okHttpClientBuilder.build()
}

fun apiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)





