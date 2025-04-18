package com.example.privacysettingmanager.di

import com.example.privacysettingmanager.data.repository.ServicesRepository
import com.example.privacysettingmanager.data.repository.ServicesRepositoryImpl
import com.example.privacysettingmanager.network.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideServiceRepository(
        api: ServiceApi
    ): ServicesRepository {
        return ServicesRepositoryImpl(api)
    }
}
