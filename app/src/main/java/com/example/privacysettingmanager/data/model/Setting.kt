package com.example.privacysettingmanager.data.model

/**
 * Represents a setting within a service in the Privacy Setting Manager app.
 *
 * @param id The unique identifier for the setting.
 * @param name The name of the setting.
 * @param enabled The state of the setting (true if enabled, false if disabled).
 */
data class Setting(
    val id: String,
    val name: String,
    var enabled: Boolean
)
