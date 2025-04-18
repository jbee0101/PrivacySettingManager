package com.example.privacysettingmanager.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privacysettingmanager.data.local.DataStoreManager
import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.data.repository.ServicesRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _service = MutableStateFlow<Service?>(null)
    val service: StateFlow<Service?> = _service.asStateFlow()

    init {
        val encodedJson = savedStateHandle.get<String>("serviceJson") ?: ""
        val json = Uri.decode(encodedJson)
        val serviceObj = Gson().fromJson(json, Service::class.java)

        viewModelScope.launch {
            val updatedSettings = serviceObj.settings.map { setting ->
                val stored = dataStoreManager.getSettingState(serviceObj.id, setting.id)
                setting.copy(enabled = stored ?: setting.enabled)
            }
            _service.value = serviceObj.copy(settings = updatedSettings)
        }
    }

    fun toggleSetting(settingId: String) {
        _service.update { current ->
            current?.copy(
                settings = current.settings.map { setting ->
                    if (setting.id == settingId) {
                        val newValue = !setting.enabled
                        viewModelScope.launch {
                            dataStoreManager.saveSettingState(current.id, setting.id, newValue)
                        }
                        setting.copy(enabled = newValue)
                    } else setting
                }
            )
        }
    }
}