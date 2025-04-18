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

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "privacy_settings")
    private val dataStore = context.dataStore

    companion object {
        private fun settingKey(serviceId: String, settingId: String) =
            booleanPreferencesKey("${serviceId}_${settingId}")
    }

    suspend fun saveSettingState(serviceId: String, settingId: String, enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[settingKey(serviceId, settingId)] = enabled
        }
    }

    suspend fun getSettingState(serviceId: String, settingId: String): Boolean? {
        val preferences = dataStore.data.first()
        return preferences[settingKey(serviceId, settingId)]
    }
}
