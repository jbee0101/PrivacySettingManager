package com.example.privacysettingmanager.data.model

/**
 * Represents a service in the Privacy Setting Manager app.
 *
 * @param id Unique identifier for the service.
 * @param name The name of the service.
 * @param icon The URL or path to the service's icon image.
 * @param settings A list of settings associated with the service that can be toggled by the user.
 */
data class Service(
    val id: String,
    val name: String,
    val icon: String,
    val settings: List<Setting>
)
