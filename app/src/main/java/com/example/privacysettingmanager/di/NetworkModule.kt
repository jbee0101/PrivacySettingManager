package com.example.privacysettingmanager.di

import android.content.Context
import com.example.privacysettingmanager.network.AssetsResponseInterceptor
import com.example.privacysettingmanager.network.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Hilt module that provides network-related dependencies, including the OkHttpClient,
 * Retrofit instance, and API service interface.
 * It provides a singleton instance of all dependencies that can be injected wherever needed in the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides an OkHttpClient instance with an interceptor to mock network responses.
     *
     * @param context The application context used to access assets.
     * @return An [OkHttpClient] configured with an interceptor to mock responses.
     */
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AssetsResponseInterceptor(context))
            .build()
    }

    /**
     * Provides a Retrofit instance configured with the OkHttpClient.
     *
     * @param client The OkHttpClient instance to be used with Retrofit.
     * @return A [Retrofit] instance configured with the provided OkHttpClient.
     */
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fake.api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the ServiceApi interface, which contains the methods for network calls.
     *
     * @param retrofit The Retrofit instance used to create the API service.
     * @return The [ServiceApi] interface for making network requests.
     */
    @Provides
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}
