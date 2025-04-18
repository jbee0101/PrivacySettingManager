package com.example.privacysettingmanager.data.repository

import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.network.ServiceApi
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(
    private val api: ServiceApi
) : ServicesRepository {
    override suspend fun getServices(): List<Service> {
        return api.getServices().services
    }
}
