package com.example.privacysettingmanager.data.model

data class Service(
    val id: String,
    val name: String,
    val icon: String,
    val settings: List<Setting>
)
