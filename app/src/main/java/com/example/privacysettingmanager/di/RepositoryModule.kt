package com.example.privacysettingmanager.di

import com.example.privacysettingmanager.data.repository.ServicesRepository
import com.example.privacysettingmanager.data.repository.ServicesRepositoryImpl
import com.example.privacysettingmanager.network.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module that provides repository dependencies for the app.
 * It provides a singleton instance of ServicesRepository that can be injected wherever needed in the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides an implementation of ServicesRepository by injecting ServiceApi.
     *
     * @param api The Retrofit service interface used to fetch data from the network.
     * @return An instance of [ServicesRepositoryImpl]
     */
    @Provides
    fun provideServiceRepository(
        api: ServiceApi
    ): ServicesRepository {
        return ServicesRepositoryImpl(api)
    }
}
