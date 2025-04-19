package com.example.privacysettingmanager.data.model

/**
 * Represents the response from the API that contains a list of services.
 *
 * @param services A list of services fetched from the API.
 */
data class ServiceResponse(
    val services: List<Service>
)
