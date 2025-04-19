package com.example.privacysettingmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.data.repository.ServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeScreenViewModel is a ViewModel responsible for managing UI-related data
 * for the Home screen. It uses Hilt for dependency injection and retrieves data from the ServicesRepository.
 *
 * @param repository ServicesRepository for calling Api methods.
 */
@HiltViewModel
open class HomeScreenViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Service>>(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = repository.getServices()
        }
    }
}
