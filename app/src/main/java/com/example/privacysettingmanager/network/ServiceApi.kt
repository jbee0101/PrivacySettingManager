package com.example.privacysettingmanager.network

import com.example.privacysettingmanager.data.model.ServiceResponse
import retrofit2.http.GET

/**
 * Retrofit interface to define network calls related to services.
 */
interface ServiceApi {
    /**
     * Makes a GET request to fetch a list of services from the "services" endpoint.
     *
     * @return [ServiceResponse] object containing the list of services.
     */
    @GET("services")
    suspend fun getServices(): ServiceResponse
}