package com.example.privacysettingmanager.di

import android.content.Context
import com.example.privacysettingmanager.data.local.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides storage-related dependencies.
 * Specifically, it provides a singleton instance of DataStoreManager.
 * that can be injected wherever needed in the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    /**
     * Provides a singleton instance of DataStoreManager using the application context.
     *
     * @param context The application context, injected by Hilt using the @ApplicationContext qualifier.
     * @return [DataStoreManager] instance for managing local key-value storage.
     */
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}