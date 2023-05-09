package com.elkin.challengeinstaleap.di

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.remote.service.ApiInterceptor
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.repository.DashboardRepositoryImpl
import com.elkin.challengeinstaleap.data.repository.DetailMediaRepositoryImpl
import com.elkin.challengeinstaleap.data.repository.ListMediaRepositoryImpl
import com.elkin.challengeinstaleap.domain.repository.DashboardRepository
import com.elkin.challengeinstaleap.domain.repository.DetailMediaRepository
import com.elkin.challengeinstaleap.domain.repository.ListMediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(ApiInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideDashboardService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(Const.URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDashboardRepository(
        apiService: ApiService
    ): DashboardRepository {
        return DashboardRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDetailMediaRepository(
        apiService: ApiService
    ): DetailMediaRepository{
        return DetailMediaRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideListMediaRepository(
        apiService: ApiService
    ): ListMediaRepository{
        return ListMediaRepositoryImpl(apiService)
    }
}