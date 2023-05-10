package com.elkin.challengeinstaleap.di

import android.content.Context
import com.elkin.challengeinstaleap.core.AppDatabase
import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.local.dao.DetailMediaDao
import com.elkin.challengeinstaleap.data.local.dao.GenresDao
import com.elkin.challengeinstaleap.data.local.dao.MediaDao
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
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAppDataBase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideMediaDao(db: AppDatabase) = db.mediaDao()

    @Singleton
    @Provides
    fun provideDetailMediaDao(db: AppDatabase) = db.detailMediaDao()

    @Singleton
    @Provides
    fun provideGenresDao(db: AppDatabase) = db.genresDao()

    @Singleton
    @Provides
    fun provideDashboardRepository(
        apiService: ApiService,
        mediaDao: MediaDao
    ): DashboardRepository {
        return DashboardRepositoryImpl(apiService, mediaDao)
    }

    @Singleton
    @Provides
    fun provideDetailMediaRepository(
        apiService: ApiService,
        detailMediaDao: DetailMediaDao
    ): DetailMediaRepository {
        return DetailMediaRepositoryImpl(apiService, detailMediaDao)
    }

    @Singleton
    @Provides
    fun provideListMediaRepository(
        apiService: ApiService,
        mediaDao: MediaDao,
        genresDao: GenresDao
    ): ListMediaRepository {
        return ListMediaRepositoryImpl(apiService, mediaDao, genresDao)
    }
}