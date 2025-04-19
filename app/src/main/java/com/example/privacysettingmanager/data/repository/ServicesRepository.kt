package com.example.privacysettingmanager.data.repository

import com.example.privacysettingmanager.data.model.Service

/**
 * This interface defines the methods for fetching services.
 */
interface ServicesRepository {
    /**
     * Fetches a list of services for the Privacy Setting Manager app.
     *
     * @return A list of [Service] objects representing different services with their settings.
     */
    suspend fun getServices(): List<Service>
}