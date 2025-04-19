package com.example.privacysettingmanager.data.repository

import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.network.ServiceApi
import javax.inject.Inject

/**
 * Implementation of the ServicesRepository interface. This class is responsible for
 * interacting with the ServiceApi to fetch data and return it to the rest of the app.
 *
 * @param api The ServiceApi instance used to make network requests.
 */
class ServicesRepositoryImpl @Inject constructor(
    private val api: ServiceApi
) : ServicesRepository {
    /**
     * Fetches the list of services from the ServiceApi.
     *
     * @return A list of [Service] objects retrieved from the API.
     */
    override suspend fun getServices(): List<Service> {
        return api.getServices().services
    }
}
