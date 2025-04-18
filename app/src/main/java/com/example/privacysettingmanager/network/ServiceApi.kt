package com.example.privacysettingmanager.network

import com.example.privacysettingmanager.data.model.ServiceResponse
import retrofit2.http.GET

interface ServiceApi {
    @GET("services")
    suspend fun getServices(): ServiceResponse
}