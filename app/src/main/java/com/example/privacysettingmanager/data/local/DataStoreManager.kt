package com.example.privacysettingmanager.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Handles saving and retrieving the state of individual service settings using Jetpack DataStore.
 * This helps persist user preferences for each setting even after app restarts.
 *
 * @param context Application context injected via Hilt.
 */
@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "privacy_settings")
    private val dataStore = context.dataStore

    companion object {
        /**
         * Creates a unique key for storing the enabled state of a specific setting
         * under a specific service in DataStore.
         *
         * It's defined inside the companion object because:
         * - It doesn't rely on any instance variables or functions.
         * - It is a utility method that's logically associated with the class,
         *   but doesn't need to be re-created with each instance.
         *
         * @param serviceId ID of the service the setting belongs to.
         * @param settingId ID of the specific setting.
         */
        private fun settingKey(serviceId: String, settingId: String) =
            booleanPreferencesKey("${serviceId}_${settingId}")
    }

    /**
     * Saves the current enabled/disabled state of a setting in DataStore.
     *
     * @param serviceId ID of the service the setting belongs to.
     * @param settingId ID of the specific setting.
     * @param enabled Whether the setting is enabled or not.
     */
    suspend fun saveSettingState(serviceId: String, settingId: String, enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[settingKey(serviceId, settingId)] = enabled
        }
    }

    /**
     * Retrieves the saved state of a setting, if it exists.
     *
     * @param serviceId ID of the service.
     * @param settingId ID of the setting.
     * @return The saved [Boolean] state, or null if not set.
     */
    suspend fun getSettingState(serviceId: String, settingId: String): Boolean? {
        val preferences = dataStore.data.first()
        return preferences[settingKey(serviceId, settingId)]
    }
}
