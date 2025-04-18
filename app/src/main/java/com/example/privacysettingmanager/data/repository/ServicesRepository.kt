package com.example.privacysettingmanager.data.repository

import com.example.privacysettingmanager.data.model.Service

interface ServicesRepository {
    suspend fun getServices(): List<Service>
}