package com.gojek.assignment.di


import com.gojek.assignment.BuildConfig.API_URL
import com.gojek.assignment.repo.RPMRemoteDataSource
import com.gojek.assignment.repo.RPMRepositoryImpl
import com.squareup.picasso.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class APIModule {

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://csrng.net")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetroRepository(): RPMRepositoryImpl {
        return RPMRepositoryImpl()
    }

    @Provides
    fun provideRPMRemoteDataSource(): RPMRemoteDataSource {
        return RPMRemoteDataSource()
    }

}